package freeboard.service;


import java.util.List;

import comment.dao.CommentDAO;
import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;

public class ReadBoardService {

	private FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	CommentDAO commantDAO = new CommentDAO();
	
	public FreeBoard getBoardDetail(int no) {
		System.out.println("서비스 진입");
	

			System.out.println("db연결 확인");
		freeBoardDAO.updateCnt(no);
		List<FreeBoardList> list = freeBoardDAO.readDetail(no);
		System.out.println("서비스 read"+list);
		
		
		return new FreeBoard(list);
	}

}
