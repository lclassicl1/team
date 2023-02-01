package tradeboard.DAO;

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
import tradeboard.model.Trade;
import tradeboard.model.WriterRequest;
import tradeboard.model.SearchTrade;
import jdbc.JdbcUtil;

public class TradeDAO {
	
	public int articleReq(Connection conn,int userNo )throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into article(article_category,user_no) " + 
					"value('trade',?)";
			String sql2 = "select last_insert_id() from article";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			int result = pstmt.executeUpdate();
			int articleNo=0;
			if(result>0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql2);
				if(rs.next()) {
					articleNo = rs.getInt(1);
				}
			}
			return articleNo;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public void insert(Connection conn,WriterRequest writerReq)throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "insert into tradeBOARD(article_no,article_category,user_no,trade_title,trade_content,user_name,trade_update) "+ 
				"value(?,?,?,?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo());//user_no
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			pstmt.setString(4, writerReq.getTradeTitle());
			pstmt.setString(5, writerReq.getTradeContent());
			pstmt.setString(6, writerReq.getUserName());
			
			pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<Trade> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tradeboard " +
						"where isshow='Y' " + 
						"order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Trade> tradeList = new ArrayList<>();
			while(rs.next()) {
				Trade trade = covertrade(rs);
				if(trade != null) {
					tradeList.add(trade);
				}
			}
			return tradeList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<Trade> search(Connection conn,SearchTrade search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tradeboard " +
						"where isshow='Y' and trade_title like ? " + 
						 "order by article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getInput()+"%");
			pstmt.setInt(2, search.getStartRow());
			pstmt.setInt(3, search.getSize());
			rs = pstmt.executeQuery();
			List<Trade> tradeList = new ArrayList<>();
			while(rs.next()) {
				Trade trade = covertrade(rs);
				if(trade != null) {
					tradeList.add(trade);
				}
			}
			return tradeList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Trade selectByNo(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tradeboard where article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			Trade trade = null;
			
			if(rs.next()) {
				trade = covertrade(rs);
			}
			if(trade == null) {
				throw new HelperNotFoundException();
			}
			return trade;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void incrementReadCnt(Connection conn,int articleNo) {
		PreparedStatement pstmt = null;
		String sql ="update tradeboard set trade_readcnt = trade_readcnt+1 where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
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
		String sql = "select count(*) from tradeboard where isshow='Y' ";
		
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
	public void update(Connection conn,String title,String content,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update tradeboard set trade_title=?,trade_content=? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setInt(3, articleNo);
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void isshow(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update tradeboard set isshow='N' where article_no=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Trade covertrade(ResultSet rs) throws SQLException {
		
		return new Trade(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"),rs.getString("trade_title"),rs.getString("trade_content"),rs.getString("user_name"),
				rs.getTimestamp("trade_credate"),rs.getTimestamp("trade_update"),rs.getInt("trade_readcnt"),rs.getString("isshow"));
	}
}
