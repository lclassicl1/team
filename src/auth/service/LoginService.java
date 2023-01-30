package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.LoginFailException;
import Exception.UserNotFoundException;
import auth.DAO.UserDAO;
import auth.model.User;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class LoginService {

	UserDAO userDAO = new UserDAO();
	public User login(String loginId,String loginPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			User user = userDAO.selectById(conn, loginId);
			if(user==null) {
				throw new UserNotFoundException();
			}
			if(!matchPwd(loginPwd,user)) {
				throw new LoginFailException();
			}
			conn.commit();
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private boolean matchPwd(String loginPwd,User user) {
		return user.getUserPwd().equals(loginPwd);
	}
}
