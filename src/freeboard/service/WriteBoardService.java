package freeboard.service;

import comment.dao.CommentDAO;
import freeboard.dao.FreeBoardDAO;

public class WriteBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	CommentDAO commentDAO = new CommentDAO();
	public int writetBoard(String title, String content, String categorySearch,String writeId) {

		 int cnt = freeBoardDAO.insertBoard(title, content, categorySearch,writeId);

		return cnt;
	}
	
}