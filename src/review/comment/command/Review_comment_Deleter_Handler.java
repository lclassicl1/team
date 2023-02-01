package review.comment.command;

//삭제요청 담당 컨트롤러 클래스(update용)
//요청url http:/localhost//review/deleter.do
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.command.CommandHandler;
import review.service.Review_DeleteService;

public class Review_comment_Deleter_Handler implements CommandHandler {

	private Review_DeleteService delArticleService
	= new Review_DeleteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터 받기
				String strNo = request.getParameter("no");//글번호
				int no = Integer.parseInt(strNo);
				
				//2.비즈니스로직<->Service<->DAO<->DB
				/*파라미터  int no : 삭제할 글번호
				 *리턴타입  int cnt: 삭제(update)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
				int cnt = delArticleService.reviewDeleteUp(no);
				
				//3.Model
				request.setAttribute("cnt", cnt);
				
				//4.View
				return "/view/review/review_delete.jsp";
	}

}
