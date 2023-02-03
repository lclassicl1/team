package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.Comment;
import comment.service.ListCommentService;
import freeboard.model.FreeBoard;
import freeboard.service.ReadBoardService;
import mvc.command.CommandHandler;

public class MypageArticleReadHandler implements CommandHandler {

	
	private static final String FORM_VIEW="/view/mypage/myArticleRead.jsp";
	
	
	ListCommentService listCommentService = new ListCommentService();
	ReadBoardService readBoardService = new ReadBoardService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ReadBoardHandler 진입");
		String noVal = request.getParameter("no");
		int mypageNo = Integer.parseInt(noVal);
		FreeBoard freeBoard = readBoardService.getBoardDetail(mypageNo);
		System.out.println("freeBoard ="+freeBoard);
		request.setAttribute("freeBoard", freeBoard);
		
	
		
		 // 댓글 - 목록 코드
		 Comment comment = listCommentService.getCommentAll(mypageNo);
		 System.out.println("comment="+comment);
		 request.setAttribute("comment",comment);
		 
		return FORM_VIEW;
	}

}
