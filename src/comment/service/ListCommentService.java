package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import comment.dao.CommentDAO;
import comment.model.Comment;
import comment.model.CommentList;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListCommentService {
	
	private CommentDAO commentDAO = new CommentDAO();
	
	
	public CommentList getCommentList(int no) {
		
		CommentList commentList = commentDAO.selectComment(no);

		return commentList;
	}
	public Comment getCommentAll(int no) {
		
		List<CommentList> commentList= commentDAO.selectAllComment(no);
		Comment comment = new Comment(commentList);

		return comment;
	}
	
	public int getCommentCount(int no) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = commentDAO.commentCount(conn, no);
			return total;
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
