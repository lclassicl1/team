package review.comment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import mvc.command.CommandHandler;
import review.comment.model.Reveiw_conmment_Writer;
import review.comment.service.Review_comment_Request;
import review.comment.service.Review_comment_WriterService;
import review.comment.service.Review_comment_Writer_Request;
import review.model.Review_Writer;
import review.service.Review_WriteRequest;

public class Review_comment_Write_Handler implements CommandHandler {
	
	private Review_comment_WriterService commentService = 
			new Review_comment_WriterService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return processCommentSubmit(request,response);//쓰기처리요청
	}//process끝
	

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
		return "/view/review/review_comment_fails.jsp";
	}
	
	//유효성검사-P641 41라인
	Map<String,Boolean> commentErrors = new HashMap<String,Boolean>();
	Review_comment_Writer_Request commReq = createWriteRequest(authUser,request);
	request.setAttribute("commentErrors",commentErrors);//p641 37라인
	
	//댓글 이동 및 글 번호 불러오기?
	/*
	 * response.sendRedirect("view/review/read.do");
	 * request.getParameter("review_no");
	 */
	
	
	if(!commentErrors.isEmpty()) {
		commReq.validate(commentErrors);
		return "/view/review/review_comment_fails.jsp";
	}
	 //차후 링크 수정예정
	commentService.commentwrite(commReq);
	response.sendRedirect("/review/read.do?no="+"item.comm_no"+"&pageNo");
	/*response.sendRedirect("/review/read.do?no="+"item.comm_no"+"&pageNo="+"reviewPage.currentPage"+"&rowSize="+"rsize");*/
	return null;
}//processSubmit의 끝.

//p641 53라인 참조
//리턴유형 WriteRequest:Writer(로그인한유저id,로그인한유저명),입력내용
private Review_comment_Writer_Request createWriteRequest(User authUser,HttpServletRequest request) {			
	return new Review_comment_Writer_Request(
				new Reveiw_conmment_Writer(authUser.getUserId(),
						   authUser.getUserName())
				,request.getParameter("content"));
	
	}//process의 끝

}//public의 끝
