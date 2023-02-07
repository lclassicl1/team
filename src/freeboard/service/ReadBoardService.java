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
	

		freeBoardDAO.updateCnt(mypageNo);
		List<FreeBoardList> list = freeBoardDAO.readDetail(mypageNo);
		
		
		return new FreePage(list);
	}

}
