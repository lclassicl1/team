package freeboard.service;

import java.util.List;

import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;

public class SearchBoardService {

	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	public FreeBoard search(String categorySearch) {
		
	List<FreeBoardList> list = freeBoardDAO.searchBoard(categorySearch);
	
		return new FreeBoard(list);
	}
}
