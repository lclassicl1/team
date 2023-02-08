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

public class CommentLikeHandler implements CommandHandler {
	
	
	
	ReadBoardService readBoardService = new ReadBoardService();
	ListCommentService listCommentService = new ListCommentService();
	UpdateCommentService updateCommentService = new UpdateCommentService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String articleNoVal = request.getParameter("articleNo");
		int articleNo = Integer.parseInt(articleNoVal);
		
		String commNoVal = request.getParameter("commNo");
		
		int commNo = Integer.parseInt(commNoVal);
		
		int commVolt = updateCommentService.likeComment(articleNo,commNo);
		int result=updateCommentService.articleCntDown(articleNo);
		//insert 되었다는 변수
		
		response.sendRedirect("read.do?no=" + articleNo);
		return null;
	}
}
