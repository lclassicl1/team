package freeboard.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import freeboard.model.FreeBoard;
import freeboard.model.FreePage;
import freeboard.service.DeleteBoardService;
import freeboard.service.ListBoardService;
import freeboard.service.ReadBoardService;
import freeboard.service.UpdateBoardService;
import mvc.command.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {

	UpdateBoardService updateBoardService = new UpdateBoardService();
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
		request.setAttribute("freePage", freePage);
		
		
		int WriterNo = freePage.getFreeBoardList().get(0).getUserNo();
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		String loginUserId = user.getUserId();
		
		if(!canModify(WriterNo,user)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		return "/view/freeboard/freeBoardDelete.jsp";
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String no = request.getParameter("no");
		
		
	
		int deleteresult = deleteBoardService.delete(no);
		
		
		//insert 되었다는 변수
		request.setAttribute("deleteresult",deleteresult);
		
		
		return "/view/freeboard/freeBoardList.jsp";
	}
	private boolean canModify(int WriterNo,User user) {
			int loginUserNo = user.getUserNo();
			return loginUserNo == WriterNo;
	}
}
