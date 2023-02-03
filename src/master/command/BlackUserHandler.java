package master.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import master.service.BlackUserService;
import mvc.command.CommandHandler;

public class BlackUserHandler implements CommandHandler {

	private BlackUserService blackUser = new BlackUserService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userNoVal = req.getParameter("no");
		int userNo = Integer.parseInt(userNoVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		
		if(user.getUserGrade()!=999) {
			return "/view/Exception/blackUserCant";
		}
		
		blackUser.BlackUser(userNo);
		
		return "/view/master/blackUserSuccess.jsp";
	}

}
