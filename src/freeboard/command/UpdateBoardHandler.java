package freeboard.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.model.FreePage;
import freeboard.service.ListBoardService;
import freeboard.service.ReadBoardService;
import freeboard.service.UpdateBoardService;
import mvc.command.CommandHandler;


//자유게시판 수정 컨트롤러
public class UpdateBoardHandler implements CommandHandler {

	UpdateBoardService updateBoardService = new UpdateBoardService();
	ListBoardService listBoardService = new ListBoardService();
	ReadBoardService readBoardService = new ReadBoardService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		} else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		
		
		return null;
	}
	
	// 자유게시판 게시글 수정화면 보여주는 메서드
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		FreePage freePage = readBoardService.getBoardDetail(no);
		System.out.println("freeBoard ="+freePage);
		request.setAttribute("freePage", freePage);
		
		
		int WriterNo = freePage.getFreeBoardList().get(0).getUserNo();
		System.out.println("boardWriter======"+WriterNo);
		
		User user = (User)request.getSession(false).getAttribute("authUser");
	
		  if(!canModify(WriterNo,user)) {
		  response.sendError(HttpServletResponse.SC_FORBIDDEN); 
		  return null; 
		 }
		return "/view/freeboard/freeBoardUpdate.jsp";
	}

	// 자유게시판 게시글 수정하는 메서드
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String freeCategory = request.getParameter("freeCategory");
		int updateresult = updateBoardService.update(no, title, content, freeCategory);
		

		//수정 완료 되었을 경우 1, 실패시 0
		request.setAttribute("result",updateresult);
		
		
		return "/freeboard/read.do?no="+no;
	}
	
	  private boolean canModify(int WriterNo,User user) { 
		  int loginNo=user.getUserNo();
		  return WriterNo == loginNo; 
		  }
	 
}
