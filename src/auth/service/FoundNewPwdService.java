package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.DAO.UserDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class FoundNewPwdService {

	UserDAO userDAO = new UserDAO();
	
	public void resetPwd(String userId,String newPwd) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			userDAO.update(conn, userId,newPwd);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
