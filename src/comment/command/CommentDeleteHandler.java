package comment.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import comment.service.DeleteCommentService;
import comment.service.ListCommentService;
import comment.service.WriteCommentService;
import freeboard.model.FreeBoard;
import freeboard.service.ListBoardService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;

public class CommentDeleteHandler implements CommandHandler {
	
	WriteBoardService writeBoardService = new WriteBoardService();
	WriteCommentService writeCommentService = new WriteCommentService();
	DeleteCommentService deleteCommentService = new DeleteCommentService();
	ListCommentService listCommentService = new ListCommentService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String articleNo = request.getParameter("articleNo");
		
		
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		
		
		int commNo = Integer.parseInt(request.getParameter("comm_no"));
		
		int cnt = deleteCommentService.deleteComment(commNo);
		
		//insert 되었다는 변수수
		request.setAttribute("cnt",cnt);
		 
		response.sendRedirect("read.do?no=" + articleNo);
		return null;
		}
	}
	
	