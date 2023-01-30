package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.DuplicatedIdException;
import auth.DAO.UserDAO;
import auth.model.JoinRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class JoinService {

	UserDAO userDAO = new UserDAO();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			userDAO.join(conn, joinReq);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new DuplicatedIdException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
