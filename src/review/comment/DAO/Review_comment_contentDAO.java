package review.comment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import review.comment.model.Review_comment_Content;
import review.model.Review_Content;

public class Review_comment_contentDAO {

	
	//글 읽기(상세조회)
	//select -조회 post - 게시글
public Review_comment_Content selectReview_comment(Connection conn, int num) throws SQLException {
	PreparedStatement pstmt = null;
	String sql ="select comm_no,comm_content " + 
			"from  review_comment " + 
			"where comm_no=?";
	
	// 선언,초기값
	ResultSet rs = null;
	Review_comment_Content content = null;
	
	System.out.println("review_content"+content);
try {
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, num);
	rs=pstmt.executeQuery();
	if(rs.next()) {
		content = new Review_comment_Content(
				rs.getInt("comm_no"),
				rs.getString("comm_content")
				);
		System.out.println("comm_content = "+content);
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
	//댓글 수정
	public void reviewcommentUpdate(Connection conn,int num, String rev_content)  {
		PreparedStatement pstmt = null;
		String sql = "update comm_content " + 
				"set comm_content=? " + 
				"where comm_no=?";
	
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
	
		//댓글 완전 삭제
	public int reviewComentDelete(Connection conn, int num) {
		PreparedStatement pstmt = null;
		String sql = "delete from comm_content " + 
						"where comm_no=?";
		
		int cnt = 0; //삭제된 행 수를 저장할 변수
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return cnt;
	}//deleteRevPos
//-------------------------------------------------------------------------------
	//댓글 내용입력
	public Review_Content reviewComentInsert(Connection conn,Review_Content content) {
		PreparedStatement stmt = null;
		String sql = "insert into review_comment(user_name,comm_no,comm_content) " + 
				"values(?,?,?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(2,content.getReview_content());//유저이름
			//내용
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

	


	
}//public class의 끝.
