package help.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.UserNotFoundException;
import auth.DAO.UserDAO;
import auth.model.User;
import help.dao.HelpDAO;
import help.model.Help;
import help.model.UserInfoHelpInfo;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ReadHelpService {

	HelpDAO helpDAO = new HelpDAO();
	UserDAO userDAO = new UserDAO();
	
	public UserInfoHelpInfo getHelp(int articleNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Help help = helpDAO.selectByNo(conn, articleNo);
			
			if(help == null) {
				throw new HelperNotFoundException();
			}
			int writerUserNo = help.getUserNo();
			User user = userDAO.selectByNo(conn, writerUserNo);
			
			if(user == null) {
				throw new UserNotFoundException();
			}
			
			if(incrementReadCnt) {
				helpDAO.incrementReadCnt(conn, articleNo);
			}
			UserInfoHelpInfo userHelp = new UserInfoHelpInfo(user,help);
			
			conn.commit();
			return userHelp;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
