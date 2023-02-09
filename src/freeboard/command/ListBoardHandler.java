package freeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.model.FreePage;
import freeboard.service.ListBoardService;
import mvc.command.CommandHandler;

public class ListBoardHandler implements CommandHandler {
	
	ListBoardService listWriteService = new ListBoardService();
	private static final String FORM_VIEW="/view/freeboard/freeBoardList.jsp";
	
	@Override
	public String process(HttpServletRequest request, 
						HttpServletResponse response) throws Exception {
		
		String pageNoVal = request.getParameter("pageNo");
		int pageNum=1;
		if(pageNoVal!=null) {
			pageNum= Integer.parseInt(pageNoVal);
		}
		
		 FreePage freePage = listWriteService.getBoardListAll(pageNum);
		 request.setAttribute("freePage", freePage);
		
		 User user = (User)request.getSession(false).getAttribute("authUser");
		 HttpSession ssesion=request.getSession();
		 ssesion.setAttribute("userInfo", user);//로그인한 유저 정보
		 
		 
		 
		return FORM_VIEW;
	}

}
