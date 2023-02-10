package helper.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import article.model.ModifyRequest;
import auth.model.User;
import helper.model.UserInfoHelperInfo;
import helper.service.ModifyHelperService;
import helper.service.ReadHelperService;
import mvc.command.CommandHandler;

public class ModifyHelperHandler implements CommandHandler {
	
	private static final String FORM_VIEW="/view/helperboard/modifyForm.jsp";
	ModifyHelperService modifyHelperService = new ModifyHelperService();
	ReadHelperService readHelperService = new ReadHelperService();

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

		try {
			
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			User user = (User)req.getSession(false).getAttribute("authUser");
			UserInfoHelperInfo userHelper = readHelperService.getHelper(no, false);
			
			if(!canModify(userHelper.getHelper().getUserNo(),user)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			req.setAttribute("helper", userHelper);
			return FORM_VIEW;
		}catch(HelperNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	//작성자와 로그인 정보를 매칭 안했음 (= 파일에서 수정 버튼 안보이게 )
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		String title = req.getParameter("title").trim();
		String content = req.getParameter("content").trim();
		String category = req.getParameter("helperCategory");
		
		ModifyRequest modReq = new ModifyRequest(user.getUserNo(),no,title
				  							,content);
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(title==null||title.isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
		if(content==null||content.isEmpty()) {
			errors.put("content",Boolean.TRUE);
		}
		
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			modifyHelperService.modify(modReq,category);
			return "/helper/read.do?no="+no;
		}catch(HelperNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		
	}

	private boolean canModify(int loginNo,User user) {
		int writer = user.getUserNo();
		return writer == loginNo;
	}
}
