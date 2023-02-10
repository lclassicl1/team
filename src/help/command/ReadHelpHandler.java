package help.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import help.model.UserInfoHelpInfo;
import help.service.ReadHelpService;
import helpComment.model.CommentTotal;
import helpComment.service.ListCommentService;
import mvc.command.CommandHandler;

public class ReadHelpHandler implements CommandHandler {

	ReadHelpService readHelpService = new ReadHelpService();
	ListCommentService listCommentService = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		try {
			UserInfoHelpInfo userHelp = readHelpService.getHelp(no, true);
			
			req.setAttribute("read", userHelp); 
			
			CommentTotal commentTotal =listCommentService.getCommentList(no);
			req.setAttribute("commentTotal", commentTotal);
			return "/view/helpboard/readHelp.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no help", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
