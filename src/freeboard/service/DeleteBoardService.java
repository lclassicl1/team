package freeboard.service;

import Exception.ArticleNullException;
import Exception.UserNoNotMatchException;
import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoardList;

public class DeleteBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	public void delete(int no,int userNo) {
		
		FreeBoardList freeBoard = freeBoardDAO.getArticleNo(no);
		
		if(freeBoard==null) {
			throw new ArticleNullException();
		}
		if(canModify(userNo,freeBoard)) {
			throw new UserNoNotMatchException();
		}
		
		 freeBoardDAO.deleteBoard(no);
		
	}
	private boolean canModify(int loginUserNo,FreeBoardList freeBoard) {
		return loginUserNo!=freeBoard.getUserNo();
}
}