package help.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.PermissionDeniedException;
import auth.model.User;
import help.service.DeleteHelpService;
import mvc.command.CommandHandler;

public class DeleteHelpHandler implements CommandHandler {

	//private static final String BACK_VIEW = "/help/list.do";
	private DeleteHelpService deletehelpService = new DeleteHelpService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int helpNo = Integer.parseInt(noVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		int userNo = user.getUserNo();
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			deletehelpService.delete(helpNo,userNo);
			return "/view/helpboard/deleteSuccess.jsp";
		}catch(PermissionDeniedException e) {
			errors.put("cantDelete",Boolean.TRUE);
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		
//		if(req.getMethod().equalsIgnoreCase("GET")) {
//			return processForm(req,res);
//		}else if (req.getMethod().equalsIgnoreCase("POST")) {
//			return processSubmit(req,res);
//		}else {
//			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//			return null;
//		}
	}
	
//	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
//		return BACK_VIEW;
//	}
//
//	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
//		String noVal = req.getParameter("no");
//		int no = Integer.parseInt(noVal);
//		
//		deletehelpService.delete(no);
//		return "/view/help/deleteSuccess.jsp";
//	}


}
