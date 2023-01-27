package comment.service;

import comment.dao.CommentDAO;

public class UpdateCommentService {
	
	CommentDAO commentDAO = new CommentDAO();

	public int updateComment(int commno, String content) {
		
		
		int result = commentDAO.updateComment(commno, content);

		return result;
	}

}
