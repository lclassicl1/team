package help.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.model.HelpPage;
import help.service.SearchHelpService;
import mvc.command.CommandHandler;

public class SearchHelpHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/helpboard/listhelp.jsp";
	private SearchHelpService searchHelpService = new SearchHelpService();

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
		HelpPage helpPage =searchHelpService.search(pageNo, category, input);
		req.setAttribute("helpPage", helpPage);
		
		return FORM_VIEW;
	}

}
