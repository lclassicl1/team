package comment.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import comment.model.Comment;
import comment.model.CommentList;
import comment.model.CommentUpdateList;
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
	
		private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String articleNo = request.getParameter("articleNo");
			int no = Integer.parseInt(articleNo);
			
			String commNoVal = request.getParameter("commNo");
			int updateCommNo = Integer.parseInt(commNoVal);
			CommentUpdateList updateList =updateCommentService.updateList(updateCommNo);
			request.setAttribute("updateList", updateList);
			
			
			FreePage freePage = readBoardService.getBoardDetail(no);
			request.setAttribute("freePage", freePage);
			
			User user = (User)request.getSession(false).getAttribute("authUser");
			String userId = user.getUserId();
			request.setAttribute("userId", userId);
			request.setAttribute("articleNo",no);
			
			Comment comment = listCommentService.getCommentAll(no);
			request.setAttribute("comment",comment);
			 
			return "/view/freeboard/freeCommentUpdate.jsp";
	}
	
	
	private void processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String articleNoVal = request.getParameter("articleNo");
		int articleNo = Integer.parseInt(articleNoVal);
		
		
		String commContent = request.getParameter("commContent");
		System.out.println("commContent====="+commContent);
		String commNo = request.getParameter("commNo");
		System.out.println("commNo========="+commNo);
		
		int commno = Integer.parseInt(commNo);
		System.out.println("commno======"+commno);
		
		int cnt = updateCommentService.updateComment(commno,commContent);
		
		//insert 되었다는 변수
		request.setAttribute("cnt",cnt);
		
		response.sendRedirect("read.do?no=" + articleNo);
	}
}
