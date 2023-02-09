package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.UserNoNotMatchException;
import auth.model.User;
import auth.service.UnregisterService;
import mvc.command.CommandHandler;

public class UnregisterHandler implements CommandHandler {

	private UnregisterService unregister = new UnregisterService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String userNoVal = req.getParameter("userNo");
		int userNo = Integer.parseInt(userNoVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		if(user.getUserNo()!=userNo) {
			throw new UserNoNotMatchException();
		}
		unregister.unregister(userNo);
		
		return "/view/mypage/unRegister.jsp";
	}

}
