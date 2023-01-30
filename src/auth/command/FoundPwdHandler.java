package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.InputNotMatchException;
import Exception.UserNotFoundException;
import auth.model.FoundPwdRequest;
import auth.service.FoundPwdService;
import mvc.command.CommandHandler;

public class FoundPwdHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/loginboard/foundPwdForm.jsp";
	private FoundPwdService foundPwdService = new FoundPwdService();

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
		String inputId = req.getParameter("inputId").trim();
		String inputName = req.getParameter("inputName").trim();
		String inputHp = req.getParameter("inputHp").trim();
		String inputBirth = req.getParameter("inputBirth");
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		empty(errors,inputId,"inputId");
		empty(errors,inputName,"inputName");
		empty(errors,inputHp,"inputHp");
		empty(errors,inputBirth,"inputBirth");
		
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		
		FoundPwdRequest foundPwdReq = new FoundPwdRequest(inputId,inputName,inputHp,inputBirth);
		try {
			String userId = foundPwdService.foundPwd(foundPwdReq);
			req.setAttribute("userId", userId);
			return "/view/loginboard/foundPwdNewPwdForm.jsp";
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			errors.put("UserNot",Boolean.TRUE);
			return FORM_VIEW;
		}catch(InputNotMatchException e) {
			e.printStackTrace();
			errors.put("InputNotMatch",Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}

	private void empty(Map<String,Boolean> errors,String val,String fieldName) {
		if(val == null || val.isEmpty()) {
			errors.put(fieldName,Boolean.TRUE);
		}
	}
}
