package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.DAO.ArticleDAO;
import article.model.Article;
import article.model.ArticlePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class MypageArticleSearchService {
	ArticleDAO articleDAO = new ArticleDAO();
		
		public ArticlePage getMyapgeArticleCategory(int loginNo,int pageNum,String categorySearch,String input) {
			Connection conn = null;
			int size = 10;
			
			try {
				conn=ConnectionProvider.getConnection();
				int total = articleDAO.selectCountMy(conn, loginNo);
				List<Article> list = articleDAO.selectSearchMy(conn, size*(pageNum-1), size, loginNo, categorySearch,input);
				return new ArticlePage(total,pageNum,size,list);
			}catch(SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				JdbcUtil.close(conn);
			}
		}
}
