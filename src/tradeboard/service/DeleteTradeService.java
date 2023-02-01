package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.PermissionDeniedException;
import tradeboard.DAO.TradeDAO;
import tradeboard.model.Trade;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class DeleteTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	
	public void delete(int articleNo,int userNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Trade trade = tradeDAO.selectByNo(conn, articleNo);
			
			//작성자의 유저 번호와 로그인한 유저번호를 매치 불일치시 에러  
			if(!(trade.getUserNo()==userNo)) {
				throw new PermissionDeniedException();
			}
			
			tradeDAO.isshow(conn, articleNo);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
