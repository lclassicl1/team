package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import review.dao.ReviewDAO;
import review.model.Review;

//목록 handler로 정보를 보낼 service 
public class Review_List_Service {

	private ReviewDAO reviewDAO = new ReviewDAO();
	
	public ReviewPage getReviewPage(int pageNo, int size) {	
		System.out.println(pageNo + ", " + size);
		try {
			Connection conn = ConnectionProvider.getConnection();
	
		//페이징 처리	
		int total = reviewDAO.selectCount(conn);// 전체 게시글 수
		List<Review> review_List = reviewDAO.select(conn,(pageNo-1)*size,size);
		return new ReviewPage(total, pageNo, size, review_List);
	
		} catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException();
	}//catch의 끝.

	}//public reviewPage의 끝.	
	
}//class의 끝.
