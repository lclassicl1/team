package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.fabric.Response;

import auth.model.User;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import review.comment.model.Review_comment_Content;
import review.dao.ReviewDAO;
import review.dao.Review_ContentDAO;
import review.model.Review;
import review.model.Review_Content;
import review.model.Review_Writer;

//p638
//글쓰기 기능을 제공하는 서비스클래스
public class Review_WriteService {

	//필드
	private ReviewDAO reviewDAO = new ReviewDAO();
	private Review_ContentDAO review_ContentDAO = new Review_ContentDAO();
	
	//생성자
	
	//메서드
	//글입력
	/*파라미터  WriteRequest writeReq:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용*/
	//리턴타입 Integer : reviewBoard테이블에 입력된 글번호
	public Integer write(Review_WriteRequest writeReq, Review_Writer writer) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autocommit 설정해제
			
			Review review = toReview(writeReq);//p639 25라인
			
			//(로그인한유저id,로그인한유저명),입력제목,입력내용, 입력일,마지막수정일 추가
			//review insert테이블에 마지막insert한 id가져오기
			Review savedReview = reviewDAO.insert(conn,review);//Article테이블에 insert//p639 26라인
			if(savedReview==null) {
				throw new RuntimeException("review테이블에 insert 실패");
			}
			
				
			
			//p639 30라인
			Review_Content content = 
				new Review_Content(savedReview.getReview_number(),
						writeReq.getContent());
			
			
			
			
			//p639 33라인
			Review_Content savedContent = review_ContentDAO.reviewInsert(conn,content, writer, savedReview);//Article_content테이블에 insert
			if(savedContent==null) {
				throw new RuntimeException("review_ContentDAO테이블에 insert 실패");
			}
			
			conn.commit();//커밋-p639 38라인
			
			return savedReview.getReview_number();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);//p639 41라인
			/* throw new RuntimeException(); */
			System.out.println("error~~~~~~"+e);
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally{//p639 44라인
			JdbcUtil.close(conn);
		}
	return null;
	}//write()끝

	//p639 52라인
	//글입력시 필요한 data를 세팅 :파라미터에 입력일,마지막수정일 추가
	/*파라미터  WriteRequest writeReq:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용*/
	//리턴유형 Article:WriteRequest에 입력일,마지막수정일 추가
	private Review toReview(Review_WriteRequest writeReq) {
		Date now = new Date();
		//입력일은 현재 날짜시간정보 설정
		//마지막수정일은 입력일과 동일하게 현재 날짜시간정보 설정
		return new Review(null,writeReq.getWriter(), 
				writeReq.getTitle(),now,now,0,"Y");
	}
	
}









