package review.comment.service;

import java.sql.Connection;
import java.sql.SQLException;


import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import review.dao.ReviewDAO;
import review.dao.Review_ContentDAO;

//삭제처리관련 Service클래스
public class Review_comment_DeleteService {

	//필드
	private ReviewDAO reviewDAO = new ReviewDAO();
	private Review_ContentDAO review_ContentDAO = new Review_ContentDAO();
	
	//생성자
	
	//메서드
	//삭제(delete용) - 아래의 update와 코드 동일
	/*
	public int reviewDelete(int no) {
			Connection conn = null;
			int cnt = 0;
				try {
					conn = ConnectionProvider.getConnection();
					conn.setAutoCommit(false);//auto커밋false설정
					cnt = review_ContentDAO.reviewDelete(conn, no); //article_content테이블에서 삭제
					
					if(cnt==0) {
						throw new review_ContentNotFoundException();
					}
					if(cnt==1) {
						cnt = reviewDAO.ReviewDelete(conn, no); //article테이블에서 삭제
					}
					conn.commit();//커밋
					return cnt;
				} catch (SQLException e) {
					JdbcUtil.rollback(conn);
				}finally {
					JdbcUtil.close(conn);
				}
			return cnt;
		}*/
		
	
	//-----------------------------------------------
		
	//삭제(update용)
	public int reviewDeleteUp(int no) {
		Connection conn = null;
		int cnt = 0;
			try {
				conn = ConnectionProvider.getConnection();
				cnt = reviewDAO.ReviewDeleteUp(conn, no);
				return cnt;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn);
			}
			return cnt;
		
	}
	
}




