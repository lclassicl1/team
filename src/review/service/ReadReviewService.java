package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import Exception.HelperNotFoundException;
import Exception.UserNotFoundException;
import article.DAO.ArticleDAO;
import article.model.Article;
import auth.DAO.UserDAO;
import auth.model.User;
import review.dao.ReviewDAO;
import review.model.Review;
import review.model.UserInfoReviewInfo;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ReadReviewService {

	ReviewDAO reviewDAO = new ReviewDAO();
	UserDAO userDAO = new UserDAO();
	ArticleDAO articleDAO = new ArticleDAO(); 
	
	public UserInfoReviewInfo getreview(int articleNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Review review = reviewDAO.selectByNo(conn, articleNo);
			Article article = articleDAO.selectByNo(conn, articleNo);
			
			if(review == null) {
				throw new HelperNotFoundException();
			}
			if(article == null) {
				throw new ArticleNullException();
			}
			int writerUserNo = review.getUserNo();
			
			User user = userDAO.selectByNo(conn, writerUserNo);
			
			if(user == null) {
				throw new UserNotFoundException();
			}
			
			if(incrementReadCnt) {
				articleDAO.incrementReadCnt(conn, articleNo);
			}
			UserInfoReviewInfo userreview = new UserInfoReviewInfo(user,article,review);
			
			conn.commit();
			return userreview;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
