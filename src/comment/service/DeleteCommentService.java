package comment.service;

import comment.dao.CommentDAO;

public class DeleteCommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	
	public int deleteComment(int commNo) {
		
		int result = commentDAO.deleteComment(commNo);
		
		
		return result;
	}

}
