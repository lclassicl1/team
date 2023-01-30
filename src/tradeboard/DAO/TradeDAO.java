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
import tradeboard.model.Trade;
import tradeboard.model.SearchTrade;
import jdbc.JdbcUtil;

public class TradeDAO {
	
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public Trade insert(Connection conn,Trade trade)throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "insert into tradeBOARD(user_no,trade_title,trade_content,trade_update,user_name) "+ 
				"value(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, trade.getUserNo());//user_no 
			pstmt.setString(2, trade.getTradeTitle());
			pstmt.setString(3, trade.getTradeContent());
			pstmt.setTimestamp(4, toTimestamp(trade.getUpdate()));//update now()
			pstmt.setString(5, trade.getUserName());//writerName
			
			int insertCount = pstmt.executeUpdate();
			
			if(insertCount>0) {
				stmt=conn.createStatement();
				String sql2 = "select last_insert_id() from tradeboard";
				rs = stmt.executeQuery(sql2);
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Trade(newNum,trade.getUserNo(),trade.getTradeTitle(),trade.getTradeContent(),trade.getCreateDate(),trade.getUpdate()
									,trade.getReadCnt(),trade.getUserName(),trade.getIsshow());
				}
			}
			return null;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<Trade> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tradeboard " +
						"where isshow='Y' " + 
						"order by trade_no desc limit ?,? " ;
		
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
						 "order by trade_no desc limit ?,? " ;
		
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
	
	public Trade selectByNo(Connection conn,int tradeNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from tradeboard where trade_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tradeNo);
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
	
	public void incrementReadCnt(Connection conn,int tradeNo) {
		PreparedStatement pstmt = null;
		String sql ="update tradeboard set trade_readcnt = trade_readcnt+1 where trade_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tradeNo);
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
	public void update(Connection conn,String title,String content,int tradeNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update tradeboard set trade_title=?,trade_content=? where trade_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setInt(3, tradeNo);
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void isshow(Connection conn,int tradeNo)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update tradeboard set isshow='N' where trade_no=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tradeNo);
			pstmt.executeUpdate();
					
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	private Trade covertrade(ResultSet rs) throws SQLException {
		
		return new Trade(rs.getInt("trade_no"),rs.getInt("user_no"), rs.getString("trade_title"),rs.getString("trade_content")
						,rs.getTimestamp("trade_credate"),rs.getTimestamp("trade_update"),rs.getInt("trade_readcnt")
						,rs.getString("user_name"),rs.getString("isshow"));
	}
}
