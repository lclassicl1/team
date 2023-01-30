package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.FoundNewPwdService;
import mvc.command.CommandHandler;

public class FoundNewPwdHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/loginboard/foundPwdNewPwdForm.jsp";
	private FoundNewPwdService foundNewPwdService = new FoundNewPwdService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String newPwd = req.getParameter("newPwd").trim();
		String newRePwd = req.getParameter("newRePwd").trim();
		String userId = req.getParameter("id").trim();
	
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(newPwd == null || newPwd.isEmpty()) {
			errors.put("newPwd",Boolean.TRUE);
		}
		if(newRePwd == null || newRePwd.isEmpty()) {
			errors.put("newRePwd",Boolean.TRUE);
		}
		if(!newPwd.equals(newRePwd)) {
			errors.put("pwdNotMatch",Boolean.TRUE);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		foundNewPwdService.resetPwd(userId, newPwd);
		
		
		return "/view/loginboard/changePwdSuccess.jsp";
	}

}
