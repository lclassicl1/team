package helper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exception.HelperNotFoundException;
import helper.model.Helper;
import helper.model.SearchHelper;
import helper.model.WriterRequest;
import jdbc.JdbcUtil;

public class HelperDAO {
	
	public int articleReq(Connection conn,int userNo )throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into article(article_category,user_no) " + 
					"value('helper',?)";
			String sql2 = "select last_insert_id() from article";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			int result = pstmt.executeUpdate();
			int articleNo=0;
			if(result>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql2);
				if(rs.next()) {
					articleNo = rs.getInt(1);
				}
			}
			return articleNo;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public void insert(Connection conn,WriterRequest writerReq)throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "insert into helperboard(article_no,article_category,user_no,helper_title,helper_content,user_name,helper_update,helper_category) " + 
				"value(?,?,?,?,?,?,now(),?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo()); 
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			pstmt.setString(4, writerReq.getHelperTitle());
			pstmt.setString(5, writerReq.getHelperContent());
			pstmt.setString(6, writerReq.getUserName());
			pstmt.setString(7, writerReq.getHelperCategory());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<Helper> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helperboard " +
						"where isshow='Y' " + 
						"order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Helper> helperList = new ArrayList<>();
			while(rs.next()) {
				Helper helper = coverHelper(rs);
				if(helper != null) {
					helperList.add(helper);
				}
			}
			return helperList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<Helper> search(Connection conn,SearchHelper search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helperboard " +
						"where isshow='Y' and helper_category like ? and helper_title like ? " + 
						 "order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<Helper> helperList = new ArrayList<>();
			while(rs.next()) {
				Helper helper = coverHelper(rs);
				if(helper != null) {
					helperList.add(helper);
				}
			}
			return helperList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Helper selectByNo(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helperboard where article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			Helper helper = null;
			
			if(rs.next()) {
				helper = coverHelper(rs);
			}
			if(helper == null) {
				throw new HelperNotFoundException();
			}
			return helper;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void incrementReadCnt(Connection conn,int articleNo) {
		PreparedStatement pstmt = null;
		String sql ="update helperboard set helper_readcnt = helper_readcnt+1 where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from helperboard where isshow='Y' ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("count(*)");
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public void update(Connection conn,String title,String content,String category,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helperboard set helper_title=?,helper_content=?, helper_category=? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setString(3, category);
			pstmt.setInt(4, articleNo);
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void isshow(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helperboard set isshow='N' where article_no=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Helper coverHelper(ResultSet rs) throws SQLException {
		
		return new Helper(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"),rs.getString("helper_title"),rs.getString("helper_content"),rs.getString("user_name"),
				rs.getTimestamp("helper_credate"),rs.getTimestamp("helper_update"),rs.getInt("helper_readcnt"),rs.getString("isshow"),rs.getString("helper_category"));
	}
}
