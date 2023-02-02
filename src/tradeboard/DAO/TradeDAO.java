package tradeboard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exception.HelperNotFoundException;
import article.model.ModifyRequest;
import tradeboard.model.Trade;
import tradeboard.model.TradeList;
import tradeboard.model.WriterRequest;
import tradeboard.model.SearchTrade;
import jdbc.JdbcUtil;

public class TradeDAO {
	
	
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public void insert(Connection conn,WriterRequest writerReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into tradeBOARD(article_no,article_category,user_no,trade_category) "+ 
				"value(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo()); 
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			pstmt.setString(4, writerReq.getTradeCategory());
			
			pstmt.executeUpdate();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<TradeList> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN tradeboard t ON a.article_no=t.article_no " +
				"where a.isshow='Y' order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<TradeList> tradeList = new ArrayList<>();
			while(rs.next()) {
				TradeList tradeList1 = coverTradeList(rs);
				if(tradeList1 != null) {
					tradeList.add(tradeList1);
				}
			}
			return tradeList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<TradeList> search(Connection conn,SearchTrade search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN tradeboard t ON a.article_no=t.article_no where a.isshow='Y' and t.trade_category like ? and a.article_title like ? " + 
				 "order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<TradeList> tradeList = new ArrayList<>();
			while(rs.next()) {
				TradeList tradeList1 = coverTradeList(rs);
				if(tradeList1 != null) {
					tradeList.add(tradeList1);
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
				trade = coverTrade(rs);
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
	
	
	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from article where isshow='Y' and article_category='trade' ";
		
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
	public void update(Connection conn,ModifyRequest modReq,String modCategory)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update tradeboard set trade_category=? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,modCategory);
			pstmt.setInt(2, modReq.getArticleNo());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	private Trade coverTrade(ResultSet rs) throws SQLException {
		return new Trade(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"),
						rs.getString("trade_category"));
	}
	private TradeList coverTradeList(ResultSet rs)throws SQLException {
		return new TradeList(rs.getInt("article_no"),rs.getString("article_category"),rs.getString("article_title"),rs.getString("user_name"),rs.getString("article_content"),rs.getTimestamp("article_credate"),
					rs.getTimestamp("article_update"),rs.getInt("article_readcnt"),rs.getString("isshow"),rs.getInt("user_no"),rs.getString("trade_category"));
	}
}
