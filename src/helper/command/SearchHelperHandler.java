package helper.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.model.HelperPage;
import helper.service.SearchHelperService;
import mvc.command.CommandHandler;

public class SearchHelperHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/helperboard/listHelper.jsp";
	private SearchHelperService searchHelperService = new SearchHelperService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String category = req.getParameter("category");
		String input = req.getParameter("input");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		System.out.println(category);
		HelperPage helperPage =searchHelperService.search(pageNo, category, input);
		req.setAttribute("helperPage", helperPage);
		
		return FORM_VIEW;
	}

}
