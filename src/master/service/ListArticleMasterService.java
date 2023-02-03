package master.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import article.model.ArticlePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import master.dao.MasterDAO;

public class ListArticleMasterService {

	private MasterDAO masterDAO = new MasterDAO();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = masterDAO.selectCount(conn);
			
			List<Article> articleList = masterDAO.select(conn, (pageNum-1)*size, size);
			
			return new ArticlePage(total,pageNum,size,articleList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
