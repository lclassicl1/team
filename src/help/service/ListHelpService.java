package help.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import help.dao.HelpDAO;
import help.model.HelpList;
import help.model.HelpPage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListHelpService {

	private HelpDAO helpDAO = new HelpDAO();
	private int size = 10;
	
	public HelpPage getHelpPage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = helpDAO.selectCount(conn);
			
			List<HelpList> helpList = helpDAO.select(conn, (pageNum-1)*size, size);
			return new HelpPage(total,pageNum,size,helpList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
