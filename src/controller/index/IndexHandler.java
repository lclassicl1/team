package controller.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class IndexHandler implements CommandHandler {
	private final static String INDEX_VIEW = "/index.jsp";
	private final static String MAIN_VIEW = "/view/main/main.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			System.out.println("index 핸들러 get방식");
			return INDEX_VIEW;
		} else if(request.getMethod().equalsIgnoreCase("post")) {
			System.out.println("index 핸들러 post방식");
			return processSubmit(request, response);
		} else {
			return null;
		}
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("user_id");
		String userpwd =  request.getParameter("user_pwd");
		System.out.println(userid);
		System.out.println(userpwd);
		
		
		return "/view/main/main.jsp";
	}

}
