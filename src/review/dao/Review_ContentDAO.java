package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.JdbcUtil;
import review.model.Review_Content;


//Review_Content테이블의 글 읽기 / 쓰기 / 삭제에 관련된 DB작업실행 클래스 
public class Review_ContentDAO {

	
	
		//글 읽기(상세조회)
		//select -조회 post - 게시글
	public Review_Content selectReview(Connection conn, int num) throws SQLException {
		PreparedStatement pstmt = null;
		String sql ="select review_no,review_content " + 
				"from  REVIEWBOARD " + 
				"where review_no=?";
		
		// 선언,초기값
		ResultSet rs = null;
		Review_Content content = null;
		
		System.out.println("review_content"+content);
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			content = new Review_Content(
					rs.getInt("review_no"),
					rs.getString("review_content")
					);
			System.out.println("review_content = "+content);
			System.out.println("rs.next"+rs.next());
		}
		return content;
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
	return content;
	
	}//selectPos의 끝.
	
//-------------------------------------------------------------------------------
	
	//글 수정
	public void reviewUpdate(Connection conn,int num, String rev_content)  {
		PreparedStatement pstmt = null;
		String sql = "update review_content " + 
				 "set review_content=? " + 
				 "where review_no=?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rev_content);
			pstmt.setInt(2, num);
			//int rev_cnt = stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//update의 끝.
	
//-------------------------------------------------------------------------------
	
		//글 삭제
	public int reviewDelete(Connection conn, int num) {
		PreparedStatement pstmt = null;
		String sql = "delete from review_content " + 
						"where review_no=?";
		
		int cnt = 0; //삭제된 행 수를 저장할 변수
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			//cnt = stmt.executeUpdate();
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return cnt;
	}//deleteRevPos
//-------------------------------------------------------------------------------
	//내용입력
	public Review_Content reviewInsert(Connection conn,Review_Content content) {
		PreparedStatement stmt = null;
		String sql = "insert into reviewboard(user_name,review_title,review_content,review_readcnt,review_category,user_no) " + 
				"values(?,?,?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			//유저이름
			//제목
			//내용
			//조회수
			//카테고리
			//유저번호
			stmt.setLong(1,content.getReview_number());//글 번호
			stmt.setString(2,content.getReview_content());//내용
			int cnt = stmt.executeUpdate();
			System.out.println("Review_Content테이블에 insert결과행수"+cnt);
			if(cnt>0) {  //articlecontent테이블에 insert성공
				return content;
			}else {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	
	
	
}//public class Review_ContentDAO의 끝.
