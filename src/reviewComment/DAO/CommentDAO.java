package reviewComment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reviewComment.model.Comment;
import reviewComment.model.ModifyCommRequest;
import reviewComment.model.WriteCommentRequest;
import jdbc.JdbcUtil;

public class CommentDAO {

	public List<Comment> select(Connection conn , int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select * from review_comment where article_no=? and isshow='Y' ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			List<Comment> commentList = new ArrayList<>();
			
			while(rs.next()) {
				commentList.add(coverComment(rs));
			}
			
			return commentList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public Comment selectByNo(Connection conn,int commNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select * from review_comment where comm_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return coverComment(rs);
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public int commentCount(Connection conn,int no)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from review_comment where isshow='Y' and article_no=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
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
	public void insert(Connection conn, WriteCommentRequest writeCommReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into review_COMMENT(comm_content,user_id,article_no) " + 
				"value(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writeCommReq.getContent());
			pstmt.setString(2, writeCommReq.getLoginId());
			pstmt.setInt(3, writeCommReq.getArticleNo());
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public void modify(Connection conn,ModifyCommRequest modiCommReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update review_comment set comm_content=? where comm_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modiCommReq.getContent());
			pstmt.setInt(2, modiCommReq.getCommNo());
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void isshow(Connection conn, int commNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update review_comment set isshow='N' where comm_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commNo);
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void volt(Connection conn , int commNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update review_comment set comm_volt = comm_volt+1 where comm_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commNo);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	private Comment coverComment(ResultSet rs) throws SQLException {
		return new Comment(rs.getInt("comm_no"),rs.getString("comm_content"),rs.getTimestamp("comm_credate"),rs.getString("user_id")
							,rs.getString("isshow"),rs.getInt("comm_volt"),rs.getInt("article_no"));
	}
}
