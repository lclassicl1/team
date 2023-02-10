package mypage.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.model.ModifyUserInfoRequest;
import auth.model.User;
import auth.service.LoginService;
import mvc.command.CommandHandler;
import mypage.service.UpdateMypageService;


//마이페이지 - 회원 정보 변경 핸들러
public class MypageUpdateHandler implements CommandHandler {

	UpdateMypageService updateMypageService  = new UpdateMypageService();
	private static final String FORM_VIEW="/view/mypage/mypageUpdate.jsp";
	private LoginService loginService = new LoginService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
			return null;
		}
	
		//회원 정보 수정 페이지로 가는 메서드
		private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
			res.setCharacterEncoding("UTF-8");
			
			String userId = req.getParameter("userId");
			
			
			User user = (User)req.getSession(false).getAttribute("authUser");
			HttpSession ssesion=req.getSession();
			ssesion.setAttribute("userInfo", user);//로그인한 유저 정보
			
			
			String userEmail = user.getUserEmail();
			String[] array = userEmail.split("@");
			
			req.setAttribute("userInfo", user);//로그인한 유저 정보
			req.setAttribute("userEmailId", array[0]);//로그인한 유저의 기존 이메일 아이디 
			req.setAttribute("userEmailAddress", array[1]);//로그인한 유저의 기존 이메일 주소 
			
			res.sendRedirect(req.getContextPath()+"/view/mypage/mypageUpdate.jsp");
			return null;
		}
		
		//회원 정보 수정 입력 메서드
		private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
			String userHp = req.getParameter("userHp").trim();
			String userAddress = req.getParameter("userAddress").trim();
			String userEmailId = req.getParameter("userEmailId").trim();
			String userEmailAddress = req.getParameter("userEmailAddress").trim();
			String userEmail = userEmailId+"@"+userEmailAddress;
			String userSkill = req.getParameter("userSkill");
			String userSchool = req.getParameter("userSchool");
			
			
			User user = (User)req.getSession(false).getAttribute("authUser");
			int loginNo = user.getUserNo();
			
			String id = user.getUserId();
			String pwd = user.getUserPwd();
			
			Map<String,Boolean> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			
			empty(errors,userHp,"userHp");
			empty(errors,userAddress,"userAddress");
			
			
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
			
			
			ModifyUserInfoRequest modiUserInfoReq = new ModifyUserInfoRequest(loginNo,userHp,userAddress,userEmail,userSkill,userSchool);
			
			updateMypageService.updateMypage(modiUserInfoReq);
			
			HttpSession session = req.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			
			User modiUser = loginService.login(id, pwd);
			req.getSession().setAttribute("authUser",modiUser);
			
			return "/mypage.do";
			}
		
			private void empty(Map<String,Boolean> errors,String value,String fieldName) {
				if(value==null||value.isEmpty()) {
					errors.put(fieldName, Boolean.TRUE);
				}
			}


}
