package reviewComment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reviewComment.service.VoltService;
import mvc.command.CommandHandler;

public class VoltReviewHandle implements CommandHandler {

	VoltService connService = new VoltService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String commNoVal = req.getParameter("commNo");
		int commNo = Integer.parseInt(commNoVal);
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		connService.volt(commNo);
		
		return "/review/read.do?no="+no;
		
	}

}
