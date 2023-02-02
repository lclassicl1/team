package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import article.DAO.ArticleDAO;
import article.model.Article;
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
	ArticleDAO articleDAO = new ArticleDAO();
	
	public UserInfoTradeInfo getTrade(int articleNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Trade trade = tradeDAO.selectByNo(conn, articleNo);
			Article article = articleDAO.selectByNo(conn, articleNo);
			
			if(trade == null) {
				throw new HelperNotFoundException();
			}
			
			int writerNo = trade.getUserNo();
			User user = userDAO.selectByNo(conn, writerNo);
			
			
			if(incrementReadCnt) {
				articleDAO.incrementReadCnt(conn, articleNo);
			}
			
			UserInfoTradeInfo usertrade = new UserInfoTradeInfo(user,article,trade);
			
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
