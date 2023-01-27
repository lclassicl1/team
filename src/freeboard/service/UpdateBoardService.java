package freeboard.service;

import freeboard.dao.FreeBoardDAO;

public class UpdateBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	public int update(String no, String title, String content, String free_category) {
		System.out.println("update서비스 진입");
		
		
		 int result = freeBoardDAO.updateBoard(no, title, content, free_category);
		
		return result;
	}
}