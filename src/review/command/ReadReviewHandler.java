package review.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import review.model.UserInfoReviewInfo;
import review.service.ReadReviewService;
import reviewComment.model.CommentTotal;
import reviewComment.service.ListCommentService;
import mvc.command.CommandHandler;

public class ReadReviewHandler implements CommandHandler {

	ReadReviewService readReviewService = new ReadReviewService();
	ListCommentService listCommentService = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		try {
			UserInfoReviewInfo userreview = readReviewService.getreview(no, true);
			
			req.setAttribute("read", userreview); 
			
			CommentTotal commentTotal =listCommentService.getCommentList(no);
			req.setAttribute("commentTotal", commentTotal);
			return "/view/reviewboard/readReview.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no review", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
