package master.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import master.service.NomalUserService;
import mvc.command.CommandHandler;

public class NomalUserHandler implements CommandHandler {

	private NomalUserService nomalUser = new NomalUserService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userNoVal = req.getParameter("no");
		int userNo = Integer.parseInt(userNoVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		
		if(user.getUserGrade()!=999) {
			return "/view/Exception/blackUserCant";
		}
		
		nomalUser.NomalUser(userNo);
		
		return "/master/user/read.do?no="+userNo;
	}

}
