package helper.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.DAO.UserDAO;
import auth.model.User;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriterInfoHelperService {

	private UserDAO userDAO = new UserDAO(); 
	
	public User getUser(int userNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			User user = userDAO.selectByNo(conn, userNo);//글쓴이의 회원 정보 
			
			return user;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
