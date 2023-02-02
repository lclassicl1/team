package notice.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.NoticePage;
import notice.service.SearchNoticeService;
import mvc.command.CommandHandler;

public class SearchNoticeHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/view/noticeboard/listNotice.jsp";
	private SearchNoticeService searchNoticeService = new SearchNoticeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String input = req.getParameter("input");
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		NoticePage noticePage =searchNoticeService.search(pageNo, input);
		req.setAttribute("noticePage", noticePage);
		
		return FORM_VIEW;
	}

}
