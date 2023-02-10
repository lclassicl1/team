package master.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticlePage;
import master.service.SearchArticleMasterService;
import mvc.command.CommandHandler;

public class SearchArticleMasterHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/master/listArticleMaster.jsp";
	private SearchArticleMasterService searchArticleService = new SearchArticleMasterService();

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
