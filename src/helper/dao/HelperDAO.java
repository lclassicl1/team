package helper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Exception.HelperNotFoundException;
import helper.model.Helper;
import helper.model.SearchHelper;
import jdbc.JdbcUtil;

public class HelperDAO {
	
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public Helper insert(Connection conn,Helper helper)throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "insert into HELPERBOARD(user_no,helper_title,helper_content,helper_update,helper_category,user_name) "+ 
				"value(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, helper.getUserNo());//user_no 
			pstmt.setString(2, helper.getHelperTitle());
			pstmt.setString(3, helper.getHelperContent());
			pstmt.setTimestamp(4, toTimestamp(helper.getUpdate()));//update now()
			pstmt.setString(5, helper.getCategory());
			pstmt.setString(6, helper.getUserName());//writerName
			
			int insertCount = pstmt.executeUpdate();
			
			if(insertCount>0) {
				stmt=conn.createStatement();
				String sql2 = "select last_insert_id() from helperboard";
				rs = stmt.executeQuery(sql2);
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Helper(newNum,helper.getUserNo(),helper.getHelperTitle(),helper.getHelperContent(),helper.getCreateDate(),helper.getUpdate()
									,helper.getCategory(),helper.getReadCnt(),helper.getUserName(),helper.getIsshow());
				}
			}
			return null;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<Helper> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helperboard " +
						"where isshow='Y' " + 
						"order by helper_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Helper> helperList = new ArrayList<>();
			while(rs.next()) {
				Helper helper = coverHelper(rs);
				if(helper != null) {
					helperList.add(helper);
				}
			}
			return helperList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<Helper> search(Connection conn,SearchHelper search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helperboard " +
						"where isshow='Y' and helper_category like ? and helper_title like ? " + 
						 "order by helper_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<Helper> helperList = new ArrayList<>();
			while(rs.next()) {
				Helper helper = coverHelper(rs);
				if(helper != null) {
					helperList.add(helper);
				}
			}
			return helperList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Helper selectByNo(Connection conn,int helperNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helperboard where helper_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, helperNo);
			rs = pstmt.executeQuery();
			Helper helper = null;
			
			if(rs.next()) {
				helper = coverHelper(rs);
			}
			if(helper == null) {
				throw new HelperNotFoundException();
			}
			return helper;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void incrementReadCnt(Connection conn,int helperNo) {
		PreparedStatement pstmt = null;
		String sql ="update helperboard set helper_readcnt = helper_readcnt+1 where helper_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, helperNo);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from helperboard where isshow='Y' ";
		
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
	public void update(Connection conn,String title,String content,String category,int helperNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helperboard set helper_title=?,helper_content=?, helper_category=? where helper_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setString(3, category);
			pstmt.setInt(4, helperNo);
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void isshow(Connection conn,int helperNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helperboard set isshow='N' where helper_no=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, helperNo);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	private Helper coverHelper(ResultSet rs) throws SQLException {
		
		return new Helper(rs.getInt("helper_no"),rs.getInt("user_no"), rs.getString("helper_title"),rs.getString("helper_content")
						,rs.getTimestamp("helper_credate"),rs.getTimestamp("helper_update"),rs.getString("helper_category"),rs.getInt("helper_readcnt")
						,rs.getString("user_name"),rs.getString("isshow"));
	}
}
