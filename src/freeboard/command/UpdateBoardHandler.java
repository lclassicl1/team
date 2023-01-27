package freeboard.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freeboard.model.FreeBoard;
import freeboard.service.ListBoardService;
import freeboard.service.ReadBoardService;
import freeboard.service.UpdateBoardService;
import mvc.command.CommandHandler;

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
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		FreeBoard freeBoard 
	 	= readBoardService.getBoardDetail(no);
		System.out.println("freeBoard ="+freeBoard);
		request.setAttribute("freeBoard", freeBoard);
		
		
		return "/view/freeboard/freeBoardUpdate.jsp";
	}


	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String free_category = request.getParameter("free_category");
		System.out.println("no2222="+no);
		System.out.println("title="+title);
		System.out.println("content="+content);
		System.out.println("free_category="+free_category);
		int updateresult = updateBoardService.update(no, title, content, free_category);
		
		
		//insert 되었다는 변수
		request.setAttribute("result",updateresult);
		
		
		
		return "/freeboard/read.do?no="+no;
	}
}
