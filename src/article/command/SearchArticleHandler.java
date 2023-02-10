package article.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticlePage;
import article.service.SearchArticleService;
import mvc.command.CommandHandler;

public class SearchArticleHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/articleboard/listArticle.jsp";
	private SearchArticleService searchArticleService = new SearchArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String input = req.getParameter("input");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		ArticlePage articlePage =searchArticleService.search(pageNo, input);
		req.setAttribute("articlePage", articlePage);
		
		return FORM_VIEW;
	}

}
