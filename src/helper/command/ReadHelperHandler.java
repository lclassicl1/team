package helper.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import helper.model.UserInfoHelperInfo;
import helper.service.ReadHelperService;
import helperComment.model.Comment;
import helperComment.service.ListCommentService;
import mvc.command.CommandHandler;

public class ReadHelperHandler implements CommandHandler {

	ReadHelperService readHelperService = new ReadHelperService();
	ListCommentService listCommentService = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		try {
			UserInfoHelperInfo userHelper = readHelperService.getHelper(no, true);
			
			req.setAttribute("read", userHelper);
			
			List<Comment> commentList =listCommentService.getCommentList(no);
			req.setAttribute("commentList", commentList);
			return "/view/helperboard/readHelper.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no helper", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
