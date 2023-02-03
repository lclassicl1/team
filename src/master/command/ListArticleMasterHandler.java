package master.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticlePage;
import master.service.ListArticleMasterService;
import mvc.command.CommandHandler;

public class ListArticleMasterHandler implements CommandHandler {

	ListArticleMasterService listArticle = new ListArticleMasterService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ArticlePage articlePage =listArticle.getArticlePage(pageNo);
		req.setAttribute("articlePage", articlePage);
		
		return "/view/master/listArticleMaster.jsp";
	}

}
