package review.comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.conn.ConnectionProvider;
import review.comment.DAO.Review_comment_DAO;
import review.comment.model.Review_comment;


public class Review_comment_WriterService {

	private Review_comment_DAO review_commnet_DAO = new Review_comment_DAO();
	
	public Integer commentwrite(Review_comment_Writer_Request commReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Review_comment review_comment = toReview_Comment(commReq);
			
			Review_comment savedComment = review_commnet_DAO.insert(conn,review_comment);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
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
