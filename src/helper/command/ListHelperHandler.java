package helper.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.model.HelperPage;
import helper.service.ListHelperService;
import mvc.command.CommandHandler;

public class ListHelperHandler implements CommandHandler {

	ListHelperService listHelperService = new ListHelperService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		HelperPage helperPage =listHelperService.getHelperPage(pageNo);
		req.setAttribute("helperPage", helperPage);
		
		return "/view/helperboard/listHelper.jsp";
	}

}
