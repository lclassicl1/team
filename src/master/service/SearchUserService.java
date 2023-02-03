package master.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import auth.DAO.UserDAO;
import auth.model.User;
import master.dao.MasterDAO;
import master.model.UserPage;
import master.model.SearchUser;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SearchUserService {

	MasterDAO masterDAO = new MasterDAO();
	UserDAO userDAO = new UserDAO();
	private int size = 10;
	
	public UserPage search(int pageNum, String input,int grade){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = userDAO.selectCountUser(conn);
			
			SearchUser search = new SearchUser((pageNum-1)*size,size,input);
			List<User> userList = null;
			
			if(grade == 0) {
				userList = masterDAO.searchUser(conn, search);
			}else {
				userList = masterDAO.searchUser(conn, search,grade);
			}
			
			
			
			return new UserPage(total,pageNum,size,userList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
