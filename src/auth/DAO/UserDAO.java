package auth.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import auth.model.ChangePwdRequest;
import auth.model.JoinRequest;
import auth.model.ModifyUserInfoRequest;
import auth.model.User;
import jdbc.JdbcUtil;

public class UserDAO {

	public User selectById(Connection conn , String loginId)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "select * from userinfo where user_id=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return coverUser(rs);
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public User selectByNo(Connection conn , int userNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "select * from userinfo where user_no=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return coverUser(rs);
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public int selectCountUser(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from userinfo ";
		
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
	public void join(Connection conn , JoinRequest user)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into userinfo(user_id,user_pwd,user_name,user_hp,user_address,user_email,user_gender,user_skill,user_school,user_birth) "+
				"value(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserHp());
			pstmt.setString(5, user.getUserAddress());
			pstmt.setString(6, user.getUserEmail());
			pstmt.setString(7, user.getUserGender());
			pstmt.setString(8, user.getUserSkill());
			pstmt.setString(9, user.getUserSchool());
			pstmt.setString(10, user.getUserBirth());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public void modifyUser(Connection conn , ModifyUserInfoRequest modiUserInfoReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql ="update userinfo set user_hp=?,user_address=?,user_email=?,user_skill=?,user_school=? where user_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modiUserInfoReq.getUserHp());
			pstmt.setString(2, modiUserInfoReq.getUserAddress());
			pstmt.setString(3, modiUserInfoReq.getUserEmail());
			pstmt.setString(4, modiUserInfoReq.getUserSkill());
			pstmt.setString(5, modiUserInfoReq.getUserSchool());
			pstmt.setInt(6, modiUserInfoReq.getLoginNo());
			
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public void update(Connection conn,ChangePwdRequest changePwdReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql ="update userinfo set user_pwd=? where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, changePwdReq.getNewPwd());
			pstmt.setString(2, changePwdReq.getLoginId());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public void update(Connection conn,String userId,String newPwd)throws SQLException {
		PreparedStatement pstmt = null;
		String sql ="update userinfo set user_pwd=? where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, userId);
			
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
