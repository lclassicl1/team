package tradeboard.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import auth.model.User;
import article.model.ModifyRequest;
import tradeboard.model.UserInfoTradeInfo;
import tradeboard.service.ModifyTradeService;
import tradeboard.service.ReadTradeService;
import mvc.command.CommandHandler;

public class ModifyTradeHandler implements CommandHandler {
	
	private static final String FORM_VIEW="/view/tradeboard/modifyForm.jsp";
	ModifyTradeService modifyTradeService = new ModifyTradeService();
	ReadTradeService readTradeService = new ReadTradeService();

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
			UserInfoTradeInfo usertrade = readTradeService.getTrade(no, false);
			
			if(!canModify(usertrade.getArticle().getUserNo(),user)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			req.setAttribute("trade", usertrade);
			return FORM_VIEW;
		}catch(HelperNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		String title = req.getParameter("title").trim();
		String content = req.getParameter("content").trim();
		String category = req.getParameter("category");
		
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
			modifyTradeService.modify(modReq,category);
			return "/view/tradeboard/modifySuccess.jsp"; // 수정 성공 후 출력 페이지 
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
