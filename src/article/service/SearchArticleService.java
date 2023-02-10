package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.DAO.ArticleDAO;
import article.model.Article;
import article.model.ArticlePage;
import article.model.SearchArticle;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SearchArticleService {

	ArticleDAO articleDAO = new ArticleDAO();
	private int size = 10;
	
	public ArticlePage search(int pageNum, String input){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = articleDAO.selectCount(conn);
			
			SearchArticle search = new SearchArticle((pageNum-1)*size,size,input);
			
			List<Article> noticeList = articleDAO.search(conn, search);
			
			
			return new ArticlePage(total,pageNum,size,noticeList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
