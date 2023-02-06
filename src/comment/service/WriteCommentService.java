package comment.service;

import comment.dao.CommentDAO;

public class WriteCommentService {

	
	CommentDAO commentDAO = new CommentDAO();
	
	
	
	
	public int writeComment(int articleNo, String newComment, String userid) {
		
		int cnt = commentDAO.insertComment(articleNo, newComment, userid);
		return cnt;
	}
	
}