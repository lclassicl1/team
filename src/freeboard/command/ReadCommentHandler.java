package freeboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.Comment;
import comment.service.ListCommentService;
import comment.service.WriteCommentService;
import freeboard.model.FreeBoard;
import freeboard.service.ReadBoardService;
import mvc.command.CommandHandler;

public class ReadCommentHandler implements CommandHandler {

	ReadBoardService readBoardService = new ReadBoardService();
	ListCommentService listCommentService = new ListCommentService();
	WriteCommentService writeCommentService = new WriteCommentService();
	
	private static final String FORM_VIEW="/view/freeboard/freeCommentRead.jsp";
	
	
	@Override
	public String process(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
		System.out.println("ReadBoardHandler 진입");
			String noVal = request.getParameter("no");
			int no = Integer.parseInt(noVal);
		 FreeBoard freeBoard = readBoardService.getBoardDetail(no);
		 System.out.println("freeBoard ="+freeBoard);
		 request.setAttribute("freeBoard", freeBoard);
		
		
		 
		 
		 // 댓글 - 목록 코드
		 Comment comment = listCommentService.getCommentAll(no);
		 System.out.println("comment="+comment);
		 request.setAttribute("comment",comment);
		 
		 
		 System.out.println("123123123123");
		return FORM_VIEW;
			
	}
	
	
}
