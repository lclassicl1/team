package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comment.model.CommentList;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class CommentDAO {
	
		// 게시글 번호에 맞는 댓글 조회
		public List<CommentList> selectAllComment(int no) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql="select comm_no,comm_content,comm_credate,user_id,isshow,comm_volt,article_no" + 
					" from free_comment" + 
					" where article_no=? and isshow='Y'";
		
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
	
		// 댓글 리스트
		public CommentList selectComment(int no) {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql="select comm_no,comm_content,comm_credate,user_id,isshow,comm_volt,article_no" + 
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
	public int insertComment(int articleNo, String newComment, String userid) {
		PreparedStatement stmt = null;
		
		String sql="INSERT INTO FREE_COMMENT (comm_content,user_id,article_no)" + 
				" VALUES (?,?,?)";
		
		int cnt = 0;
			Connection conn = null;
		try {
			conn= ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newComment);
			stmt.setString(2, userid);
			stmt.setInt(3, articleNo);
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
	
	// 좋아요 
		public int updateLikeComment(int articleNo, int commno) {
			PreparedStatement stmt = null;
			int result = 0;
			
			String sql="UPDATE free_comment" + 
					" SET comm_volt=comm_volt+1" + 
					" WHERE article_no=? and comm_no=?";
			
			Connection conn = null;
			try {
				conn=ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, articleNo);
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
		
		public int articleCntDown(int articleNo) {
			PreparedStatement stmt = null;
			int result = -1;
			
			String sql="update article" + 
						" set article_readcnt=article_readcnt-1" + 
						" where article_no=?";
			

				Connection conn = null;
			
				try {
				conn=ConnectionProvider.getConnection();
				
				conn.setAutoCommit(false);
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, articleNo);
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
		
	
	
	
	//글 삭제하기
	public int deleteComment(int commNo) {
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
			stmt.setInt(1, commNo);
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
}































