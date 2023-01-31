package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.PwdMatchFailException;
import auth.DAO.UserDAO;
import auth.model.ChangePwdRequest;
import auth.model.User;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class MypageChangePwService {
	UserDAO userDAO = new UserDAO();
	public void changePwd(ChangePwdRequest changePwdReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			User user = userDAO.selectById(conn, changePwdReq.getLoginId());
			
			if(!loginPwd(changePwdReq.getNowPwd(),user)) {
				throw new PwdMatchFailException();
			}
			
			userDAO.update(conn, changePwdReq);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private boolean loginPwd(String inputPwd,User user) {
		return user.getUserPwd().equals(inputPwd);
	}
}
