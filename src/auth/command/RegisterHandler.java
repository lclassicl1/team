package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class RegisterHandler implements CommandHandler {
	private final static String REGISTERFORM = "/view/register/registerForm.jsp";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("회원가입 핸들러");
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);;
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return REGISTERFORM;
	}



}
