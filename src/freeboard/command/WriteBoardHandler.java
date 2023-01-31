package freeboard.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.model.User;
import comment.service.WriteCommentService;
import freeboard.service.WriteBoardService;
import mvc.command.CommandHandler;

public class WriteBoardHandler implements CommandHandler {
	
	WriteBoardService writeBoardService = new WriteBoardService();
	WriteCommentService writeCommentService = new WriteCommentService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WriteBoardHandler 진입");
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);// 수정폼보여줘
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);// 수정처리요청
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
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		
		return "/view/freeboard/freeBoardWrite.jsp";
	}

	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String categorySearch = request.getParameter("categorySearch");
		System.out.println("free_category="+categorySearch);
		
		User user = (User)request.getSession(false).getAttribute("authUser");
		String writeId = user.getUserId();
		HttpSession ssesion=request.getSession();
		ssesion.setAttribute("userInfo", user);//로그인한 유저 정보
		
		int cnt = writeBoardService.writetBoard(title, content, categorySearch, writeId);
		 
		//insert 되었다는 변수
		request.setAttribute("cnt",cnt);
		
		
		
		return "/view/freeboard/freeBoardWriteSuccess.jsp";
	}


}
