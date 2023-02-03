package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticlePage;
import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.service.ListBoardService;
import mvc.command.CommandHandler;
import mypage.service.MypageArticleService;

public class MypageArticleHandler implements CommandHandler {

	
	MypageArticleService mypageArticleService = new MypageArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		User user =(User)req.getSession(false).getAttribute("authUser");
		int mypageUserNo = user.getUserNo();
		int pageNum=1;
	
		String pageNoVal = req.getParameter("pageNo");
		if(pageNoVal!=null) {
			pageNum = Integer.parseInt(pageNoVal);
		}
		ArticlePage articlePage = mypageArticleService.getMyapgeArticle(mypageUserNo,pageNum);
		req.setAttribute("articlePage", articlePage);
		
		return "/view/mypage/mypageArticle.jsp";
	}

}


















