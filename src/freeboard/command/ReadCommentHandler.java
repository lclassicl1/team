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
		 
		 
		 //댓글 입력
		 System.out.println("코멘트no"+no);
		 String content = request.getParameter("content");
		 int commentResult = writeCommentService.writeComment(no,content);
		 request.setAttribute("commentResult", commentResult);
		 
		 
		 //조회수 증가

		return FORM_VIEW;
			
	}
	
	/*
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("processSubmit 메서드 진입 - Post");
		
		String no 
			= request.getParameter("no");
		 FreeBoard freeBoard = readBoardService.getBoardDetail(no);
		 System.out.println("freeBoard ="+freeBoard);
		 request.setAttribute("freeBoard", freeBoard);
	
	
	 
		 
		 // 댓글 상세 보기 코드
		 Comment comment = listCommentService.getCommentList(no);
		 System.out.println("comment="+comment);
		 request.setAttribute("comment",comment);
		
		
		
		
		//댓글 입력
		 no 
			= request.getParameter("no");
		 System.out.println("코멘트no"+no);
		 String content = request.getParameter("content");
		 int commentResult = writeCommentService.writeComment(content, no);
		 request.setAttribute("commentResult", commentResult);
		 return FORM_VIEW;
	}
	*/
}
