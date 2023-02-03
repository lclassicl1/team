package freeboard.service;

import comment.dao.CommentDAO;
import freeboard.dao.FreeBoardDAO;

public class WriteBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	CommentDAO commentDAO = new CommentDAO();
	public int writetBoard(int articleNo,int userNo,String articleCategory,String categorySearch) 
	{
		 int cnt = freeBoardDAO.insertBoard(articleNo,userNo,articleCategory,categorySearch);
		return cnt;
	}
	
}