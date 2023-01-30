package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import review.service.Review_Data;
import review.service.Review_ReadService;

//상세조회 담당 컨트롤러
public class Review_Read_Handler implements CommandHandler {

	private Review_ReadService readService =
			new Review_ReadService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
		// 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
		String strNo = request.getParameter("no");
		if(strNo==null) {
			throw new RuntimeException();
		}
		int no = Integer.parseInt(strNo);//상세조회할글번호
		
		//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
		//기본으로 보여주는 페이지위치
		String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
		int pageNo = 1;   
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);			
		}
		
		//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 5로 설정
		//기본으로 보여주는 값을 5개 단위로 한다는 내용
		String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
		int rsize = 5;
		if(strRowSize!=null) {
			rsize = Integer.parseInt(strRowSize);			
		}
		
		
		//2.비즈니스로직 -> Service->DAO->DB
		/*파라미터 
		  int no : 상세조회할 글번호
	  	  boolean increseReadCount:true(이면 조회수증가)
		*리턴유형 
		  reviewData: reviewboard테이블과 review_content테이블 관련 데이터  */  
		Review_Data reviewData = readService.getReview(no,true);
		
		//3.Model
		//릴레이용 pageNo=요청페이지&rowSize=1페이지당게시글수
		request.setAttribute("reviewData", reviewData);//review테이블과 review_content테이블 관련 데이터
		System.out.println(reviewData);
		request.setAttribute("pageNo", pageNo);//요청페이지
		request.setAttribute("rowSize", rsize);//1페이지당게시글수
			
		//4.View
		return "/view/review/review_read.jsp";
	}

}
