package help.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import auth.model.User;
import help.model.WriterRequest;
import help.service.WriteHelpService;
import mvc.command.CommandHandler;

public class WriteHelpHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/helpboard/writeForm.jsp";
	WriteHelpService writeHelpService = new WriteHelpService();
	
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
		int articleNo = writeHelpService.articleReq(user.getUserNo());
		WriterRequest writerReq = createWriterRequest(req,articleNo,user);
		
		if(writerReq.getHelpTitle() == null||writerReq.getHelpTitle().isEmpty()) {
			errors.put("titleEmpty",Boolean.TRUE);
		}
		
		if(writerReq.getHelpContent() == null||writerReq.getHelpContent().isEmpty()) {
			errors.put("contentEmpty",Boolean.TRUE);
		}
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		writeHelpService.write(writerReq);
		return "/view/helpboard/writeSuccess.jsp";
	}
	private WriterRequest createWriterRequest(HttpServletRequest req,int articleNo,User user) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String category = req.getParameter("category");
		String articleCategory = "Help";
		return new WriterRequest(articleNo,articleCategory,user.getUserNo(),title,content,user.getUserName(),category);
	}
}
