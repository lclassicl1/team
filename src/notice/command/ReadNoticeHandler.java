package notice.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import notice.model.UserInfoNoticeInfo;
import notice.service.ReadNoticeService;
import mvc.command.CommandHandler;

public class ReadNoticeHandler implements CommandHandler {

	ReadNoticeService readNoticeService = new ReadNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		try {
			UserInfoNoticeInfo usernotice = readNoticeService.getnotice(no, true);
			
			req.setAttribute("read", usernotice); 
			
			return "/view/noticeboard/readNotice.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no notice", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
