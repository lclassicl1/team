package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import tradeboard.DAO.TradeDAO;
import tradeboard.model.TradeList;
import tradeboard.model.TradePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListTradeService {

	private TradeDAO tradeDAO = new TradeDAO();
	private int size = 10;
	
	public TradePage getTradePage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = tradeDAO.selectCount(conn);
			List<TradeList> tradeList = tradeDAO.select(conn, (pageNum-1)*size, size);
			
			return new TradePage(total,pageNum,size,tradeList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
