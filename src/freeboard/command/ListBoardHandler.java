package freeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.service.ListBoardService;
import mvc.command.CommandHandler;

public class ListBoardHandler implements CommandHandler {
	
	ListBoardService listWriteService = new ListBoardService();
	private static final String FORM_VIEW="/view/freeboard/freeBoardList.jsp";
	
	@Override
	public String process(HttpServletRequest request, 
						HttpServletResponse response) throws Exception {
		System.out.println("ListBoardHandler 진입");
		
		 FreeBoard freeBoard = listWriteService.getBoardListAll();
		 request.setAttribute("freeBoard", freeBoard);
		
		 User user = (User)request.getSession(false).getAttribute("authUser");
		String writeId = user.getUserId();
		HttpSession ssesion=request.getSession();
		ssesion.setAttribute("userInfo", user);//로그인한 유저 정보
		 
		 
		 
		return FORM_VIEW;
	}

}
