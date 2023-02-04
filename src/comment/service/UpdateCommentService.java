package comment.service;

import comment.dao.CommentDAO;

public class UpdateCommentService {
	
	CommentDAO commentDAO = new CommentDAO();

	public int updateComment(int commno, String content) {
		
		
		int result = commentDAO.updateComment(commno, content);

		return result;
	}
	
	public int likeComment(int articleNo, int commno) {
		int result = commentDAO.updateLikeComment(articleNo, commno);
		return result;
	}
	
	public int articleCntDown(int articleNo) {
		int result2 = commentDAO.articleCntDown(articleNo);
		return result2;
	}

}
