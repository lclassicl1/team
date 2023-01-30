package helpComment.service;

import java.sql.Connection;
import java.sql.SQLException;

import helpComment.DAO.CommentDAO;
import jdbc.conn.ConnectionProvider;

public class ConnService {

	CommentDAO commentDAO = new CommentDAO();
	
	public void conn(int commNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			commentDAO.conn(conn, commNo);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
}
