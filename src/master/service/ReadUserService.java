package master.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.UserNotFoundException;
import auth.DAO.UserDAO;
import auth.model.User;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ReadUserService {

	UserDAO userDAO = new UserDAO();
	
	public User getUser(int userNo) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			User user = userDAO.selectByNo(conn, userNo);
			
			if(user == null) {
				throw new UserNotFoundException();
			}
			
			
			conn.commit();
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
