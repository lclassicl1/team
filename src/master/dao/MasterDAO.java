package master.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import auth.model.User;
import master.model.SearchUser;
import jdbc.JdbcUtil;

public class MasterDAO {

	
	public List<User> selectUser(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM userinfo  " +
						"order by user_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<User> userList = new ArrayList<>();
			while(rs.next()) {
				User user = coverUser(rs);
				if(user != null) {
					userList.add(user);
				}
			}
			return userList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	//검색시 회원등급을 지정시 
	public List<User> searchUser(Connection conn,SearchUser search,int grade)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM userinfo where user_id like ? and user_grade= ?  " + 
						 "order by user_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getInput()+"%");
			pstmt.setInt(2, grade);
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<User> userList = new ArrayList<>();
			while(rs.next()) {
				User user = coverUser(rs);
				if(user != null) {
					userList.add(user);
				}
			}
			return userList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	//검색시 회원등급을 전체로 할때 메소드 
	public List<User> searchUser(Connection conn,SearchUser search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM userinfo where user_name like ? " + 
						 "order by user_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getInput()+"%");
			pstmt.setInt(2, search.getStartRow());
			pstmt.setInt(3, search.getSize());
			rs = pstmt.executeQuery();
			List<User> userList = new ArrayList<>();
			while(rs.next()) {
				User user = coverUser(rs);
				if(user != null) {
					userList.add(user);
				}
			}
			return userList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public void blackUser(Connection conn, int userNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update userinfo set user_grade=900 where user_no=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	
	private User coverUser(ResultSet rs) throws SQLException {
		return new User(rs.getInt("user_no"),rs.getString("user_id"),rs.getString("user_pwd"),rs.getString("user_name"),rs.getString("user_hp")
						,rs.getTimestamp("user_regdate"),rs.getString("user_address"),rs.getInt("user_grade"),rs.getString("user_email")
						,rs.getString("user_gender"),rs.getString("user_skill"),rs.getString("user_school"),rs.getString("user_birth"));
	}

}
