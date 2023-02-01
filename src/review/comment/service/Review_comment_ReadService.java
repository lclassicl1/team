package review.comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import review.comment.DAO.Review_comment_DAO;
import review.comment.DAO.Review_comment_contentDAO;
import review.comment.model.Review_comment;
import review.comment.model.Review_comment_Content;

//p658
//상세글조회 관련 Service클래스이다
public class Review_comment_ReadService {
	
	private Review_comment_DAO comment_reviewDAO = new Review_comment_DAO();
	private Review_comment_contentDAO comment_contentDAO = new Review_comment_contentDAO();

	//p658 17라인
	/*파라미터 
	  int no : 상세조회할 글번호
	  boolean increseReadCount:true(이면 조회수증가)
	 *리턴유형 
	  ArticleData: article테이블과 article_content테이블 관련 데이터 */ 
	public Review_comment_Data getReview(int no, boolean increseReadCount) {
		Connection conn = null;
		
		try {
			conn= ConnectionProvider.getConnection();
			
			Review_comment review_comment = comment_reviewDAO.selectReview_comment(conn, no);
		
			Review_comment_Content comment_content = comment_contentDAO.selectReview_comment(conn, no);
		

			return new Review_comment_Data(review_comment, comment_content);
		}catch(SQLException e) {
			System.out.println("Review_comment_ReadService-getReview()발생시 exception발생");
			throw new RuntimeException(e);
		}
	}
	
}








