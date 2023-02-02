package mypage.service;

import java.util.List;

import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;

public class MypageArticleService {

	private FreeBoardDAO freeBoardDAO = new FreeBoardDAO();	
	
	
	public FreeBoard getMyapgeArticle(String mypageUserId,String input) {
		List<FreeBoardList> list = freeBoardDAO.selectMypageArticle(mypageUserId,input);
		System.out.println("마이페이지 서비스 list====="+list);
		return new FreeBoard(list);
	}
	
}
