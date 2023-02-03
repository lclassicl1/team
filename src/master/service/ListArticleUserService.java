package master.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import article.model.ArticlePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import master.dao.MasterDAO;

public class ListArticleUserService {

	private MasterDAO masterDAO = new MasterDAO();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum,int userNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = masterDAO.selectCountUserArticle(conn,userNo);
			
			List<Article> articleList = masterDAO.selectUserArticle(conn, (pageNum-1)*size, size,userNo);
			
			return new ArticlePage(total,pageNum,size,articleList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
