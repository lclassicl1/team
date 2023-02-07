package freeboard.service;

import freeboard.dao.FreeBoardDAO;

public class UpdateBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	public int update(String no, String title, String content, String freeCategory) {
		
		
		 int result = freeBoardDAO.updateBoard(no, title, content, freeCategory);
		
		return result;
	}
}