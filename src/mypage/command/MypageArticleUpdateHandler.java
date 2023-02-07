package mypage.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.model.FreePage;
import freeboard.service.ReadBoardService;
import freeboard.service.UpdateBoardService;
import mvc.command.CommandHandler;

public class MypageArticleUpdateHandler implements CommandHandler {

	
	ReadBoardService readBoardService = new ReadBoardService();
	UpdateBoardService updateBoardService = new UpdateBoardService();
	
	
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
		request.setAttribute("freeBoard", freePage);
		
		
		int WriterNo = freePage.getFreeBoardList().get(0).getUserNo();
		
		User user = (User)request.getSession(false).getAttribute("authUser");
	
		return "/view/mypage/mypageArticleUpdate.jsp";
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String freeCategory = request.getParameter("freeCategory");
		int updateresult = updateBoardService.update(no, title, content, freeCategory);
		

		//수정 완료 되었을 경우 1, 실패시 0
		request.setAttribute("result",updateresult);
		
		
		return "/mypageArticleRead.do?no="+no;
	}
	
	
	
}
