package article.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import article.model.Article;
import article.model.ArticleRequest;
import article.model.ModifyRequest;
import article.model.SearchArticle;
import jdbc.JdbcUtil;
import notice.model.SearchNotice;

public class ArticleDAO {
	
	public int articleReq(Connection conn,ArticleRequest articleReq)throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into article(article_category,article_title,user_name,article_content,user_no) " + 
					"value(?,?,?,?,?)";
			String sql2 = "select last_insert_id() from article";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, articleReq.getArticleCategory());
			pstmt.setString(2,articleReq.getArticleTitle() );
			pstmt.setString(3,articleReq.getUserName() );
			pstmt.setString(4,articleReq.getArticleContent() );
			pstmt.setInt(5, articleReq.getUserNo());
			
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

	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from article where isshow='Y'  ";
		
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
	
	public int selectCountMy(Connection conn,int userNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from article where isshow='Y' and user_no=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
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
	
	public List<Article> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article where isshow='Y' " +
						"order by article_no desc limit ?,? " ;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Article> articleList = new ArrayList<>();
			while(rs.next()) {
				Article article = coverArticle(rs);
				if(article != null) {
					articleList.add(article);
				}
			}
			return articleList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<Article> selectMy(Connection conn, int startRow,int size, int userNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article where isshow='Y' and user_no=? " +
						"order by article_no desc limit ?,? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<Article> articleList = new ArrayList<>();
			while(rs.next()) {
				Article article = coverArticle(rs);
				if(article != null) {
					articleList.add(article);
				}
			}
			return articleList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Article> selectReadArticle(Connection conn, int startRow,int size, int myArticleNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article where isshow='Y' and article_no=? " +
						"order by article_no desc limit ?,? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myArticleNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			List<Article> articleList = new ArrayList<>();
			while(rs.next()) {
				Article article = coverArticle(rs);
				if(article != null) {
					articleList.add(article);
				}
			}
			return articleList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<Article> search(Connection conn,SearchArticle search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article where isshow='Y' and article_title like ? " + 
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
	// mypage 검색 기능
	public List<Article> selectSearchMy(Connection conn, int startRow,int size, int userNo,String categorySearch,String input) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article where isshow='Y' and user_no=? and article_category like ? and article_title like ? " +
						"order by article_no desc limit ?,? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setString(2, "%"+categorySearch+"%");
			pstmt.setString(3, "%"+input+"%");
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, size);
			rs = pstmt.executeQuery();
			List<Article> articleList = new ArrayList<>();
			while(rs.next()) {
				Article article = coverArticle(rs);
				if(article != null) {
					articleList.add(article);
				}
			}
			return articleList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	public Article selectByNo(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from article where article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			Article article = null;
			
			if(rs.next()) {
				article = coverArticle(rs);
			}
			return article;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public void update(Connection conn,ModifyRequest modReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update article set article_title=?,article_content=?,article_update=now() where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,modReq.getModTitle());
			pstmt.setString(2, modReq.getModContent());
			pstmt.setInt(3, modReq.getArticleNo());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public void incrementReadCnt(Connection conn,int articleNo) {
		PreparedStatement pstmt = null;
		String sql ="update article set article_readcnt = article_readcnt+1 where article_no = ?";
		
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
	public void isshow(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update article set isshow='N' where article_no=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Article coverArticle(ResultSet rs)throws SQLException {
		return new Article(rs.getInt("article_no"),rs.getString("article_category"),rs.getString("article_title"),rs.getString("user_name"),rs.getString("article_content"),rs.getTimestamp("article_credate"),
					rs.getTimestamp("article_update"),rs.getInt("article_readcnt"),rs.getString("isshow"),rs.getInt("user_no"));
	}
}
