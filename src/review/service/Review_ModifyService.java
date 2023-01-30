package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import review.dao.ReviewDAO;
import review.dao.Review_ContentDAO;
import review.model.Review;

//p667
//게시글 수청처리 관련 서비스클래스
public class Review_ModifyService {
	
	//필드
	private ReviewDAO reviewDAO = new ReviewDAO();
	private Review_ContentDAO review_contentDAO = new Review_ContentDAO();
	
	//생성자
	
	//메서드
	//수정처리-p667 17라인
	//파라미터 modifyRequest: 글 수정을 위한    수정하는 사용자id,수정할 글번호,수정할 제목,수정할 내용
	public void update(Review_ModifyRequest modifyRequest) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			
			//특정글번호에 해당하는 (변경전)db데이터가져오기-p667 23라인
			Review rivew = reviewDAO.selectReview(conn,modifyRequest.getReviewNumber());
			if(rivew==null) {
				throw new Review_NotFoundException();
			}
			
			//수정불가시 커미션거부예외처리로 넘긴다
			//파라미터:수정하려는 사용자id, 특정글번호에 해당하는 (변경전)db데이터
			//리턴유형:db데이터작성자id와 수정하려는사용자id가 동일하면 true리턴, 그렇지않으면 false리턴
			if(!canUpdate(modifyRequest.getUserId(),rivew)) {//p667 28라인
				throw new PermissionDeniedException();
			}
			
			//article테이블의 수정처리-p668 31라인
			reviewDAO.reviewUpdate(conn, modifyRequest.getReviewNumber(),
							  modifyRequest.getTitle());
			
			//article_content테이블의 수정처리-p668 33라인
			review_contentDAO.reviewUpdate(conn, modifyRequest.getReviewNumber(), 
					  modifyRequest.getContent());
			
			conn.commit();//커밋
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//p668 47라인
	/*파라미터
	  String modifyingUserId: 수정하려는 사용자id
	  Article article : 특정글번호에 해당하는 (변경전)db데이터
	 *리턴유형
	  boolean : db데이터작성자id와 수정하려는사용자id가 동일하면 true리턴, 그렇지않으면 false리턴 */
	private boolean canUpdate(String modifyUserId,
			Review revirw) {
		
		//db데이터에서  작성자id를 가져오기
		String id = revirw.getReview_writer().getReview_writer_id();
		
		//"db데이터작성자id".equals("수정하려는사용자id")
		return id.equals(modifyUserId);
	}//canModify()끝
}












