package review.comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import review.comment.DAO.Review_comment_DAO;
import review.comment.DAO.Review_comment_contentDAO;
import review.comment.model.Review_comment;
import review.comment.model.Review_comment_Content;
import review.model.Review;
import review.model.Review_Content;


public class Review_comment_WriterService {

	private Review_comment_DAO review_commnet_DAO = new Review_comment_DAO();
	private Review_comment_contentDAO review_comment_content_DAO = new Review_comment_contentDAO();
	
	
	public Integer commentwrite(Review_comment_Writer_Request commReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto commit  해제
			
			Review_comment review_comment = toReview_Comment(commReq);
			
			
			
			//(로그인한유저id,로그인한유저명),입력제목,입력내용, 입력일,마지막수정일 추가
			//review insert테이블에 마지막insert한 id가져오기
			Review_comment savedReviewComment = review_commnet_DAO.insert(conn, review_comment);
			if(savedReviewComment==null) {
				throw new RuntimeException("review테이블에 insert 실패");
			}
			
			//p639 30라인
			Review_comment_Content content = 
				new Review_comment_Content(savedReviewComment.getReview_no(),
						commReq.getReveiw_conmment_content());
			
			//p639 33라인
			Review_Content savedContent = savedReviewComment.reviewInsert(conn,content);//Article_content테이블에 insert
			if(savedContent==null) {
				throw new RuntimeException("review_ContentDAO테이블에 insert 실패");
			}
			
			conn.commit();//커밋-p639 38라인
			
			return savedReviewComment.getReview_no();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);//p639 41라인
			throw new RuntimeException();
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally{//p639 44라인
			JdbcUtil.close(conn);
		}
	}
	
	
	
	//p639 52라인
	//글입력시 필요한 data를 세팅 :파라미터에 입력일,마지막수정일 추가
	/*파라미터  WriteRequest writeReq:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용*/
	//리턴유형 Article:WriteRequest에 입력일,마지막수정일 추가
	private Review_comment toReview_Comment(Review_comment_Writer_Request commReq) {
		Date now = new Date();
		//입력일은 현재 날짜시간정보 설정
		//마지막수정일은 입력일과 동일하게 현재 날짜시간정보 설정
		return new Review_comment(null,commReq.getcomment_Wrtier(),
				commReq.getReveiw_conmment_content(),now,now,"Y");
	}
	
}
