package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import auth.DAO.UserDAO;
import auth.model.User;
import tradeboard.DAO.TradeDAO;
import tradeboard.model.Trade;
import tradeboard.model.UserInfoTradeInfo;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ReadTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	UserDAO userDAO = new UserDAO();
	
	public UserInfoTradeInfo getTrade(int tradeNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Trade trade = tradeDAO.selectByNo(conn, tradeNo);
			
			if(trade == null) {
				throw new HelperNotFoundException();
			}
			
			int writerNo = trade.getUserNo();
			User user = userDAO.selectByNo(conn, writerNo);
			
			
			if(incrementReadCnt) {
				tradeDAO.incrementReadCnt(conn, tradeNo);
			}
			
			UserInfoTradeInfo usertrade = new UserInfoTradeInfo(user,trade);
			
			conn.commit();
			return usertrade;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
