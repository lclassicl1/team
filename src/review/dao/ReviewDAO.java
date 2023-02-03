package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.Review;
import review.model.ReviewList;
import article.model.ModifyRequest;
import review.model.SearchReview;
import review.model.WriterRequest;
import jdbc.JdbcUtil;

public class ReviewDAO {
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public void insert(Connection conn,WriterRequest writerReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into reviewBOARD(article_no,article_category,user_no,review_category) " + 
				"value(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo()); 
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			pstmt.setString(4, writerReq.getReviewCategory());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<ReviewList> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN reviewboard h ON a.article_no=h.article_no " +
						"where a.isshow='Y' order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<ReviewList> reviewList = new ArrayList<>();
			while(rs.next()) {
				ReviewList reviewList1 = coverReviewList(rs);
				if(reviewList != null) {
					reviewList.add(reviewList1);
				}
			}
			return reviewList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	public List<ReviewList> search(Connection conn,SearchReview search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN reviewboard h ON a.article_no=h.article_no where a.isshow='Y' and h.review_category like ? and a.article_title like ? " + 
						 "order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<ReviewList> reviewList = new ArrayList<>();
			while(rs.next()) {
				ReviewList reviewList1 = coverReviewList(rs);
				if(reviewList1 != null) {
					reviewList.add(reviewList1);
				}
			}
			return reviewList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Review selectByNo(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from reviewboard where article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			Review review = null;
			
			if(rs.next()) {
				review = coverReview(rs);
			}
			return review;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from article where isshow='Y' and article_category='review' ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("count(*)");
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public void update(Connection conn,ModifyRequest modReq,String modCategory)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update reviewboard set review_category=? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,modCategory);
			pstmt.setInt(2, modReq.getArticleNo());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	private Review coverReview(ResultSet rs) throws SQLException {
		return new Review(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"),
						rs.getString("review_category"));
	}
	private ReviewList coverReviewList(ResultSet rs)throws SQLException {
		return new ReviewList(rs.getInt("article_no"),rs.getString("article_category"),rs.getString("article_title"),rs.getString("user_name"),rs.getString("article_content"),rs.getTimestamp("article_credate"),
					rs.getTimestamp("article_update"),rs.getInt("article_readcnt"),rs.getString("isshow"),rs.getInt("user_no"),rs.getString("review_category"));
	}
}
