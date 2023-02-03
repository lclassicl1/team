package reviewComment.service;

import java.sql.Connection;
import java.sql.SQLException;

import reviewComment.DAO.CommentDAO;
import reviewComment.model.WriteCommentRequest;
import jdbc.conn.ConnectionProvider;

public class WriteCommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	public void writeComm(WriteCommentRequest writeCommReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			commentDAO.insert(conn, writeCommReq);
			
			conn.commit();
		}catch(SQLException e) {
			
		}finally {
			
		}
	}
}
