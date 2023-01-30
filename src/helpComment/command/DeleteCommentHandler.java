package helpComment.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.CommentNotFoundException;
import Exception.canNotDeleteException;
import auth.model.User;
import helpComment.service.DeleteCommentService;
import mvc.command.CommandHandler;

public class DeleteCommentHandler implements CommandHandler {

	
	private DeleteCommentService deleteCommentService = new DeleteCommentService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String commNoVal = req.getParameter("commNo");
		int commNo = Integer.parseInt(commNoVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		String loginId = user.getUserId();
		
		try {
			deleteCommentService.commDelete(commNo, loginId);
			
			return "/view/helpboard/deleteCommSuccess.jsp";
		}catch(CommentNotFoundException e) {
			res.sendError(HttpServletResponse.SC_FOUND);
			return null;
		}catch(canNotDeleteException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
