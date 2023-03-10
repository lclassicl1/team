package helpComment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpComment.service.ConnService;
import mvc.command.CommandHandler;

public class ConnHandler implements CommandHandler {

	ConnService connService = new ConnService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String commNoVal = req.getParameter("commNo");
		int commNo = Integer.parseInt(commNoVal);
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		connService.conn(commNo);

		return "/help/read.do?no="+no;
		
	}

}
