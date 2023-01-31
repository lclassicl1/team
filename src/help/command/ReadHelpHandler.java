package help.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import auth.model.User;
import help.model.Help;
import help.model.UserInfoHelpInfo;
import help.service.ReadHelpService;
import helpComment.model.Comment;
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
			User user = userHelp.getUser();
			Help help = userHelp.getHelp();
			req.setAttribute("user", user);//글쓴이의 유저정보 
			req.setAttribute("help", help);//게시판 정보 
			
			List<Comment> commentList =listCommentService.getCommentList(no);
			req.setAttribute("commentList", commentList);
			return "/view/helpboard/readHelp.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no help", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
