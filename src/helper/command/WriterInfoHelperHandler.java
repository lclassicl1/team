package helper.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import helper.service.WriterInfoHelperService;
import mvc.command.CommandHandler;

public class WriterInfoHelperHandler implements CommandHandler {

	private WriterInfoHelperService writerService = new WriterInfoHelperService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int no  = Integer.parseInt(noVal);
		
		User user = writerService.getUser(no);
		
		req.setAttribute("user", user);
		
		return "/view/helperboard/writerInfo.jsp";
	}

}
