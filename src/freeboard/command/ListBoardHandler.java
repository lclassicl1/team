package freeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreeBoard;
import freeboard.service.ListBoardService;
import mvc.command.CommandHandler;

public class ListBoardHandler implements CommandHandler {
	
	ListBoardService listWriteService = new ListBoardService();

	private static final String FORM_VIEW="/view/freeboard/freeBoardList.jsp";
	
	/* private ListWriteService listWriteService = new ListWriteService(); */
	
	@Override
	public String process(HttpServletRequest request, 
						HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("ListBoardHandler 진입");
		
		 FreeBoard freeBoard = listWriteService.getBoardListAll();
		 System.out.println("freeBoard="+freeBoard);
		 request.setAttribute("freeBoard", freeBoard);
		
		return FORM_VIEW;
	}

}
