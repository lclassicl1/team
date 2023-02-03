package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import review.dao.ReviewDAO;
import review.model.ReviewList;
import review.model.ReviewPage;
import review.model.SearchReview;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SearchReviewService {

	ReviewDAO reviewDAO = new ReviewDAO();
	private int size = 10;
	
	public ReviewPage search(int pageNum, String category, String input){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = reviewDAO.selectCount(conn);
			
			SearchReview search = new SearchReview((pageNum-1)*size,size,category,input);
			
			List<ReviewList> reviewList = reviewDAO.search(conn, search);
			
			
			return new ReviewPage(total,pageNum,size,reviewList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
