package tradeboard.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.HelperContentNotFoundException;
import Exception.HelperNotFoundException;
import auth.model.User;
import tradeboard.model.Trade;
import tradeboard.model.UserInfoTradeInfo;
import tradeboard.service.ReadTradeService;
import tradecomment.model.Comment;
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
			
			User user = usertrade.getUser();
			Trade trade = usertrade.getTrade();
			
			req.setAttribute("user", user);//글쓴이의 유저정보 
			req.setAttribute("trade", trade);//게시판 정보 
			List<Comment> commentList =listCommentService.getCommentList(no);
			req.setAttribute("commentList", commentList);
			return "/view/tradeboard/readTrade.jsp";
		}catch(HelperNotFoundException | HelperContentNotFoundException e) {
			req.getServletContext().log("no trade", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
