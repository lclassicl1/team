package freeboard.service;

import freeboard.dao.FreeBoardDAO;

public class DeleteBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	public int delete(String no) {
		System.out.println("delete서비스 진입");
		
		
		 int result = freeBoardDAO.deleteBoard(no);
		
		return result;
	}
}