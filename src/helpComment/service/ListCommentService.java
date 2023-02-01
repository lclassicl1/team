package helpComment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import helpComment.DAO.CommentDAO;
import helpComment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListCommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	public List<Comment> getCommentList(int no){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
//			List<Comment> commentList = commentDAO.select(conn, no);
			return commentDAO.select(conn, no);
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
