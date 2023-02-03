package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.ReviewPage;
import review.service.ListReviewService;
import mvc.command.CommandHandler;

public class ListReviewHandler implements CommandHandler {

	ListReviewService listreviewService = new ListReviewService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ReviewPage reviewPage =listreviewService.getreviewPage(pageNo);
		req.setAttribute("reviewPage", reviewPage);
		
		return "/view/reviewboard/listReview.jsp";
	}

}
