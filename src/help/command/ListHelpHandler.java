package help.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.model.HelpPage;
import help.service.ListHelpService;
import mvc.command.CommandHandler;

public class ListHelpHandler implements CommandHandler {

	ListHelpService listhelpService = new ListHelpService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		HelpPage helpPage =listhelpService.getHelpPage(pageNo);
		req.setAttribute("helpPage", helpPage);
		
		return "/view/helpboard/listHelp.jsp";
	}

}
