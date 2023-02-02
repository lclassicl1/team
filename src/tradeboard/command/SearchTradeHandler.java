package tradeboard.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tradeboard.model.TradePage;
import tradeboard.service.SearchTradeService;
import mvc.command.CommandHandler;

public class SearchTradeHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/tradeboard/listTrade.jsp";
	private SearchTradeService searchTradeService = new SearchTradeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String input = req.getParameter("input");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		String category = req.getParameter("category");
		
		TradePage tradePage =searchTradeService.search(pageNo,category, input);
		req.setAttribute("tradePage", tradePage);
		
		return FORM_VIEW;
	}

}
