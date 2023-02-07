package review.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.ReviewPage;
import review.service.SearchReviewService;
import mvc.command.CommandHandler;

public class SearchReviewHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/reviewboard/listReview.jsp";
	private SearchReviewService searchReviewService = new SearchReviewService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String input = req.getParameter("input");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		ReviewPage reviewPage =searchReviewService.search(pageNo, input);
		req.setAttribute("reviewPage", reviewPage);
		
		return FORM_VIEW;
	}

}
