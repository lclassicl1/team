package notice.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import article.model.Article;
import notice.model.Notice;
import notice.model.SearchNotice;
import notice.model.WriterRequest;
import jdbc.JdbcUtil;

public class NoticeDAO {

	public void insert(Connection conn,WriterRequest writerReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into noticeBOARD(article_no,article_category,user_no) " + 
				"value(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo()); 
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<Article> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article  " +
						"where isshow='Y' and article_category='notice' order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Article> noticeList = new ArrayList<>();
			while(rs.next()) {
				Article notice = coverArticle(rs);
				if(notice != null) {
					noticeList.add(notice);
				}
			}
			return noticeList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	public List<Article> search(Connection conn,SearchNotice search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article where isshow='Y' and article_title like ? and article_category='notice' " + 
						 "order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getInput()+"%");
			pstmt.setInt(2, search.getStartRow());
			pstmt.setInt(3, search.getSize());
			rs = pstmt.executeQuery();
			List<Article> noticeList = new ArrayList<>();
			while(rs.next()) {
				Article notice = coverArticle(rs);
				if(notice != null) {
					noticeList.add(notice);
				}
			}
			return noticeList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Notice selectByNo(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from noticeboard where article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			Notice notice = null;
			
			if(rs.next()) {
				notice = coverNotice(rs);
			}
			return notice;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from article where isshow='Y' and article_category='notice' ";
		
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

	private Notice coverNotice(ResultSet rs) throws SQLException {
		return new Notice(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"));
	}
	private Article coverArticle(ResultSet rs)throws SQLException {
		return new Article(rs.getInt("article_no"),rs.getString("article_category"),rs.getString("article_title"),rs.getString("user_name"),rs.getString("article_content"),rs.getTimestamp("article_credate"),
					rs.getTimestamp("article_update"),rs.getInt("article_readcnt"),rs.getString("isshow"),rs.getInt("user_no"));
	}

}
