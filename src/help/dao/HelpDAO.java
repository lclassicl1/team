package help.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Exception.HelperNotFoundException;
import article.model.Article;
import help.model.Help;
import help.model.SearchHelp;
import help.model.WriterRequest;
import jdbc.JdbcUtil;

public class HelpDAO {
	public int articleReq(Connection conn,int userNo )throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into article(article_category,user_no) " + 
					"value('help',?)";
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
		String sql = "insert into helpboard(article_no,article_category,user_no,help_title,help_content,user_name,help_update,help_category) " + 
				"value(?,?,?,?,?,?,now(),?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo()); 
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			pstmt.setString(4, writerReq.getHelpTitle());
			pstmt.setString(5, writerReq.getHelpContent());
			pstmt.setString(6, writerReq.getUserName());
			pstmt.setString(7, writerReq.getHelpCategory());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<Help> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helpboard " +
						"where isshow='Y' " + 
						"order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Help> helpList = new ArrayList<>();
			while(rs.next()) {
				Help help = coverHelp(rs);
				if(help != null) {
					helpList.add(help);
				}
			}
			return helpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<Help> search(Connection conn,SearchHelp search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helpboard " +
						"where isshow='Y' and help_category like ? and help_title like ? " + 
						 "order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<Help> helpList = new ArrayList<>();
			while(rs.next()) {
				Help help = coverHelp(rs);
				if(help != null) {
					helpList.add(help);
				}
			}
			return helpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Help selectByNo(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helpboard where article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			Help help = null;
			
			if(rs.next()) {
				help = coverHelp(rs);
			}
			return help;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void incrementReadCnt(Connection conn,int articleNo) {
		PreparedStatement pstmt = null;
		String sql ="update helpboard set help_readcnt = help_readcnt+1 where article_no = ?";
		
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
		String sql = "select count(*) from helpboard where isshow='Y' ";
		
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
		String sql = "update helpboard set help_title=?,help_content=?, help_category=? where article_no = ?";
		
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
		String sql = "update helpboard set isshow='N' where article_no=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Help coverHelp(ResultSet rs) throws SQLException {
		return new Help(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"),rs.getString("help_title"),rs.getString("help_content"),rs.getString("user_name"),
						rs.getTimestamp("help_credate"),rs.getTimestamp("help_update"),rs.getInt("help_readcnt"),rs.getString("isshow"),rs.getString("help_category"));
	}
}
