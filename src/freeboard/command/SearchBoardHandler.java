package freeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreeBoard;
import freeboard.service.SearchBoardService;
import mvc.command.CommandHandler;

public class SearchBoardHandler implements CommandHandler {

	
	SearchBoardService searchBoardService = new SearchBoardService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SearchBoardHandler-==========진입");
		String categorySearch = request.getParameter("categorySearch");
		
		
		FreeBoard freeBoard = searchBoardService.search(categorySearch);
		request.setAttribute("freeBoard", freeBoard);
		
		
		return "/view/freeboard/freeBoardSearch.jsp";
	}

}
