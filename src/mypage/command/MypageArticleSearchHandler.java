package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.service.SearchBoardService;
import mvc.command.CommandHandler;

public class MypageArticleSearchHandler implements CommandHandler {

	
	SearchBoardService searchBoardService = new SearchBoardService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String categorySearch = request.getParameter("categorySearch");
		String input = request.getParameter("input");
		
		User user =(User)request.getSession(false).getAttribute("authUser");
		String loginName=user.getUserName();
		
		FreeBoard freeBoard = searchBoardService.mypageSearch(categorySearch,input,loginName);
		request.setAttribute("freeBoard", freeBoard);
		
		
		return "/view/mypage/mypageArticleSearch.jsp";
	}
}
