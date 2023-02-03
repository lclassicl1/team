package master.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticlePage;
import master.service.ListArticleUserService;
import mvc.command.CommandHandler;

public class ListArticleUserHandler implements CommandHandler {

	ListArticleUserService listArticle = new ListArticleUserService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		String userNoVal = req.getParameter("userNo");
		int userNo = Integer.parseInt(userNoVal);
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ArticlePage articlePage =listArticle.getArticlePage(pageNo,userNo);
		req.setAttribute("articlePage", articlePage);
		
		return "/view/master/listArticleMaster.jsp";
	}

}
