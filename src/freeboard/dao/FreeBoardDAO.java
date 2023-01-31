package freeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import freeboard.model.FreeBoardList;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class FreeBoardDAO {
	
	
	//게시판 글 목록 페이지 
	public List<FreeBoardList> selectAll() {
		System.out.println("FreeBoardDAO selectAll진입");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="SELECT *" + 
				" FROM FREEBOARD" + 
				" WHERE isshow='Y'" + 
				" ORDER BY article_no DESC";
		
		List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			System.out.println("dao1");
			while(rs.next()) {
				FreeBoardList list = new FreeBoardList(
									rs.getInt("article_no"),
									rs.getString("article_category"),
									rs.getString("free_title"),
									rs.getString("free_content"),
									rs.getTimestamp("free_credate"),
									rs.getTimestamp("free_update"),
									rs.getInt("free_readcnt"),
									rs.getString("user_id"),
									rs.getString("isshow"),
									rs.getString("free_category"),
									rs.getInt("user_no"));
				System.out.println("dao2"+list);
							freeBoardList.add(list);
							
}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return freeBoardList;
	}
	
	
	
	//게시글 상세조회
	public List<FreeBoardList> readDetail(int no) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="SELECT *"+ 
				" FROM FREEBOARD"+ 
				" WHERE article_no=?";
		
		List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			System.out.println("dao1");
			while(rs.next()) {
				FreeBoardList list = new FreeBoardList(
										rs.getInt("article_no"),
										rs.getString("article_category"),
										rs.getString("free_title"),
										rs.getString("free_content"),
										rs.getTimestamp("free_credate"),
										rs.getTimestamp("free_update"),
										rs.getInt("free_readcnt"),
										rs.getString("user_id"),
										rs.getString("isshow"),
										rs.getString("free_category"),
										rs.getInt("user_no"));
							freeBoardList.add(list);
							
}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(rs);
		}
		System.out.println("doa3"+freeBoardList);
		return freeBoardList;
	}
	
		// 카테고리로 글 검색 DAO
		public List<FreeBoardList> searchBoard(String categorySearch) {
			
			System.out.println();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql="SELECT article_no,free_title,free_content,free_credate,free_update,free_readcnt,user_id,isshow,free_category,user_no"+ 
					" FROM FREEBOARD"+ 
					" WHERE free_category=?";
			
			List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
			Connection conn = null;
			try {
				conn=ConnectionProvider.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categorySearch);
				rs = stmt.executeQuery();
				
				System.out.println("dao1");
				while(rs.next()) {
					FreeBoardList list = new FreeBoardList(
												rs.getInt("article_no"),
												rs.getString("article_category"),
												rs.getString("free_title"),
												rs.getString("free_content"),
												rs.getTimestamp("free_credate"),
												rs.getTimestamp("free_update"),
												rs.getInt("free_readcnt"),
												rs.getString("user_id"),
												rs.getString("isshow"),
												rs.getString("free_category"),
												rs.getInt("user_no"));
					System.out.println("dao========"+list);
								freeBoardList.add(list);
								
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stmt);
				JdbcUtil.close(rs);
			}
			System.out.println("doa3"+freeBoardList);
			return freeBoardList;
		}
	
	
	// 게시글 작성
	public int insertBoard(String title, String content, String categorySearch, String writeId) {
		PreparedStatement stmt = null;
		
		String sql="INSERT INTO FREEBOARD (free_title,free_content,free_credate,free_update,free_readcnt,user_id,isshow,free_category,user_no)" + 
				" VALUES (?,?,now(),0,?,'Y',?,11)";
		
		Connection conn = null;
		int cnt = 0;
		try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, writeId);
			stmt.setString(4, categorySearch);
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
	
	//게시글 수정하기
	public int updateBoard(String no, String title, String content, String free_category) {
		PreparedStatement stmt = null;
		int result = -1;
		
		String sql="UPDATE FREEBOARD" + 
				" SET free_title=?,free_content=?,free_category=?,free_update=now()" + 
				" WHERE article_no=?";
		
		
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, free_category);
			stmt.setString(4, no);
			result = stmt.executeUpdate();
			
			conn.commit();
			
			System.out.println("dao-updateBoard 진입");
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
	public int deleteBoard(String no) {
		PreparedStatement stmt = null;
		int result = -1;
		
		String sql="update freeboard" + 
				" set isshow='N'" + 
				" where article_no=?";
		

		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, no);
			result = stmt.executeUpdate();
			
			conn.commit();
			
			System.out.println("dao-delete 성공");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
	
		return result;
	}
	
	// 조회수 증가
	public int updateCnt(int no) {
		PreparedStatement stmt = null;
		int result = -1;
		
		String sql="update FREEBOARD" + 
				" SET free_readcnt=free_readcnt+1" + 
				" WHERE article_no=?";
		

			Connection conn = null;
		
			try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			result = stmt.executeUpdate();
			
			conn.commit();
			System.out.println("dao-updateCnt 진입");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
	
		return result;
	}
	
	
}





























