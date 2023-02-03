package comment.service;

import comment.dao.CommentDAO;

public class WriteCommentService {

	
	CommentDAO commentDAO = new CommentDAO();
	
	
	
	
	public int writeComment(int article_no, String comm_content, String userid) {
		
		int cnt = commentDAO.insertComment(article_no, comm_content, userid);
		return cnt;
	}
	
}