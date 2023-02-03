package master.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.model.UserPage;
import master.service.SearchUserService;
import mvc.command.CommandHandler;

public class SearchUserHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/master/listUser.jsp";
	private SearchUserService searchUserService = new SearchUserService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String input = req.getParameter("input");
		String pageNoVal = req.getParameter("pageNo");
		String gradeVal = req.getParameter("grade");
		
		int grade = Integer.parseInt(gradeVal);
			int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		UserPage userPage =searchUserService.search(pageNo, input,grade);
		req.setAttribute("userPage", userPage);
		
		return FORM_VIEW;
	}

}
