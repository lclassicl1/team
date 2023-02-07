package freeboard.service;

import freeboard.dao.FreeBoardDAO;

public class DeleteBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	public int delete(String no) {
		
		
		 int result = freeBoardDAO.deleteBoard(no);
		
		return result;
	}
}