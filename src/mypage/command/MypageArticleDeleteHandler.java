package mypage.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		} else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		return null;
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		FreePage freePage = readBoardService.getBoardDetail(no);
		System.out.println("freeBoard ="+freePage);
		request.setAttribute("freeBoard", freePage);
		
		
		int WriterNo = freePage.getFreeBoardList().get(0).getUserNo();
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		String loginUserId = user.getUserId();
		System.out.println("loginUserId===="+loginUserId);
		
		return "/view/mypage/mypageArticleDelete.jsp";
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String no = request.getParameter("no");
		
		int deleteresult = deleteBoardService.delete(no);
		
		//insert 되었다는 변수
		request.setAttribute("deleteresult",deleteresult);
		
		
		return "/mypageArticle.do";
	}
	
}
