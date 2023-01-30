package tradeboard.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.PermissionDeniedException;
import auth.model.User;
import tradeboard.service.DeleteTradeService;
import mvc.command.CommandHandler;

public class DeleteTradeHandler implements CommandHandler {

	//private static final String BACK_VIEW = "/trade/list.do";
	private DeleteTradeService deleteTradeService = new DeleteTradeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int tradeNo = Integer.parseInt(noVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		int userNo = user.getUserNo();
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			deleteTradeService.delete(tradeNo,userNo);
			return "/view/tradeboard/deleteSuccess.jsp";
		}catch(PermissionDeniedException e) {
			errors.put("cantDelete",Boolean.TRUE);
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
	}
}
