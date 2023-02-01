package tradecomment.service;

import java.sql.Connection;
import java.sql.SQLException;

import tradecomment.DAO.CommentDAO;
import tradecomment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class VoltService {

	CommentDAO commentDAO = new CommentDAO();
	
	public void volt(int commNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			commentDAO.volt(conn, commNo);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
