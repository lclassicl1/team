package master.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import auth.model.User;
import master.service.ReadUserService;
import mvc.command.CommandHandler;

public class ReadUserHandler implements CommandHandler {

	ReadUserService readUserService = new ReadUserService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		try {
			User user = readUserService.getUser(no);
			
			req.setAttribute("user", user); 
			
			return "/view/master/readUser.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no user", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
