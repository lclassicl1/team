package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.ArticlePage;
import auth.model.User;
import comment.model.Comment;
import comment.service.ListCommentService;
import freeboard.model.FreeBoard;
import freeboard.service.ReadBoardService;
import mvc.command.CommandHandler;
import mypage.service.MypageArticleReadService;

public class MypageArticleReadHandler implements CommandHandler {

	
	
	ListCommentService listCommentService = new ListCommentService();
	ReadBoardService readBoardService = new ReadBoardService();
	MypageArticleReadService mypageArticleReadService = new MypageArticleReadService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String noVal = request.getParameter("no");
		int myArticleNo= Integer.parseInt(noVal);
		String myArticleCategory = request.getParameter("category");
		
		if(myArticleCategory.equalsIgnoreCase("free")) {
			return "/freeboard/read.do?no="+myArticleNo;
		}else if(myArticleCategory.equalsIgnoreCase("help")) {
			return "/help/read.do?no="+myArticleNo;
		}else if(myArticleCategory.equalsIgnoreCase("helper")) {
			return "/helper/read.do?no="+myArticleNo;
		}else if(myArticleCategory.equalsIgnoreCase("notice")) {
			return "/notice/read.do?no="+myArticleNo;
		}else if(myArticleCategory.equalsIgnoreCase("trade")) {
			return "/trade/read.do?no="+myArticleNo;
		}else if(myArticleCategory.equalsIgnoreCase("review")) {
			return "/review/read.do?no="+myArticleNo;
		}else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
