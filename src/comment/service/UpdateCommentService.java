package comment.service;

import java.sql.Connection;

import comment.dao.CommentDAO;
import comment.model.CommentUpdateList;
import jdbc.conn.ConnectionProvider;

public class UpdateCommentService {
	
	CommentDAO commentDAO = new CommentDAO();

	public int updateComment(int commNo, String content) {
		
		
		int result = commentDAO.updateComment(commNo, content);

		return result;
	}
	
	public int likeComment(int articleNo, int commNo) {
		int result = commentDAO.updateLikeComment(articleNo, commNo);
		return result;
	}
	
	public int articleCntDown(int articleNo) {
		int result2 = commentDAO.articleCntDown(articleNo);
		return result2;
	}
	
	public CommentUpdateList updateList(int updateCommNo) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			CommentUpdateList updateList = commentDAO.updateList(conn,updateCommNo);
			
			return updateList;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

}
