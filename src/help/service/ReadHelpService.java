package help.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
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
	
	public UserInfoHelpInfo getHelp(int helpNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Help help = helpDAO.selectByNo(conn, helpNo);
			
			if(help == null) {
				throw new HelperNotFoundException();
			}
			
			int writerNo = help.getUserNo();
			User user = userDAO.selectByNo(conn, writerNo);
			
			
			if(incrementReadCnt) {
				helpDAO.incrementReadCnt(conn, helpNo);
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
