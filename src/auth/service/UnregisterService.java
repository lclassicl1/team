package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.DAO.UserDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class UnregisterService {

	private UserDAO userDAO = new UserDAO();
	
	public void unregister(int userNo) {
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			userDAO.unregiseter(conn, userNo);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
