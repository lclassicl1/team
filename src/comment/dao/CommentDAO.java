package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comment.model.Comment;
import comment.model.CommentList;
import freeboard.model.FreeBoardList;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class CommentDAO {
	
	// 게시글 번호에 맞는 댓글 조회
		public List<CommentList> selectAllComment(int no) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql="select comm_no,comm_content,comm_credate,user_name,isshow,comm_volt,free_no" + 
					" from free_comment" + 
					" where free_no=? and isshow='Y'";
		
			Connection conn = null;
			List<CommentList> commentList = new ArrayList<>();
			try {
				conn=ConnectionProvider.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
				
			
				while(rs.next()) {
					CommentList list = new CommentList(
												rs.getInt(1),
												rs.getString(2),
												rs.getTimestamp(3),
												rs.getString(4),
												rs.getString(5),
												rs.getInt(6),
												rs.getInt(7));
					commentList.add(list);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(conn);
			}
			return commentList;
		}
	
		public CommentList selectComment(int no) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql="select comm_no,comm_content,comm_credate,user_name,isshow,comm_volt,free_no" + 
					" from free_comment" + 
					" where comm_no=?";
		
			Connection conn = null;
			CommentList commentList = null;
			try {
				conn=ConnectionProvider.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				rs = stmt.executeQuery();
			
				if(rs.next()) {
					commentList = new CommentList(rs.getInt(1),
												rs.getString(2),
												rs.getTimestamp(3),
												rs.getString(4),
												rs.getString(5),
												rs.getInt(6),
												rs.getInt(7));
				}
				return commentList;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(conn);
			}
		}
	//댓글 쓰기
	public int insertComment(int free_no, String comm_content) {
		PreparedStatement stmt = null;
		
		String sql="INSERT INTO FREE_COMMENT (comm_content,comm_credate,user_name,isshow,comm_volt,free_no)" + 
				" VALUES (?,now(),'이름15','Y',0,?)";
		
		int cnt = 0;
			Connection conn = null;
		try {
			conn= ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comm_content);
			stmt.setInt(2, free_no);
			cnt = stmt.executeUpdate();
			
			conn.commit();
		
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
		return cnt;
	}
	
	// 수정하기
	public int updateComment(int commno, String content) {
		PreparedStatement stmt = null;
		int result = -1;
		
		String sql="UPDATE free_comment" + 
				" SET comm_content=?" + 
				" WHERE comm_no=?";
		
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setInt(2, commno);
			result = stmt.executeUpdate();
			
				conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
	
		return result;
	}
	
	
	//글 삭제하기
	public int deleteComment(String comm_no) {
		System.out.println("dao-comment delete 진입");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = -1;
		
		String sql="Delete from free_comment" +
				" where comm_no=?";
		
		
			Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comm_no);
			result = stmt.executeUpdate();
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
	
		return result;
	} 
	/*
	//글 삭제하기
		public int deleteComment(String comm_no) {
			System.out.println("dao-comment delete 진입");
			PreparedStatement stmt = null;
			int result = -1;
			
			String sql="Delete from free_comment" +
					" where comm_no=?";
			
			
				Connection conn = null;
			try {
				conn=ConnectionProvider.getConnection();
				
				conn.setAutoCommit(false);
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, comm_no);
				result = stmt.executeUpdate();
				
				conn.commit();
			}catch(SQLException e) {
				e.printStackTrace();
				
			}finally {
				
				JdbcUtil.close(stmt);
				JdbcUtil.close(conn);
			}
		
			return result;
		} 
	*/
	
}































