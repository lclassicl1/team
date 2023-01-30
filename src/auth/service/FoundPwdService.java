package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.InputNotMatchException;
import Exception.UserNotFoundException;
import auth.DAO.UserDAO;
import auth.model.FoundPwdRequest;
import auth.model.User;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class FoundPwdService {

	private UserDAO userDAO = new UserDAO();
	
	public String foundPwd(FoundPwdRequest foundPwdReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			
			User user = userDAO.selectById(conn, foundPwdReq.getUserId());
			if(user==null) {
				throw new UserNotFoundException();
			}
			
			if(!match(foundPwdReq, user)) {
				throw new InputNotMatchException();
			}
			return user.getUserId();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private boolean match(FoundPwdRequest foundPwdReq,User user) {
		return user.getUserName().equals(foundPwdReq.getUserName()) && user.getUserHp().equals(foundPwdReq.getUserHp()) && user.getUserBirth().equals(foundPwdReq.getUserBirth());
		
	}
}
