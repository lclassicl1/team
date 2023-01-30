package review.service;

import java.sql.Connection;
import java.sql.SQLException;


import jdbc.conn.ConnectionProvider;
import review.dao.ReviewDAO;
import review.dao.Review_ContentDAO;
import review.model.Review;
import review.model.Review_Content;

//p658
//상세글조회 관련 Service클래스이다
public class Review_ReadService {
	
	private ReviewDAO reviewDAO = new ReviewDAO();
	private Review_ContentDAO contentDAO = new Review_ContentDAO();

	//p658 17라인
	/*파라미터 
	  int no : 상세조회할 글번호
	  boolean increseReadCount:true(이면 조회수증가)
	 *리턴유형 
	  ArticleData: article테이블과 article_content테이블 관련 데이터 */ 
	public Review_Data getReview(int no, boolean increseReadCount) {
		Connection conn = null;
		
		try {
			conn= ConnectionProvider.getConnection();
			
			Review review = reviewDAO.selectReview(conn, no);
			if(review==null) { //article테이블에서 특정글번호에 해당하는 레코드존재x
				throw new Review_NotFoundException();
			}
			
			Review_Content content = contentDAO.selectReview(conn, no);
			if(content==null) { //article_content테이블에서 특정글번호에 해당하는 레코드존재x
				throw new Review_ContentNotFoundException();
			}

			
			//특정글번호 상세조회시   조회수 증가관련-p659 27라인
			if(increseReadCount) {
				reviewDAO.reviewReadCount(conn, no);
			}
			
			return new Review_Data(review, content);
		}catch(SQLException e) {
			System.out.println("Review_ReadService-getReview()발생시 exception발생");
			throw new RuntimeException(e);
		}
	}
	
}








