package reviewComment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import reviewComment.DAO.CommentDAO;
import reviewComment.model.Comment;
import reviewComment.model.CommentTotal;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListCommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	public CommentTotal getCommentList(int no){
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = commentDAO.commentCount(conn, no);
			
			List<Comment> commentList = commentDAO.select(conn, no);
			return new CommentTotal(commentList,total);
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
