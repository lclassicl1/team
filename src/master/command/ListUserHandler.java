package master.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.model.UserPage;
import master.service.ListUserService;
import mvc.command.CommandHandler;

public class ListUserHandler implements CommandHandler {

	ListUserService listUserService = new ListUserService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		UserPage userPage =listUserService.getUserPage(pageNo);
		req.setAttribute("userPage", userPage);
		
		return "/view/master/listUser.jsp";
	}

}
