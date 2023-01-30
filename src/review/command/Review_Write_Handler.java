package review.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import mvc.command.CommandHandler;
import review.model.Review_Writer;
import review.service.Review_WriteRequest;

//리뷰페이지 쓰기폼 보여주기, 요청, 처리 담당 컨트롤러
//요청주소   http:/localhost/review/review.do
//article : 신문기사, 글 등의~~
//revie : 후기
//AUTH : 인증
public class Review_Write_Handler implements CommandHandler {

	private static final String FORM_VIEW = "/view/review/review_Write_Form.jsp";

	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//폼의 요청방식에 따라 쓰기폼보여줘 요청과  쓰기처리요청을 구분
				if(request.getMethod().equalsIgnoreCase("GET")) {
					return processForm(request,response);//쓰기폼 보여주기
				}else if(request.getMethod().equalsIgnoreCase("POST")) {
					return processSubmit(request,response);//쓰기처리요청
				}else {
					response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					return null;
				}
			}//process끝

	
	
		//쓰기폼으로 이동-p641 31라인 
		//user Class파일 및 loginUser 세션정보 추가필요  
		private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
			User authUser = loginedUser(request);
			request.setAttribute("authUser", authUser);
			return FORM_VIEW;	
		}//processForm의 끝.
		
		
		
		//로그인한 유저정보는 세션에서 받자
		//user Class파일 추가 필요
		public User loginedUser(HttpServletRequest request) {
			User authUser = (User)request.getSession().getAttribute("authUser");
			return authUser;
		}//loginedUser의 끝.
		
		
		
		//쓰기처리-p641 35라인
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//로그인한 유저정보는 세션에서 받자
			//세션정보 추가필요  
			User authUser = loginedUser(request);
			
			if(authUser == null) {
				return "/veiw/review/review_modifyFailse.jsp";
			}
			
			//유효성검사-P641 41라인
			Map<String,Boolean> errors = new HashMap<String,Boolean>();
			Review_WriteRequest writeReq = createWriteRequest(authUser,request);
			request.setAttribute("errors",errors);//p641 37라인
		
			
			
			if(!errors.isEmpty()) {
				writeReq.validate(errors);
				return "/veiw/review/review_modifyFailse.jsp";
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
			
		}
		
		
		
}//CommandHandler의 끝.