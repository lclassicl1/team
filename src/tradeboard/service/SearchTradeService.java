package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import tradeboard.DAO.TradeDAO;
import tradeboard.model.Trade;
import tradeboard.model.TradePage;
import tradeboard.model.SearchTrade;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SearchTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	private int size = 10;
	
	public TradePage search(int pageNum, String category, String input){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = tradeDAO.selectCount(conn);
			
			SearchTrade search = new SearchTrade((pageNum-1)*size,size,input);
			
			List<Trade> tradeList = tradeDAO.search(conn, search);
			
			
			return new TradePage(total,pageNum,size,tradeList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
