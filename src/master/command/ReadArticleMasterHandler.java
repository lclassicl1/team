package master.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ReadArticleMasterHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int articleNo = Integer.parseInt(noVal);
		String category = req.getParameter("category");
		
		if(category.equalsIgnoreCase("free")) {
			return "/freeboard/read.do?no="+articleNo;
		}else if(category.equalsIgnoreCase("help")) {
			return "/help/read.do?no="+articleNo;
		}else if(category.equalsIgnoreCase("helper")) {
			return "/helper/read.do?no="+articleNo;
		}else if(category.equalsIgnoreCase("notice")) {
			return "/notice/read.do?no="+articleNo;
		}else if(category.equalsIgnoreCase("trade")) {
			return "/trade/read.do?no="+articleNo;
		}else if(category.equalsIgnoreCase("review")) {
			return "/review/read.do?no="+articleNo;
		}else {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
