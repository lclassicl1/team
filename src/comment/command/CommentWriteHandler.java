package comment.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import comment.service.WriteCommentService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;

public class CommentWriteHandler implements CommandHandler {
	
	WriteBoardService writeBoardService = new WriteBoardService();
	WriteCommentService writeCommentService = new WriteCommentService();
	
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);// 수정폼보여줘
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			processSubmit(request, response);// 수정처리요청
			return null;
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "/view/freeboard/freeBoardRead.jsp";
	}

	
	private void processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String articleNoVal = request.getParameter("articleNo");
		int articleNo = Integer.parseInt(articleNoVal);
		String newComment = request.getParameter("newComment");
	
		User user = (User)request.getSession().getAttribute("authUser");
		String userid = user.getUserId();
		
		if(newComment!=null) {
			int cnt = writeCommentService.writeComment(articleNo,newComment,userid);
			response.sendRedirect("/freeboard/read.do?no="+articleNo);
		} else {
			response.sendRedirect("/freeboard/read.do?no="+articleNo);
		}

	}
}
