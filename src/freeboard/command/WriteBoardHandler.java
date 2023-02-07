package freeboard.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.model.User;
import comment.service.WriteCommentService;
import freeboard.service.ArticleBoardService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;


public class WriteBoardHandler implements CommandHandler {
	
	WriteBoardService writeBoardService = new WriteBoardService();
	WriteCommentService writeCommentService = new WriteCommentService();
	ArticleBoardService articleBoardService = new ArticleBoardService();
	
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
		
		
		return "/view/freeboard/freeBoardWrite.jsp";
	}

	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String categorySearch = request.getParameter("categorySearch");
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		String loginName=user.getUserName();
		int userNo = user.getUserNo();
		HttpSession ssesion=request.getSession();
		ssesion.setAttribute("userInfo", user); //로그인한 유저 정보
		
		String articleCategory = "free";
		int articleNo = articleBoardService.freeArticleInsert(articleCategory,title,loginName,content,userNo);
		
		
		int cnt = writeBoardService.writetBoard(articleNo,userNo,articleCategory,categorySearch);
		
		if(articleNo!=0) {
			response.sendRedirect(request.getContextPath()+"/freeboard/read.do?no="+articleNo);
		}
		return "/view/freeboard/freeBoardList.jsp";
	}


}
