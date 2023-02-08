package master.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import master.dao.MasterDAO;

public class NomalUserService {

	private MasterDAO masterDAO = new MasterDAO();
	
	public void NomalUser(int userNo){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			masterDAO.nomalUser(conn, userNo);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
