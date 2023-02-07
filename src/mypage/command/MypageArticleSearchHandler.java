package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticlePage;
import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.service.SearchBoardService;
import mvc.command.CommandHandler;
import mypage.service.MypageArticleSearchService;

public class MypageArticleSearchHandler implements CommandHandler {

	
	SearchBoardService searchBoardService = new SearchBoardService();
	MypageArticleSearchService mypageArticleSearchService = new MypageArticleSearchService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String categorySearch = request.getParameter("categorySearch");
		String input = request.getParameter("input");
		String pageNumVal = request.getParameter("pageNum");
		
		User user =(User)request.getSession(false).getAttribute("authUser");
		int loginNo=user.getUserNo();
		int pageNum = 1;
		
		if(pageNumVal!=null) {
			pageNum = Integer.parseInt(pageNumVal);
		}
		
		
		ArticlePage articlePage 
		= mypageArticleSearchService.getMyapgeArticleCategory(loginNo,pageNum,categorySearch,input);
		
		request.setAttribute("articlePage", articlePage);
		
		
		return "/view/mypage/mypageArticleSearch.jsp";
	}
}
