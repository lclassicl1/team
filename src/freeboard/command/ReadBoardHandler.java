package freeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import comment.model.Comment;
import comment.service.ListCommentService;
import comment.service.WriteCommentService;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;
import freeboard.model.FreePage;
import freeboard.service.ReadBoardService;
import mvc.command.CommandHandler;

public class ReadBoardHandler implements CommandHandler {

	ReadBoardService readBoardService = new ReadBoardService();
	ListCommentService listCommentService = new ListCommentService();
	WriteCommentService writeCommentService = new WriteCommentService();
	
	private static final String FORM_VIEW="/view/freeboard/freeBoardRead.jsp";
	
	
	@Override
	public String process(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
		String test = request.getParameter("test");
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		FreePage freePage = readBoardService.getBoardDetail(no);
		request.setAttribute("freePage", freePage);
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		String userId = user.getUserId();
		request.setAttribute("userId", userId);
		
		 // 댓글 - 목록 코드
		 Comment comment = listCommentService.getCommentAll(no);
		 request.setAttribute("comment",comment);
		 
		
		 
		
			return FORM_VIEW;
	}
	
	
}
