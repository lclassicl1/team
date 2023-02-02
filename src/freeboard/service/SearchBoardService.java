package freeboard.service;

import java.util.List;

import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;

public class SearchBoardService {

	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	public FreeBoard search(String categorySearch,String input) {
		
	List<FreeBoardList> list = freeBoardDAO.searchBoard(categorySearch,input);
	
		return new FreeBoard(list);
	}
}
