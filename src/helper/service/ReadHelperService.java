package helper.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import auth.DAO.UserDAO;
import auth.model.User;
import helper.dao.HelperDAO;
import helper.model.Helper;
import helper.model.UserInfoHelperInfo;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ReadHelperService {

	HelperDAO helperDAO = new HelperDAO();
	UserDAO userDAO = new UserDAO();
	
	public UserInfoHelperInfo getHelper(int articleNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Helper helper = helperDAO.selectByNo(conn, articleNo);
			
			if(helper == null) {
				throw new HelperNotFoundException();
			}
			
			int writerNo = helper.getUserNo();
			User user = userDAO.selectByNo(conn, writerNo);
			
			
			if(incrementReadCnt) {
				helperDAO.incrementReadCnt(conn, articleNo);
			}
			
			UserInfoHelperInfo userHelper = new UserInfoHelperInfo(user,helper);
			
			conn.commit();
			return userHelper;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
