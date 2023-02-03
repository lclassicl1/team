package freeboard.service;


import java.util.List;

import comment.dao.CommentDAO;
import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoardList;
import freeboard.model.FreePage;

public class ReadBoardService {

	private FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	CommentDAO commantDAO = new CommentDAO();
	
	public FreePage getBoardDetail(int mypageNo) {
		System.out.println("서비스 진입");
	

		freeBoardDAO.updateCnt(mypageNo);
		List<FreeBoardList> list = freeBoardDAO.readDetail(mypageNo);
		System.out.println("서비스 read"+list);
		
		
		return new FreePage(list);
	}

}
