package master.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import auth.DAO.UserDAO;
import auth.model.User;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import master.dao.MasterDAO;
import master.model.UserPage;

public class ListUserService {

	private MasterDAO masterDAO = new MasterDAO();
	private UserDAO userDAO = new UserDAO();
	private int size = 10;
	
	public UserPage getUserPage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = userDAO.selectCountUser(conn);
			
			List<User> userList = masterDAO.selectUser(conn, (pageNum-1)*size, size);
			return new UserPage(total,pageNum,size,userList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
