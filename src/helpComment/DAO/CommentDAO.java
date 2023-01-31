package helpComment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helpComment.model.Comment;
import helpComment.model.ModifyCommRequest;
import helpComment.model.WriteCommentRequest;
import jdbc.JdbcUtil;

public class CommentDAO {

	public List<Comment> select(Connection conn , int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select * from help_comment where article_no=? and isshow='Y' ";
		
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
		String sql ="select * from help_comment where comm_no=?";
		
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
		String sql = "insert into help_COMMENT(comm_content,user_id,help_no,comm_update) " + 
				"value(?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writeCommReq.getContent());
			pstmt.setString(2, writeCommReq.getLoginId());
			pstmt.setInt(3, writeCommReq.getHelpNo());
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public void modify(Connection conn,ModifyCommRequest modiCommReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update help_comment set comm_content=?,comm_update=now() where comm_no = ?";
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
		String sql = "update help_comment set isshow='N' where comm_no=?";
		
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
		String sql = "update help_comment set comm_conn='Y' where comm_no = ?";
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
							,rs.getString("comm_conn"),rs.getString("isshow"),rs.getInt("help_no"));
	}
}
