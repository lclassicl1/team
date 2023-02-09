package freeboard.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import article.model.ArticleRequest;
import auth.model.User;
import comment.service.WriteCommentService;
import freeboard.service.ArticleBoardService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;


public class WriteBoardHandler implements CommandHandler {
	
	WriteBoardService writeBoardService = new WriteBoardService();
	WriteCommentService writeCommentService = new WriteCommentService();
	ArticleBoardService articleBoardService = new ArticleBoardService();
	
	
	private static final String FORM_VIEW = "/view/freeboard/freeBoardWrite.jsp";
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		User user = (User)request.getSession(false).getAttribute("authUser");
		String userId = user.getUserId();
		System.out.println("userId===="+userId);
		request.setAttribute("userId", userId);
		
		
		return FORM_VIEW;
	}

	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String categorySearch = request.getParameter("categorySearch");
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		
		ArticleRequest articleReq = createWriterRequest(request,user);
		
		int articleNo = writeBoardService.writetBoard(articleReq,categorySearch);
		System.out.println("articleNo======"+articleNo);
		return "/freeboard/read.do?no="+articleNo;
	}
	
	private ArticleRequest createWriterRequest(HttpServletRequest request,User user) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String articleCategory = "free";
		return new ArticleRequest(articleCategory,title,user.getUserName(),content,user.getUserNo());
	}


}
