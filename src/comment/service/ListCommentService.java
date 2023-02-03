package comment.service;

import java.util.List;

import comment.dao.CommentDAO;
import comment.model.Comment;
import comment.model.CommentList;

public class ListCommentService {
	
	private CommentDAO commentDAO = new CommentDAO();
	
	
	public CommentList getCommentList(int no) {
		
		CommentList commentList 
			= commentDAO.selectComment(no);

		return commentList;
	}
	public Comment getCommentAll(int no) {
		
		List<CommentList> commentList= commentDAO.selectAllComment(no);
		Comment comment = new Comment(commentList);

		return comment;
	}
	
	
	
}
