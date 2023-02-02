package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.service.ListBoardService;
import mvc.command.CommandHandler;
import mypage.service.MypageArticleService;

public class MypageArticleHandler implements CommandHandler {

	
	MypageArticleService mypageArticleService = new MypageArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String input = req.getParameter("input");
		User user =(User)req.getSession(false).getAttribute("authUser");
		String mypageUserName = user.getUserName();
		
		FreeBoard freeBoard = mypageArticleService.getMyapgeArticle(mypageUserName,input);
		System.out.println("freeBoard======"+freeBoard);
		req.setAttribute("freeBoard", freeBoard);
		
		return "/view/mypage/mypageArticleSearch.jsp";
	}

}


















