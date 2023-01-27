package comment.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.DeleteCommentService;
import comment.service.WriteCommentService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;

public class CommentDeleteHandler implements CommandHandler {
	
	WriteBoardService writeBoardService = new WriteBoardService();
	WriteCommentService writeCommentService = new WriteCommentService();
	DeleteCommentService deleteCommentService = new DeleteCommentService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String free_no = request.getParameter("free_no");
		String comm_no = request.getParameter("comm_no");
		System.out.println("comm_no="+comm_no);
		
		int cnt = deleteCommentService.deleteComment(comm_no);
		
		//insert 되었다는 변수수
		request.setAttribute("cnt",cnt);
		
		response.sendRedirect("read.do?no=" + free_no);
		return null;
		}
	}
	
	/*
	 * private String processForm(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * return "/view/freeboard/freeBoardRead.jsp"; }
	 * 
	 * 
	 * private void processSubmit(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { request.setCharacterEncoding("UTF-8"); String
	 * free_no = request.getParameter("free_no"); String comm_no =
	 * request.getParameter("comm_no"); System.out.println("comm_no="+comm_no);
	 * 
	 * int cnt = deleteCommentService.deleteComment(comm_no);
	 * 
	 * //insert 되었다는 변수 request.setAttribute("cnt",cnt);
	 * 
	 * response.sendRedirect("read.do?no=" + free_no); } }
	 */