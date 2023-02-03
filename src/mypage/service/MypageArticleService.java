package mypage.service;

import java.util.List;

import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;

public class MypageArticleService {

	private FreeBoardDAO freeBoardDAO = new FreeBoardDAO();	
	
	
	public FreeBoard getMyapgeArticle(String mypageUserName) {
		List<FreeBoardList> list = freeBoardDAO.selectMypageArticle(mypageUserName);
		System.out.println("마이페이지 서비스 list====="+list);
		return new FreeBoard(list);
	}
	
}
