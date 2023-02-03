package mypage.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.PwdMatchFailException;
import auth.model.ChangePwdRequest;
import auth.model.User;
import mvc.command.CommandHandler;
import mypage.service.MypageChangePwService;

public class MypageChangePwdHandler implements CommandHandler {
	
	private static final String FORM_VIEW="/view/mypage/mypageChangePwd.jsp";
	private MypageChangePwService mypageChangePwService = new MypageChangePwService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.sendRedirect(req.getContextPath()+"/view/mypage/mypageChangePwd.jsp");
		return null;
	}
	
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String nowPwd = req.getParameter("nowPwd");
		String newPwd = req.getParameter("newPwd");
		String newRePwd = req.getParameter("newRePwd");
		
		User user = (User)req.getSession().getAttribute("authUser");//현재 로그인 한 정보
		ChangePwdRequest changePwdReq = new ChangePwdRequest(user.getUserId(),nowPwd,newPwd);
		
		
		Map<String ,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		empty(errors,nowPwd,"nowPwd");
		empty(errors,newPwd,"newPwd");
		empty(errors,newRePwd,"newRePwd");
		
		if(!newPwd.equals(newRePwd)) {
			errors.put("newPwdRePwdMatch",Boolean.TRUE);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try{
			mypageChangePwService.changePwd(changePwdReq);
			return "/view/mypage/mypage.jsp";
		}catch(PwdMatchFailException e) {
			errors.put("pwdMatchFail",Boolean.TRUE);
			e.printStackTrace();
			return FORM_VIEW;
		}
	}

	private void empty(Map<String ,Boolean> errors,String value,String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName,Boolean.TRUE);
		}
	}
}
