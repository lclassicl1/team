package review.comment.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import mvc.command.CommandHandler;
import review.model.Review_Writer;
import review.service.Review_WriteRequest;

public class Review_comment_Write_Handler implements CommandHandler {

	
	private static final String FORM_VIEW = "/view/review/review_comment_Write_Form.jsp";
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//폼의 요청방식에 따라 쓰기폼보여줘 요청과  쓰기처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processCommentForm(request,response);//쓰기폼 보여주기
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processCommentSubmit(request,response);//쓰기처리요청
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process끝

	
	//쓰기폼으로 이동-p641 31라인 
	//user Class파일 및 loginUser 세션정보 추가필요  
	private String processCommentForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User authUser = loginedUser(request);
		request.setAttribute("authUser", authUser);
		return "/view/review/review_comment_list.jsp"; //글 작성에 성공할 경우 댓글 목록, db에 댓글이 올라가도록 설정.	
	}//processForm의 끝.
	

//로그인한 유저정보는 세션에서 받자
//user Class파일 추가 필요
public User loginedUser(HttpServletRequest request) {
	User authUser = (User)request.getSession().getAttribute("authUser");
	return authUser;
}//loginedUser의 끝.



//쓰기처리-p641 35라인
private String processCommentSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//로그인한 유저정보는 세션에서 받자
	//세션정보 추가필요  
	User authUser = loginedUser(request);
	
	if(authUser == null) {
		return "/veiw/review/review_comment_fails.jsp";
	}
	
	//유효성검사-P641 41라인
	Map<String,Boolean> commentErrors = new HashMap<String,Boolean>();
	Review_WriteRequest writeReq = createWriteRequest(authUser,request);
	request.setAttribute("commentErrors",commentErrors);//p641 37라인
	
	//댓글 이동 및 글 번호 불러오기?
	/*
	 * response.sendRedirect("view/review/read.do");
	 * request.getParameter("review_no");
	 */
	
	
	if(!commentErrors.isEmpty()) {
		writeReq.validate(commentErrors);
		return "/veiw/review/review_comment_fails.jsp";
	}
	 //차후 링크 수정예정
	return FORM_VIEW;
}//processSubmit의 끝.

//p641 53라인 참조
//리턴유형 WriteRequest:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용
private Review_WriteRequest createWriteRequest(User authUser,HttpServletRequest request) {			
	return new Review_WriteRequest(
				new Review_Writer(authUser.getUserId(),
						   authUser.getUserName())
				,request.getParameter("title")
				,request.getParameter("content"));
	
	}//process의 끝

}//public의 끝
