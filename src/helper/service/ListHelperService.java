package helper.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import helper.dao.HelperDAO;
import helper.model.Helper;
import helper.model.HelperPage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListHelperService {

	private HelperDAO helperDAO = new HelperDAO();
	private int size = 10;
	
	public HelperPage getHelperPage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = helperDAO.selectCount(conn);
			List<Helper> helperList = helperDAO.select(conn, (pageNum-1)*size, size);
			
			return new HelperPage(total,pageNum,size,helperList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
