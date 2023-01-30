package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.ModifyUserInfoRequest;
import auth.model.User;
import auth.service.ModifyUserInfoService;
import mvc.command.CommandHandler;

public class ModifyUserInfoHandler implements CommandHandler {

	private static final String FORM_VIEW="/view/loginboard/modifyUserInfoForm.jsp";
	ModifyUserInfoService modifyUserInfoService = new ModifyUserInfoService();
	
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
			
			User user = (User)req.getSession(false).getAttribute("authUser");
			req.setAttribute("userInfo", user);//로그인한 유저 정보
			
			String userEmail = user.getUserEmail();
			String[] array = userEmail.split("@");
			
			req.setAttribute("userEmailId", array[0]);//로그인한 유저의 기존 이메일 아이디 
			req.setAttribute("userEmailAddress", array[1]);//로그인한 유저의 기존 이메일 주소 
			
			return FORM_VIEW;
		}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String userHp = req.getParameter("userHp").trim();
		String userAddress = req.getParameter("userAddress").trim();
		String userEmailId = req.getParameter("userEmailId").trim();
		String userEmailAddress = req.getParameter("userEmailAddress").trim();
		String userEmail = userEmailId+"@"+userEmailAddress;
		String userSkill = req.getParameter("userSkill");
		String userSchool = req.getParameter("userSchool");
		
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		System.out.println(user);
		int loginNo = user.getUserNo();
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		empty(errors,userHp,"userHp");
		empty(errors,userAddress,"userAddress");
		//empty(errors,userEmail,"userEmail");
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		ModifyUserInfoRequest modiUserInfoReq = new ModifyUserInfoRequest(loginNo,userHp,userAddress,userEmail,userSkill,userSchool);
		
		modifyUserInfoService.modiUserInfo(modiUserInfoReq);
		return "/view/loginboard/modifyUserInfoSuccess.jsp";
	}

	private void empty(Map<String,Boolean> errors,String value,String fieldName) {
		if(value==null||value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
	}
}
