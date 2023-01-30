package helpComment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.CantModiException;
import Exception.CommentNotFoundException;
import auth.model.User;
import helpComment.model.Comment;
import helpComment.model.ModifyCommRequest;
import helpComment.service.ModifyCommentService;
import mvc.command.CommandHandler;

public class ModifyCommentHandler implements CommandHandler {

	private ModifyCommentService modifyCommentService = new ModifyCommentService();
	private static final String FORM_VIEW = "/view/helpboard/modifyCommForm.jsp";
	
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
			
			String commNoVal = req.getParameter("commNo");
			int commNo = Integer.parseInt(commNoVal);
			
			User user = (User)req.getSession(false).getAttribute("authUser");
			Comment comment = modifyCommentService.selectByNo(commNo);
			
			
			if(!canModify(comment.getUserId(),user)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			req.setAttribute("comment", comment);
			return FORM_VIEW;
		}catch(CommentNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String commNoVal = req.getParameter("commNo");
		int commNo = Integer.parseInt(commNoVal);
		
		String content = req.getParameter("content");
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		String loginId = user.getUserId();
		
		if(content==null||content.isEmpty()) {
			req.setAttribute("content", Boolean.TRUE);
			return FORM_VIEW;
		}
		
		ModifyCommRequest modifyCommRequest = new ModifyCommRequest(commNo,content,loginId);
		try {
			modifyCommentService.modifyComm(modifyCommRequest);
			return "/view/helpboard/modifyCommSuccess.jsp";
		}catch(CommentNotFoundException e) {
			res.sendError(HttpServletResponse.SC_FOUND);
			return null;
		}catch(CantModiException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		
	}

	private boolean canModify(String loginId,User user) {
		String writer = user.getUserId();
		return writer.equals(loginId);
	}

}
