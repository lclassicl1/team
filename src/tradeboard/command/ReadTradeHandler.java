package tradeboard.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import tradeboard.model.UserInfoTradeInfo;
import tradeboard.service.ReadTradeService;
import tradecomment.model.CommentTotal;
import tradecomment.service.ListCommentService;
import mvc.command.CommandHandler;

public class ReadTradeHandler implements CommandHandler {

	ReadTradeService readtradeService = new ReadTradeService();
	ListCommentService listCommentService = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		try {
			UserInfoTradeInfo usertrade = readtradeService.getTrade(no, true);

			req.setAttribute("read", usertrade);
			
			CommentTotal commentTotal =listCommentService.getCommentList(no);
			req.setAttribute("commentTotal", commentTotal);
			return "/view/tradeboard/readTrade.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no trade", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
