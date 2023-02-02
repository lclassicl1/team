package comment.command;

import java.io.IOException;

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
		request.setCharacterEncoding("UTF-8");
		String article_noVal = request.getParameter("article_no");
		int article_no = Integer.parseInt(article_noVal);
		String comm_content = request.getParameter("comm_content");
	
		User user = (User)request.getSession().getAttribute("authUser");
		String userid = user.getUserId();
		
		
		int cnt = writeCommentService.writeComment(article_no, comm_content,userid);
		
		//insert 되었다는 변수
		request.setAttribute("cnt",cnt);
		
		
		
//		return "redirect:" + FORM_VIEW+"?no=" + free_no;
//		response.sendRedirect( FORM_VIEW +"?no=" + free_no );
		response.sendRedirect("read.do?no=" + article_no);
	}
}
