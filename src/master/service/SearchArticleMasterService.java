package master.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import article.model.ArticlePage;
import article.model.SearchArticle;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import master.dao.MasterDAO;

public class SearchArticleMasterService {

	MasterDAO masterDAO = new MasterDAO();
	private int size = 10;
	
	public ArticlePage search(int pageNum, String input){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = masterDAO.selectCount(conn);
			
			SearchArticle search = new SearchArticle((pageNum-1)*size,size,input);
			
			List<Article> noticeList = masterDAO.search(conn, search);
			
			
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
