package tradeboard.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import tradeboard.model.WriterRequest;
import tradeboard.service.WriteTradeService;
import mvc.command.CommandHandler;

public class WriteTradeHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/tradeboard/writeForm.jsp";
	WriteTradeService writeTradeService = new WriteTradeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String,Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriterRequest writerReq = createWriterRequest(req,user);
		
		if(writerReq.getTitle() == null||writerReq.getTitle().isEmpty()) {
			errors.put("titleEmpty",Boolean.TRUE);
		}
		
		if(writerReq.getContent() == null||writerReq.getContent().isEmpty()) {
			errors.put("contentEmpty",Boolean.TRUE);
		}
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		writeTradeService.write(writerReq);
		return "/view/tradeboard/writeSuccess.jsp";
	}
	private WriterRequest createWriterRequest(HttpServletRequest req,User user) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		return new WriterRequest(title,content,user.getUserNo(),user.getUserName());
	}
}
