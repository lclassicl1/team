package tradeboard.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import auth.model.User;
import tradeboard.model.Trade;
import tradeboard.model.ModifyRequest;
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
			Trade trade = usertrade.getTrade();
			
			if(!canModify(trade.getUserNo(),user)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			ModifyRequest modReq = new ModifyRequest(user.getUserNo(),trade.getTradeNo(),trade.getTradeTitle()
					,trade.getTradeContent());
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}catch(HelperNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	//작성자와 로그인 정보를 매칭 안했음 (=jsp 파일에서 수정 버튼 안보이게 )
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		String title = req.getParameter("title").trim();
		String content = req.getParameter("content").trim();
		
		ModifyRequest modReq = new ModifyRequest(user.getUserNo(),no,title
				  							,content);
		
		req.setAttribute("modReq", modReq);
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
			modifyTradeService.modify(modReq);
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
