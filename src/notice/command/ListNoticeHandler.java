package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.NoticePage;
import notice.service.ListNoticeService;
import mvc.command.CommandHandler;

public class ListNoticeHandler implements CommandHandler {

	ListNoticeService listNoticeService = new ListNoticeService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		NoticePage noticePage =listNoticeService.getNoticePage(pageNo);
		req.setAttribute("noticePage", noticePage);
		
		return "/view/noticeboard/listNotice.jsp";
	}

}
