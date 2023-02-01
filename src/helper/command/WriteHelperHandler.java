package helper.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import helper.model.WriterRequest;
import helper.service.WriteHelperService;
import mvc.command.CommandHandler;

public class WriteHelperHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/helperboard/writeForm.jsp";
	WriteHelperService writeHelperService = new WriteHelperService();
	
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
		int articleNo = writeHelperService.articleReq(user.getUserNo());
		WriterRequest writerReq = createWriterRequest(req,articleNo,user);
		
		if(writerReq.getHelperTitle() == null||writerReq.getHelperTitle().isEmpty()) {
			errors.put("titleEmpty",Boolean.TRUE);
		}
		
		if(writerReq.getHelperContent() == null||writerReq.getHelperContent().isEmpty()) {
			errors.put("contentEmpty",Boolean.TRUE);
		}
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		writeHelperService.write(writerReq);
		return "/view/helperboard/writeSuccess.jsp";
	}
	private WriterRequest createWriterRequest(HttpServletRequest req,int articleNo,User user) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String category = req.getParameter("category");
		String articleCategory = "Helper";
		return new WriterRequest(articleNo,articleCategory,user.getUserNo(),title,content,user.getUserName(),category);
	}
}
