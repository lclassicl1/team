package comment.service;

import comment.dao.CommentDAO;

public class DeleteCommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	
	public int deleteComment(String comm_no) {
		
		int result = commentDAO.deleteComment(comm_no);
		
		
		return result;
	}

}
