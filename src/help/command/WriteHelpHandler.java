package help.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticleRequest;
import auth.model.User;
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
		String helpCategory=req.getParameter("helpCategory");
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		
		ArticleRequest articleReq = createWriterRequest(req,user);
		
		
		if(articleReq.getArticleTitle() == null||articleReq.getArticleTitle().isEmpty()) {
			errors.put("titleEmpty",Boolean.TRUE);
		}
		
		if(articleReq.getArticleContent() == null||articleReq.getArticleContent().isEmpty()) {
			errors.put("contentEmpty",Boolean.TRUE);
		}
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int articleNo = writeHelpService.writer(articleReq,helpCategory);
		return "/view/helpboard/writeSuccess.jsp";
	}
	private ArticleRequest createWriterRequest(HttpServletRequest req,User user) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String articleCategory = "help";
		return new ArticleRequest(articleCategory,title,user.getUserName(),content,user.getUserNo());
	}
}
