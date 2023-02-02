package helper.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import helper.dao.HelperDAO;
import helper.model.HelperList;
import helper.model.HelperPage;
import helper.model.SearchHelper;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SearchHelperService {

	HelperDAO helperDAO = new HelperDAO();
	private int size = 10;
	
	public HelperPage search(int pageNum, String category, String input){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = helperDAO.selectCount(conn);
			
			SearchHelper search = new SearchHelper((pageNum-1)*size,size,category,input);
			
			List<HelperList> helperList = helperDAO.search(conn, search);
			
			
			return new HelperPage(total,pageNum,size,helperList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
