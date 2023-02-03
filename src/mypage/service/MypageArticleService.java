package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.DAO.ArticleDAO;
import article.model.Article;
import article.model.ArticlePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class MypageArticleService {

	ArticleDAO articleDAO = new ArticleDAO();
	private int size = 10;
	
	public ArticlePage getMyapgeArticle(int userNo,int pageNum) {
		Connection conn = null;
		
		try {
			conn=ConnectionProvider.getConnection();
			int total = articleDAO.selectCountMy(conn, userNo);
			
			List<Article> list = articleDAO.selectMy(conn, (pageNum-1)*size, size, userNo);
			
			return new ArticlePage(total,pageNum,size,list);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
