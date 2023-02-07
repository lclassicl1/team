package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import article.DAO.ArticleDAO;
import article.model.ArticleRequest;
import review.dao.ReviewDAO;
import review.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteReviewService {

	private ReviewDAO reviewDAO = new ReviewDAO();
	private ArticleDAO articleDAO = new ArticleDAO();
	
	public void writer(ArticleRequest articleReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleNo = articleDAO.articleReq(conn, articleReq);
			
			if(articleNo==0) {
				throw new ArticleNullException();
			}
			String reviewCategory = "리뷰";
			WriterRequest writerReq = new WriterRequest(articleNo,articleReq.getArticleCategory(),articleReq.getUserNo(),reviewCategory);
			
			reviewDAO.insert(conn, writerReq);
			conn.commit();
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
}
