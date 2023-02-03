package comment.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.Comment;
import comment.model.CommentList;
import comment.service.DeleteCommentService;
import comment.service.ListCommentService;
import comment.service.UpdateCommentService;
import comment.service.WriteCommentService;
import freeboard.command.ReadBoardHandler;
import freeboard.model.FreeBoard;
import freeboard.model.FreePage;
import freeboard.service.ReadBoardService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;

public class CommentUpdateHandler implements CommandHandler {
	
	
	
	ReadBoardService readBoardService = new ReadBoardService();
	ListCommentService listCommentService = new ListCommentService();
	UpdateCommentService updateCommentService = new UpdateCommentService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);// 수정폼보여줘
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			processSubmit(request, response);
			return null;
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("CommentUpdateHandler 진입");
			String articleNo = request.getParameter("articleNo");
			System.out.println("articleNo======="+articleNo);
			int no = Integer.parseInt(articleNo);
			
		 FreePage freePage = readBoardService.getBoardDetail(no);
		 request.setAttribute("freePage", freePage);
		
		
		 
		 request.setAttribute("articleNo",no);
		 // 댓글 - 목록 코드
		 String commnoVal = request.getParameter("comm_no");
			int commno = Integer.parseInt(commnoVal);
		 CommentList commentList = listCommentService.getCommentList(commno);
		 request.setAttribute("commentList",commentList);
		 
		return "/view/freeboard/freeCommentUpdate.jsp";
	}
	
	
	private void processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("댓글 post 요청 진입");
		String articleNoVal = request.getParameter("articleNo");
		int articleNo = Integer.parseInt(articleNoVal);
		
		
		String commContent = request.getParameter("commContent");
		String commNo = request.getParameter("commNo");
		System.out.println("commContent======"+commContent);
		System.out.println("commNo====="+commNo);
		
		
		int commno = Integer.parseInt(commNo);
		
		int cnt = updateCommentService.updateComment(commno,commContent);
		
		//insert 되었다는 변수
		request.setAttribute("cnt",cnt);
		
		response.sendRedirect("read.do?no=" + articleNo);
	}
}
