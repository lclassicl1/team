package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jdbc.JdbcUtil;
import review.model.Review;
import review.model.Review_Writer;


public class ReviewDAO {

	//rev_str : 시작행 인덱스번호. 첫 행은 0부터 시작
	//rev_size : 1페이지당 출력 게시물 수
	public List<Review> select(Connection conn,int rev_Strno, int rev_size) 
			throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "select review_no,user_name, " + 
				"       review_title,review_credate,review_update,review_readcnt,isshow " + 
				"from REVIEWBOARD " +
				"where isshow='Y' "+
				"order by review_no desc limit ?,?";
		
		ResultSet rs = null;
		 List<Review> reviewList = new ArrayList<Review>();
		//try,catch 필요
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,rev_Strno);
		pstmt.setInt(2,rev_size);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			Review review = converReivew(rs);
			reviewList.add(review);
		}
		return reviewList;
	}
	
	//Review객체로 변환하기-p647 36라인
	private Review converReivew(ResultSet rs) throws SQLException{
		
		return new Review(rs.getInt("review_no"),
				 new Review_Writer(rs.getString("user_name")),
						rs.getString("review_title"),
						toDate(rs.getTimestamp("review_credate")),
						toDate(rs.getTimestamp("review_update")),
						rs.getInt("review_readcnt"),
						rs.getString("isshow"));	
	}
	//Timestamp->Date객체로 변환하기:p648 47라인
		private Date toDate(Timestamp timestamp) {
			if(timestamp==null) {
				return null;
			}
		//	System.out.println("tistamp : "+timestamp);
			return new Date(timestamp.getTime());
			
		}
	
	
	//전체 게시글 수-p646
		public int selectCount(Connection conn) throws SQLException {
			PreparedStatement pstmt = null;
			String sql = "select count(review_no) " + 
					     "from REVIEWBOARD "+
					     "where isshow='Y'";
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;			
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
		
		
	
	//상세조회-p655
		public Review selectReview(Connection conn,int rev_no) throws SQLException{
			PreparedStatement pstmt = null;
			String sql = "select review_no,user_name, " + 
						 "       review_title,review_credate,review_update,review_readcnt,isshow " + 
						 "from  REVIEWBOARD " + 
						 "where isshow='Y' and review_no=?";
			ResultSet rs = null;
			Review reivew = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rev_no);//상세조회할 글 번호
				rs = pstmt.executeQuery();
				if(rs.next()) {
					reivew = converReivew(rs);
				}
				return reivew;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}//상세조회 selectById()끝
		
		
	//조회수증가
		public void reviewReadCount(Connection conn, int no) {
			PreparedStatement stmt = null;
			String sql = "update reviewboard " + 
					"set review_readcnt=review_readcnt+1 " + 
					"where isshow='Y' and review_no=?";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				int cnt = stmt.executeUpdate();
				System.out.println("조회수증가된 행 수="+cnt);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	//글수정
		public void reviewUpdate(Connection conn,int review_number, String title) {
			PreparedStatement stmt = null;
			String sql = "update reviewboard " + 
					"set review_title=?, review_update = now() " + 
					"where isshow='Y' and review_no=?";
		
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,title);
				stmt.setInt(2, review_number);
				int cnt = stmt.executeUpdate();
				System.out.println("review update된 행 수="+cnt);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//글삭제-delete
		/*파라미터  int no : 삭제할 글번호
		 *리턴타입  int : 삭제(delete)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
		/*
		public int ReviewDelete(Connection conn, int no) {
			PreparedStatement stmt = null;
			String sql = "delete from reviewboard " + 
						 "where review_no=?";
			
			int cnt = 0;//삭제(delte)된 행 수를 저당할 변수
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				cnt = stmt.executeUpdate();
				System.out.println("review 삭제(delete)된 행 수="+cnt);
				return cnt;
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stmt);
			}
			return cnt;
		}	*/
		
		
		
	//글삭제-update
		public int ReviewDeleteUp(Connection conn, int no) {
			PreparedStatement stmt = null;
			String sql = "update reviewboard " + 
					 "set isshow='N' " + 
					 "where review_no=?";
			int cnt = 0;//삭제(update)된 행 수를 저당할 변수
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				cnt = stmt.executeUpdate();
				System.out.println("review 삭제(update)된 행 수="+cnt);
				return cnt;
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stmt);
			}
			return cnt;
		}
		
		//글쓰기
		//유저 이름, 제목, 내용, 조회수, 카테고리, 유저 번호
		public Review insert(Connection conn,Review review) throws SQLException {
			PreparedStatement stmt = null;  //insert용 
			Statement stmt2 = null; //select용
			String sql = "insert into reviewboard(user_name,review_title,review_content,review_readcnt,review_category,user_no) " + 
					"values(?,?,?,?,?)";
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,review.getReview_writer().getReview_writer_id()); //작성자 id
				stmt.setString(2,review.getReview_writer().getReview_writer_name()); //작성자 이름
				stmt.setString(3,review.getReview_title()); //제목
				stmt.setTimestamp(4, toTimestamp(review.getReview_credate())); //입력일
				stmt.setTimestamp(5, toTimestamp(review.getReview_update())); //마지막수정일
				int cnt = stmt.executeUpdate();
				System.out.println("insert결과행수"+cnt);
				
				if(cnt>0) { //입력이 되었다면
					stmt2 = conn.createStatement();
					rs = stmt2.executeQuery("select last_insert_id() from reviewboard");
					if(rs.next()) {//p635 34라인
						Integer newNum = rs.getInt(1);
						return new Review(newNum, 
								review.getReview_writer(), 
								review.getReview_title(), 
								review.getReview_credate(), 
								review.getReview_update(),
								0,
								"Y");
					}
				}
				
				return null;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt2);
				JdbcUtil.close(stmt);
			}
		}
		
	
	//Date타입을 Timestamp타입으로 변환-p635 52라인
		private Timestamp toTimestamp(Date date){
			return new Timestamp(date.getTime());
			
		}
	
	
	
	
	
}//public class ReviewDAO의 끝.
