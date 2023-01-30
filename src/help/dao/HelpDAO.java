package help.dao;

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
import help.model.Help;
import help.model.SearchHelp;
import jdbc.JdbcUtil;

public class HelpDAO {
	
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public Help insert(Connection conn,Help help)throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "insert into helpBOARD(user_no,help_title,help_content,help_update,help_category,user_name) "+ 
				"value(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, help.getUserNo());//user_no 
			pstmt.setString(2, help.getHelpTitle());
			pstmt.setString(3, help.getHelpContent());
			pstmt.setTimestamp(4, toTimestamp(help.getUpdate()));//update now()
			pstmt.setString(5, help.getCategory());
			pstmt.setString(6, help.getUserName());//writerName
			
			int insertCount = pstmt.executeUpdate();
			
			if(insertCount>0) {
				stmt=conn.createStatement();
				String sql2 = "select last_insert_id() from helpboard";
				rs = stmt.executeQuery(sql2);
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Help(newNum,help.getUserNo(),help.getHelpTitle(),help.getHelpContent(),help.getCreateDate(),help.getUpdate()
									,help.getCategory(),help.getReadCnt(),help.getUserName(),help.getIsshow());
				}
			}
			return null;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<Help> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helpboard " +
						"where isshow='Y' " + 
						"order by help_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Help> helpList = new ArrayList<>();
			while(rs.next()) {
				Help help = coverHelp(rs);
				if(help != null) {
					helpList.add(help);
				}
			}
			return helpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<Help> search(Connection conn,SearchHelp search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helpboard " +
						"where isshow='Y' and help_category like ? and help_title like ? " + 
						 "order by help_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<Help> helpList = new ArrayList<>();
			while(rs.next()) {
				Help help = coverHelp(rs);
				if(help != null) {
					helpList.add(help);
				}
			}
			return helpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Help selectByNo(Connection conn,int helpNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helpboard where help_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, helpNo);
			rs = pstmt.executeQuery();
			Help help = null;
			
			if(rs.next()) {
				help = coverHelp(rs);
			}
			if(help == null) {
				throw new HelperNotFoundException();
			}
			return help;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void incrementReadCnt(Connection conn,int helpNo) {
		PreparedStatement pstmt = null;
		String sql ="update helpboard set help_readcnt = help_readcnt+1 where help_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, helpNo);
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
		String sql = "select count(*) from helpboard where isshow='Y' ";
		
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
	public void update(Connection conn,String title,String content,String category,int helpNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helpboard set help_title=?,help_content=?, help_category=? where help_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setString(3, category);
			pstmt.setInt(4, helpNo);
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void isshow(Connection conn,int helpNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helpboard set isshow='N' where help_no=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, helpNo);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	private Help coverHelp(ResultSet rs) throws SQLException {
		
		return new Help(rs.getInt("help_no"),rs.getInt("user_no"), rs.getString("help_title"),rs.getString("help_content")
						,rs.getTimestamp("help_credate"),rs.getTimestamp("help_update"),rs.getString("help_category"),rs.getInt("help_readcnt")
						,rs.getString("user_name"),rs.getString("isshow"));
	}
}
