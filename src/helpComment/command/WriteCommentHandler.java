package helpComment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import helpComment.model.WriteCommentRequest;
import helpComment.service.WriteCommentService;
import mvc.command.CommandHandler;

public class WriteCommentHandler implements CommandHandler {

	private static final String FORM_VIEW= "/view/helpboard/readhelp.jsp";
	private WriteCommentService writeCommentService = new WriteCommentService();
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
		User user = (User)req.getSession(false).getAttribute("authUser");
		String loginId = user.getUserId();
		String content = req.getParameter("content");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(content == null || content.isEmpty()) {
			errors.put("contentEmpty",Boolean.TRUE);
		}
		
		if(!errors.isEmpty()) {
			return "/help/read.do?no="+no;
		}
		WriteCommentRequest wirteCommReq = new WriteCommentRequest(no,loginId,content);
		
		writeCommentService.writeComm(wirteCommReq);
		return "/view/helpboard/writeCommentSuccess.jsp";
	}

}
