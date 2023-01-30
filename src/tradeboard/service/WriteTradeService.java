package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import tradeboard.DAO.TradeDAO;
import tradeboard.model.Trade;
import tradeboard.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	
	public void write(WriterRequest writerReq) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Trade trade = toTrade(writerReq);
			Trade savetrade = tradeDAO.insert(conn, trade);
			if(savetrade == null) {
				throw new RuntimeException("fail to insert trade");
			}
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(Exception e){
			JdbcUtil.rollback(conn);
			throw e;
		}
		finally {
			JdbcUtil.close(conn);
		}
	}
	private Trade toTrade(WriterRequest writerReq) {
		Date now = new Date();
		return new Trade(null,writerReq.getUserNo(),writerReq.getTitle(),writerReq.getContent()
							,now,now,0,writerReq.getUserName(),"Y");
	}
}
