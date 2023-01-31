package comment.service;

import comment.dao.CommentDAO;
import freeboard.dao.FreeBoardDAO;

public class WriteCommentService {

	
	CommentDAO commentDAO = new CommentDAO();
	
	
	
	
	public int writeComment(int article_no, String comm_content, String userid) {
		
		System.out.println("12312312312123");
		int cnt = commentDAO.insertComment(article_no, comm_content, userid);
		System.out.println("1231231231123dfgdgfdg");
		return cnt;
	}
	
}