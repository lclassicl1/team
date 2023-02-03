package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.BlackUserException;
import Exception.LoginFailException;
import Exception.UserNotFoundException;
import auth.model.User;
import auth.service.LoginService;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {

	private static final String FORM_VIEW = "/index.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);;
			return null;
		}
	} 
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return FORM_VIEW;  
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	String loginId = trim(req.getParameter("user_id"));
	String loginPwd = trim(req.getParameter("user_pwd"));
	
	Map<String,Boolean> errors = new HashMap<>();
	req.setAttribute("errors", errors);
	
	if(loginId==null || loginId.isEmpty())	{
		errors.put("loginId",Boolean.TRUE);
	}
	if(loginPwd == null || loginPwd.isEmpty()) {
		errors.put("loginPwd",Boolean.TRUE);
	}
	if(!errors.isEmpty()) {
		return FORM_VIEW;
	}
	
	try {
		User user = loginService.login(loginId, loginPwd);
		req.getSession().setAttribute("authUser", user);
		res.sendRedirect(req.getContextPath()+"/view/loginboard/loginView.jsp");
		return null;
	}catch(UserNotFoundException e) {
		errors.put("userNotFound",Boolean.TRUE);
		e.printStackTrace();
		return FORM_VIEW;
	}catch(LoginFailException e) {
		errors.put("idPwdNotMatch",Boolean.TRUE);
		e.printStackTrace();
		return FORM_VIEW;
	}catch(BlackUserException e) {
		res.sendRedirect(req.getContextPath()+"/view/Exception/blackLogin.jsp");
		return null;
	}
}

private String trim(String str) {
	return (str==null) ? null:str.trim();
}

}
