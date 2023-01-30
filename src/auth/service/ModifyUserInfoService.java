package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.DAO.UserDAO;
import auth.model.ModifyUserInfoRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyUserInfoService {

	UserDAO userDAO = new UserDAO();
	
	public void modiUserInfo(ModifyUserInfoRequest modiUserInfoReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			userDAO.modifyUser(conn, modiUserInfoReq);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
