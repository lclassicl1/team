package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import review.dao.ReviewDAO;
import review.model.ReviewList;
import review.model.ReviewPage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListReviewService {

	private ReviewDAO reviewDAO = new ReviewDAO();
	private int size = 10;
	
	public ReviewPage getreviewPage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = reviewDAO.selectCount(conn);
			
			List<ReviewList> reviewList = reviewDAO.select(conn, (pageNum-1)*size, size);
			return new ReviewPage(total,pageNum,size,reviewList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
