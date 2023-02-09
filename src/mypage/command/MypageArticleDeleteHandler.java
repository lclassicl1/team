package mypage.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.ArticleNullException;
import Exception.UserNoNotMatchException;
import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.model.FreePage;
import freeboard.service.DeleteBoardService;
import freeboard.service.ReadBoardService;
import mvc.command.CommandHandler;

public class MypageArticleDeleteHandler implements CommandHandler {

	
	ReadBoardService readBoardService = new ReadBoardService();
	DeleteBoardService deleteBoardService = new DeleteBoardService();
	
	@Override
//	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		if(request.getMethod().equalsIgnoreCase("GET")) {
//			return processForm(request,response);
//		} else if(request.getMethod().equalsIgnoreCase("POST")) {
//			return processSubmit(request,response);
//		} else {
//			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//		}
//		return null;
//	}
	
//	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String noVal = request.getParameter("no");
//		int no = Integer.parseInt(noVal);
//		
//		FreePage freePage = readBoardService.getBoardDetail(no);
//		request.setAttribute("freeBoard", freePage);
//		
//		
//		int WriterNo = freePage.getFreeBoardList().get(0).getUserNo();
//		
//		User user = (User)request.getSession(false).getAttribute("authUser");
//		String loginUserId = user.getUserId();
//		
//		return "/view/mypage/mypageArticleDelete.jsp";
//	}
	
	
	public String process(HttpServletRequest request, HttpServletResponse response){
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		
		int userNo =user.getUserNo();
		
		readBoardService.getBoardDetail(no);
	
		try {
			deleteBoardService.delete(no,userNo);
		}catch(ArticleNullException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}catch(UserNoNotMatchException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "/view/mypage/mypageArticleDelete.jsp";
	}	
}
