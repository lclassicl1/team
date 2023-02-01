package helper.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import auth.model.User;
import helper.model.Helper;
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
			
			User user = userHelper.getUser();
			Helper helper = userHelper.getHelper();
			
			req.setAttribute("user", user);//글쓴이의 유저정보 
			req.setAttribute("helper", helper);//게시판 정보 
			
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
