package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import review.service.ReviewPage;
import review.service.Review_List_Service;

//reivew 목록보기 요청에 따라 호출되는 Controller
//요청주소 : http://localhost//review/list.do
public class Review_List_Handler implements CommandHandler {

	//private 서비스 서비스변수명 = new 서비스 이름
	private  Review_List_Service review_List_Service = new Review_List_Service();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strPageNo = request.getParameter("pageNo"); //보려하는 페이지
				int pageNo = 1;
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		String strRowSize = request.getParameter("rowSize");//한 페이지당 보여줄 게시물 수
		int rsize = 5;
		if(strRowSize!=null) {
			rsize = Integer.parseInt(strRowSize);
		}
		
		//목록+페이징처리 관련 내용
		ReviewPage reviewPage = review_List_Service.getReviewPage(pageNo,rsize);
		
		//model
		request.setAttribute("reviewPage", reviewPage);
		request.setAttribute("rsize",rsize);
		return "/view/review/review_list.jsp";
	}

}

