package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DuplicatedIdException;
import auth.model.JoinRequest;
import auth.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/loginboard/joinForm.jsp";
	JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
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
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		String userId = req.getParameter("userId".trim());
		String userPwd = req.getParameter("userPwd").trim();
		String userRePwd = req.getParameter("userRePwd").trim();
		String userName = req.getParameter("userName").trim();
		String userHp = req.getParameter("userHp").trim();
		String userAddress = req.getParameter("userAddress").trim();
		String EmailId = req.getParameter("emailId").trim();
		String EmailAdress = req.getParameter("emailAddress").trim();
		String userEmail = EmailId+"@"+EmailAdress;
		String userGender = req.getParameter("userGender");
		String userSkill = req.getParameter("userSkill");
		String userSchool = req.getParameter("userSchool");
		String userBirth = req.getParameter("userBirth");
		
		if(userGender.equals("없음")) {
			errors.put("userGender",Boolean.TRUE);
		}
		
		JoinRequest joinReq = new JoinRequest(userId,userPwd,userRePwd,userName,userHp,userAddress,userEmail,userGender,userSkill,userSchool,userBirth);
		
		
		joinReq.validdate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			joinService.join(joinReq);
			return "/view/loginboard/joinSuccess.jsp";
		}catch(DuplicatedIdException e) {
			errors.put("duplicateId",Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
