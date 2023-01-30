package comment.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import comment.service.WriteCommentService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;

public class CommentWriteHandler implements CommandHandler {
	
	WriteBoardService writeBoardService = new WriteBoardService();
	WriteCommentService writeCommentService = new WriteCommentService();
	
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);// 수정폼보여줘
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			processSubmit(request, response);// 수정처리요청
			return null;
		} else {
			/*
			 * 참고. 상태코드 => SC_OK 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는
			 * 의미로 쓰인다.
			 * 
			 * 상태코드 => SC_METHOD_NOT_ALLOWED 405(허용되지 않는 메소드): 요청에 지정된 방법을 사용할 수 없다. 예를 들어
			 * POST 방식으로 요청을 받는 서버에 GET 요청을 보내는 경우, 또는 읽기 전용 리소스에 PUT 요청을 보내는 경우에 이 코드를
			 * 제공한다.
			 */
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "/view/freeboard/freeBoardRead.jsp";
	}

	
	private void processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String free_noVal = request.getParameter("free_no");
		int free_no = Integer.parseInt(free_noVal);
		String comm_content = request.getParameter("comm_content");
	
		User user = (User)request.getSession().getAttribute("authUser");
		String userid = user.getUserId();
		
		
		int cnt = writeCommentService.writeComment(free_no, comm_content,userid);
		
		//insert 되었다는 변수
		request.setAttribute("cnt",cnt);
		
		
		
//		return "redirect:" + FORM_VIEW+"?no=" + free_no;
//		response.sendRedirect( FORM_VIEW +"?no=" + free_no );
		response.sendRedirect("read.do?no=" + free_no);
	}
}
