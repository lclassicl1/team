package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import article.DAO.ArticleDAO;
import review.dao.ReviewDAO;
import review.model.Review;
import article.model.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyReviewService {

	ReviewDAO reviewDAO = new ReviewDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Review review = reviewDAO.selectByNo(conn, modReq.getArticleNo());
			if(review == null) {
				throw new HelperNotFoundException();
			}
			if(!canModify(modReq.getUserNo(),review)) {
				throw new PermissionDeniedException();
			}
			
			articleDAO.update(conn,modReq);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e){
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private boolean canModify(int loginNo,Review review) {
		int writer = review.getUserNo();
		return writer == loginNo;
	}
}
