package helper.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.PermissionDeniedException;
import auth.model.User;
import helper.service.DeleteHelperService;
import mvc.command.CommandHandler;

public class DeleteHelperHandler implements CommandHandler {

	//private static final String BACK_VIEW = "/helper/list.do";
	private DeleteHelperService deleteHelperService = new DeleteHelperService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int helperNo = Integer.parseInt(noVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		int userNo = user.getUserNo();
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			deleteHelperService.delete(helperNo,userNo);
			return "/view/helperboard/deleteSuccess.jsp";
		}catch(PermissionDeniedException e) {
			errors.put("cantDelete",Boolean.TRUE);
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
	}
}
