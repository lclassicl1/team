package help.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import help.dao.HelpDAO;
import help.model.Help;
import help.model.HelpPage;
import help.model.SearchHelp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SearchHelpService {

	HelpDAO helpDAO = new HelpDAO();
	private int size = 10;
	
	public HelpPage search(int pageNum, String category, String input){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = helpDAO.selectCount(conn);
			
			SearchHelp search = new SearchHelp((pageNum-1)*size,size,category,input);
			
			List<Help> helpList = helpDAO.search(conn, search);
			
			
			return new HelpPage(total,pageNum,size,helpList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
