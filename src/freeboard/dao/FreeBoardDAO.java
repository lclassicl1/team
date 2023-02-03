package freeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		String sql="select a.*,f.free_category" + 
				" from article as a inner join freeboard as f " + 
				" on a.article_no=f.article_no" + 
				" where isshow='Y'"+
				" ORDER BY article_no DESC";
		
		List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FreeBoardList list = new FreeBoardList(
									rs.getInt("article_no"),
									rs.getString("article_category"),
									rs.getString("article_title"),
									rs.getString("user_name"),
									rs.getString("article_content"),
									rs.getTimestamp("article_credate"),
									rs.getTimestamp("article_update"),
									rs.getInt("article_readcnt"),
									rs.getString("isshow"),
									rs.getInt("user_no"),
									rs.getString("free_category"));
							freeBoardList.add(list);
							
}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
		return freeBoardList;
	}
	
	
	
	//게시글 상세조회
	public List<FreeBoardList> readDetail(int no) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="select a.*,f.free_category" + 
				" from article as a inner join freeboard as f" + 
				" on a.article_no=f.article_no" + 
				" where a.article_no=? and isshow='Y'" + 
				" ORDER BY article_no DESC";
		
		
		List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			
			System.out.println("dao1");
			if(rs.next()) {
				FreeBoardList list = new FreeBoardList(
											rs.getInt("article_no"),
											rs.getString("article_category"),
											rs.getString("article_title"),
											rs.getString("user_name"),
											rs.getString("article_content"),
											rs.getTimestamp("article_credate"),
											rs.getTimestamp("article_update"),
											rs.getInt("article_readcnt"),
											rs.getString("isshow"),
											rs.getInt("user_no"),
											rs.getString("free_category"));
									freeBoardList.add(list);
							
}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(rs);
			JdbcUtil.close(conn);
		}
		System.out.println("doa3"+freeBoardList);
		return freeBoardList;
	}
	
		// 카테고리로 글 검색 DAO
		public List<FreeBoardList> searchBoard(String categorySearch, String input) {
			
			System.out.println();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql="select a.*,f.free_category" + 
					" from article as a inner join freeboard as f" + 
					" on a.article_no=f.article_no" + 
					" where a.isshow='Y' and f.free_category=? and a.article_title like ?" + 
					" ORDER BY article_no DESC";
			
			List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
			Connection conn = null;
			try {
				conn=ConnectionProvider.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categorySearch);
				stmt.setString(2, "%"+input+"%");
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					FreeBoardList list = new FreeBoardList(
												rs.getInt("article_no"),
												rs.getString("article_category"),
												rs.getString("article_title"),
												rs.getString("user_name"),
												rs.getString("article_content"),
												rs.getTimestamp("article_credate"),
												rs.getTimestamp("article_update"),
												rs.getInt("article_readcnt"),
												rs.getString("isshow"),
												rs.getInt("user_no"),
												rs.getString("free_category"));
										freeBoardList.add(list);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stmt);
				JdbcUtil.close(rs);
				JdbcUtil.close(conn);
			}
			System.out.println("doa3"+freeBoardList);
			return freeBoardList;
		}
		
		
		
		// 카테고리로 글 검색 DAO
				public List<FreeBoardList> mypageSearchBoard(String categorySearch, String input,String loginName) {
					
					System.out.println();
					PreparedStatement stmt = null;
					ResultSet rs = null;
					
					String sql="select a.*,f.free_category" + 
							" from article as a inner join freeboard as f" + 
							" on a.article_no=f.article_no" + 
							" where a.user_name=? and a.isshow='Y' and a.article_title like ? and f.free_category like ?" + 
							" ORDER BY article_no DESC";
					
					List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
					Connection conn = null;
					try {
						conn=ConnectionProvider.getConnection();
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, loginName);
						stmt.setString(2, "%"+input+"%");
						stmt.setString(3, "%"+categorySearch+"%");
						
						rs = stmt.executeQuery();
						
						while(rs.next()) {
							FreeBoardList list = new FreeBoardList(
														rs.getInt("article_no"),
														rs.getString("article_category"),
														rs.getString("article_title"),
														rs.getString("user_name"),
														rs.getString("article_content"),
														rs.getTimestamp("article_credate"),
														rs.getTimestamp("article_update"),
														rs.getInt("article_readcnt"),
														rs.getString("isshow"),
														rs.getInt("user_no"),
														rs.getString("free_category"));
												freeBoardList.add(list);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						JdbcUtil.close(stmt);
						JdbcUtil.close(rs);
						JdbcUtil.close(conn);
					}
					System.out.println("doa3"+freeBoardList);
					return freeBoardList;
				}
	
	
	// 게시글 작성
	public int insertBoard(int articleNo,int userNo,String articleCategory,String categorySearch) {
		
		
		PreparedStatement stmt = null;
		String sql="insert into freeboard (article_no,user_no,article_category,free_category)" + 
				"values(?,?,?,?)";
		
		Connection conn = null;
		int cnt = 0;
		try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,articleNo);
			stmt.setInt(2, userNo);
			stmt.setString(3, articleCategory);
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
	public int updateBoard(String no, String title, String content, String freeCategory) {
		PreparedStatement stmt = null;
		int result = -1;
		
		String sql="update article as a" + 
				" inner join freeboard as f on (f.article_no= a.article_no)" + 
				" set a.article_title=? , a.article_content=? , f.free_category=?" + 
				" where a.article_no=?";
		
		
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, freeCategory);
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
		
		String sql="update article as a " + 
				" inner join freeboard as f on (f.article_no= a.article_no)" + 
				" set a.isshow='N'" + 
				" where a.article_no=?";
		

		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, no);
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
	

	// 게시글 생성전에 article_no 생성해주는 쿼리문.
	public int freeArticleCreate(Connection conn,String articleCategory,String title,String loginName,String content,int userNo) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs=null;
		String sql = "insert into article (article_category,article_title,user_name,article_content,user_no)" + 
				"values (?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, articleCategory);
			pstmt.setString(2, title);
			pstmt.setString(3, loginName);
			pstmt.setString(4, content);
			pstmt.setInt(5, userNo);
			int result = pstmt.executeUpdate();
			
			conn.commit();
			
			int articleNo =0;
			if(result>0) {
				String sql2 = "select last_insert_id() from article";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql2);
				if(rs.next()) {
					articleNo=rs.getInt(1);
					}
				}
				return articleNo;
			}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	//내가 쓴 글 보기 
	public List<FreeBoardList> selectMypageArticle(String mypageUserName) {
		
		System.out.println();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="select a.*,f.free_category" + 
				" from article as a inner join freeboard as f " + 
				" on a.article_no=f.article_no " + 
				" where a.user_name=? and isshow='Y'" + 
				" ORDER BY article_no DESC";
		
		List<FreeBoardList> freeBoardList = new ArrayList<FreeBoardList>();
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mypageUserName);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				FreeBoardList list = new FreeBoardList(
											rs.getInt("article_no"),
											rs.getString("article_category"),
											rs.getString("article_title"),
											rs.getString("user_name"),
											rs.getString("article_content"),
											rs.getTimestamp("article_credate"),
											rs.getTimestamp("article_update"),
											rs.getInt("article_readcnt"),
											rs.getString("isshow"),
											rs.getInt("user_no"),
											rs.getString("free_category")
						);
						System.out.println("list==="+list);
						freeBoardList.add(list);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(rs);
			JdbcUtil.close(conn);
		}
		System.out.println("doa3"+freeBoardList);
		return freeBoardList;
	}
	
	
	
	
	
	// 조회수 증가
	public int updateCnt(int no) {
		PreparedStatement stmt = null;
		int result = -1;
		
		String sql="update article" + 
					" set article_readcnt=article_readcnt+1" + 
					" where article_no=?";
		

			Connection conn = null;
		
			try {
			conn=ConnectionProvider.getConnection();
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
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
	
	
}





























