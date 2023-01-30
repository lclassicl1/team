package helperComment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helperComment.model.Comment;
import helperComment.model.ModifyCommRequest;
import helperComment.model.WriteCommentRequest;
import jdbc.JdbcUtil;

public class CommentDAO {

	public List<Comment> select(Connection conn , int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select * from helper_comment where helper_no=? and isshow='Y' ";
		
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
		String sql ="select * from helper_comment where comm_no=?";
		
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
	public void insert(Connection conn, WriteCommentRequest writeCommReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into HELPER_COMMENT(comm_content,user_id,helper_no,comm_update) " + 
				"value(?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writeCommReq.getContent());
			pstmt.setString(2, writeCommReq.getLoginId());
			pstmt.setInt(3, writeCommReq.getHelperNo());
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public void modify(Connection conn,ModifyCommRequest modiCommReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helper_comment set comm_content=?,comm_update=now() where comm_no = ?";
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
		String sql = "update helper_comment set isshow='N' where comm_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commNo);
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void conn(Connection conn , int commNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helper_comment set comm_conn='Y' where comm_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commNo);
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	private Comment coverComment(ResultSet rs) throws SQLException {
		return new Comment(rs.getInt("comm_no"),rs.getString("comm_content"),rs.getTimestamp("comm_credate"),rs.getTimestamp("comm_update"),rs.getString("user_id")
							,rs.getString("comm_conn"),rs.getString("isshow"),rs.getInt("helper_no"));
	}
}
