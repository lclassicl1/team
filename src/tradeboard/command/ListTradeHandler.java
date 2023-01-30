package tradeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tradeboard.model.TradePage;
import tradeboard.service.ListTradeService;
import mvc.command.CommandHandler;

public class ListTradeHandler implements CommandHandler {

	ListTradeService listtradeService = new ListTradeService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		TradePage tradePage =listtradeService.getTradePage(pageNo);
		req.setAttribute("tradePage", tradePage);
		
		return "/view/tradeboard/listTrade.jsp";
	}

}
