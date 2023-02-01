package review.comment.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import mvc.command.CommandHandler;
import review.comment.service.Review_comment_Data;
import review.comment.service.Review_comment_ModifyRequest;
import review.comment.service.Review_comment_ModifyService;
import review.comment.service.Review_comment_ReadService;
import review.service.PermissionDeniedException;
import review.service.Review_Data;
import review.service.Review_ModifyService;
import review.service.Review_NotFoundException;
//수정폼 보여주기 및 수정처리요청 처리담당 컨트롤러
public class Review_comment_Modify_Handler implements CommandHandler {

	private static final String FORM_VIEW = "view/reviewreview_comment_modifyForm.jsp";
	
	//상세보기 Service
	private Review_comment_ReadService comment_readService = 
			new Review_comment_ReadService();
	
	//수정처리 Service
	private Review_comment_ModifyService modifyService = 
			new Review_comment_ModifyService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//폼 요청 방식에 따라 수청폼을 보여주거나 처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//수정폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//수정처리요청
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}//process의 끝.

	//(해당게시글의 정보가 출력되어있는)수정폼으로 이동-p669 38라인
		private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			try {
				//no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
				//만약 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
				String strNo = request.getParameter("no");//글번호
				if(strNo==null) {
					throw new RuntimeException();
				}
				int no = Integer.parseInt(strNo);//상세조회할글번호
				System.out.println("no="+no);
				
				//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
				String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
				int pageNo = 1;   
				if(strPageNo!=null) {
					pageNo = Integer.parseInt(strPageNo);			
				}
				
				//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 3으로 설정
				String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
				int rsize = 3;
				if(strRowSize!=null) {
					rsize = Integer.parseInt(strRowSize);			
				}		
				
				String title = request.getParameter("title");//수정할 제목
				String content = request.getParameter("content");//수정할 내용
				
				boolean increseReadCount = false;
				//2.비즈니스로직수행<->Service<->DB
				/*파라미터 
				  int no : 상세조회할 글번호
			  	  boolean increseReadCount:true(이면 조회수증가)
			  	  여기에서는 수정을 위해 상세보기를 진행하므로 조회수 증가를 하지않기 위해 false넘긴다
				 *리턴유형 */  
				Review_comment_Data reviewData = comment_readService.getReview(pageNo, increseReadCount);
				System.out.println("reviewData ="+reviewData);
				
				
				//p670 44라인
				//로그인한 회원은 자신의 글에 한하여 내용을 수정할 수 있어야 한다.
				//조건1)로그인 여부
				/*조건2)로그인한 회원은 자신의 글 여부
				    =>로그인한 user의 id와 작성자의 id가 일치?*/
				User authUser = (User)request.getSession().getAttribute("authUSER");				
				
				if(!canModify(authUser, reviewData)) {//수정불가이면
					//response.sendError(HttpServletResponse.SC_FORBIDDEN);
					request.setAttribute("cnt", 1);
					System.out.println("수정할 수 없는 게시글입니다.");
					return "/view/reviewreview_comment_fails.jsp";
				}
				/* HttpServletResponse.SC_FORBIDDEN: 403에러
				 서버가 허용하지 않는 웹 페이지나 미디어를 사용자가 요청할 때 
				 웹 서버가 반환하는 HTTP 상태 코드이다. 
				 다시 말해, 클라이언트가 서버에 도달할 수 있어도 
				 서버가 페이지 접근 허용을 거부했다는 것을 뜻한다*/
				
				//아래 ModifyRequest(로그인한userid,글번호,db의작성자명,db의title,db의내용)
				Review_comment_ModifyRequest modReq = 
						new Review_comment_ModifyRequest(authUser.getUserId(),
								no,
								reviewData.getReview().getComm_writer().getReview_comment_writer_name(),
								reviewData.getContent().getReview_comment_content());
				
				//3.Model & 4.View -p670 53라인
				request.setAttribute("modReq", modReq);
				//4.View
				return FORM_VIEW;
			}catch(Review_NotFoundException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);//404에러
				return null;
			}
		}
		
		
		//수정처리-p670 66라인
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
			//no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
			//만약 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
			String strNo = request.getParameter("no");//글번호
			if(strNo==null) {
				throw new RuntimeException();
			}
			int no = Integer.parseInt(strNo);//상세조회할글번호
			System.out.println("no="+no);
			
			//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
			String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
			int pageNo = 1;   
			if(strPageNo!=null) {
				pageNo = Integer.parseInt(strPageNo);			
			}
			
			//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 3으로 설정
			String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
			int rsize = 3;
			if(strRowSize!=null) {
				rsize = Integer.parseInt(strRowSize);			
			}		
			String content = request.getParameter("content");//수정할 내용
			String writer_name = request.getParameter("writer_name");//작성자명
			
			
			User authUser = (User)request.getSession().getAttribute("authUSER");
			
					
			//아래 ModifyRequest(로그인한userid,글번호,db의작성자명,db의title,db의내용)
			Review_comment_ModifyRequest modReq = 
					new Review_comment_ModifyRequest(authUser.getUserId(), 
							no,
							writer_name, 
							content);
			
			//3.Model & 4.View -p670 53라인
			request.setAttribute("modReq", modReq);
			
		
			try {//P670 83라인
				//3.비즈니스로직수행=>db에 update진행해라
				//파라미터 ModifyRequest modReq: 글 수정을 위한    수정하는 사용자id,수정할 글번호,수정할 제목,수정할 내용
				modifyService.update(modReq);
			
				//4.View-P670 85라인
				return "/view/reviewreview_comment_modifySuccess.jsp";  //리뷰 수정성공페이지
			}catch(Review_NotFoundException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);//404에러
				return null;
			}catch(PermissionDeniedException e) {//p671 89라인
				response.sendError(HttpServletResponse.SC_FORBIDDEN);//403에러
				return null;
			}

		}//processSubmit()끝	

		//수정권한체크-p670 61라인
		/*파라미터
		  User authUser: 로그인한유저정보
		 Review_Data review_Data : review_테이블과 review_content테이블 관련 데이터
		     작성자정보
		 *리턴유형
		  boolean : 수정가능하면 true리턴, 그렇지않으면 false리턴 
		  canModify : 수정가능이라는 뜻에서 canModify로 지정*/
		private boolean canModify(User authUser,Review_comment_Data reviewData) {
			if(authUser == null) {
				return false;
			}
			//로그인한유저정보에서 id를 가져오기
			String userId = authUser.getUserId();
			//작성자정보에서 id를 가져오기
			String writerId = reviewData.getReview().getComm_writer().getReview_comment_writer_id();
			
			//"로그인한userid".equals("작성자id")
			return userId.equals(writerId);
			
		}//canModify()끝
		
	
}//class의 끝.