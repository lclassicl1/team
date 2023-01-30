package tradecomment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tradecomment.service.ConnService;
import mvc.command.CommandHandler;

public class ConnTrade implements CommandHandler {

	ConnService connService = new ConnService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String commNoVal = req.getParameter("commNo");
		int commNo = Integer.parseInt(commNoVal);
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		connService.conn(commNo);
		
		return "/tradeboard/read.do?no="+no;
		
	}

}
