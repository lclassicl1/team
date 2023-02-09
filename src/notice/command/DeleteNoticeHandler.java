package notice.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.PermissionDeniedException;
import auth.model.User;
import notice.service.DeleteNoticeService;
import mvc.command.CommandHandler;

public class DeleteNoticeHandler implements CommandHandler {

	//private static final String BACK_VIEW = "/notice/list.do";
	private DeleteNoticeService deleteNoticeService = new DeleteNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int articleNo = Integer.parseInt(noVal);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		int userNo = user.getUserNo();
		
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		try {
			deleteNoticeService.delete(articleNo,userNo);
			return "/notice/list.do";
		}catch(PermissionDeniedException e) {
			errors.put("cantDelete",Boolean.TRUE);
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		


	}
}
