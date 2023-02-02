package helpComment.service;

import java.sql.Connection;
import java.sql.SQLException;

import helpComment.DAO.CommentDAO;
import helpComment.model.WriteCommentRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteCommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	public void writeComm(WriteCommentRequest writeCommReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1");
			commentDAO.insert(conn, writeCommReq);
			System.out.println("1");
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
