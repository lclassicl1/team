package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.DAO.ArticleDAO;
import article.model.Article;
import article.model.ArticlePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListArticleService {

	private ArticleDAO articleDAO = new ArticleDAO();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = articleDAO.selectCount(conn);
			
			List<Article> articleList = articleDAO.select(conn, (pageNum-1)*size, size);
			
			return new ArticlePage(total,pageNum,size,articleList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
