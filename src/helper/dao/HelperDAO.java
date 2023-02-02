package helper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exception.HelperNotFoundException;
import article.model.ModifyRequest;
import helper.model.Helper;
import helper.model.HelperList;
import helper.model.SearchHelper;
import helper.model.WriterRequest;
import jdbc.JdbcUtil;

public class HelperDAO {
	
	
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public void insert(Connection conn,WriterRequest writerReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into HELPERBOARD(article_no,article_category,user_no,helper_category) " + 
								"value(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo()); 
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			pstmt.setString(4, writerReq.getHelperCategory());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<HelperList> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN helperboard h ON a.article_no=h.article_no " +
				"where a.isshow='Y' order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<HelperList> helperList = new ArrayList<>();
			while(rs.next()) {
				HelperList helperList1 = coverHelperList(rs);
				if(helperList1 != null) {
					helperList.add(helperList1);
				}
			}
			return helperList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<HelperList> search(Connection conn,SearchHelper search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN helperboard h ON a.article_no=h.article_no where a.isshow='Y' and h.helper_category like ? and a.article_title like ? " + 
				 "order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<HelperList> helperList = new ArrayList<>();
			while(rs.next()) {
				HelperList helperList1 = coverHelperList(rs);
				if(helperList1 != null) {
					helperList.add(helperList1);
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
	
	
	
	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =  "select count(*) from article where isshow='Y' and article_category='helper' ";
		
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
	public void update(Connection conn,ModifyRequest modReq,String modCategory)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helperboard set helper_category=? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,modCategory);
			pstmt.setInt(2, modReq.getArticleNo());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	
	private Helper coverHelper(ResultSet rs) throws SQLException {
		return new Helper(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"),
						rs.getString("helper_category"));
	}
	private HelperList coverHelperList(ResultSet rs)throws SQLException {
		return new HelperList(rs.getInt("article_no"),rs.getString("article_category"),rs.getString("article_title"),rs.getString("user_name"),rs.getString("article_content"),rs.getTimestamp("article_credate"),
					rs.getTimestamp("article_update"),rs.getInt("article_readcnt"),rs.getString("isshow"),rs.getInt("user_no"),rs.getString("helper_category"));
	}
}
