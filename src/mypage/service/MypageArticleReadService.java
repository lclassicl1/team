package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.DAO.ArticleDAO;
import article.model.Article;
import article.model.ArticlePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class MypageArticleReadService {

	ArticleDAO articleDAO = new ArticleDAO();
	
	public ArticlePage readMyapgeArticle(int myArticleNo,int pageNum) {
		
		Connection conn = null;
		int size = 30;
		
		try {
			conn=ConnectionProvider.getConnection();
			int total = articleDAO.selectCountMy(conn, myArticleNo);
			List<Article> list = articleDAO.selectReadArticle(conn, size*(pageNum-1), size, myArticleNo);
			return new ArticlePage(total,pageNum,size,list);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
