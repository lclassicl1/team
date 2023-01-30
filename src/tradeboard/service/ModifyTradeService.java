package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import tradeboard.DAO.TradeDAO;
import tradeboard.model.Trade;
import tradeboard.model.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Trade trade = tradeDAO.selectByNo(conn, modReq.getTradeNo());
			if(trade == null) {
				throw new HelperNotFoundException();
			}
			if(!canModify(modReq.getUserNo(),trade)) {
				throw new PermissionDeniedException();
			}
			
			tradeDAO.update(conn, modReq.getModTitle(), modReq.getModContent(),  modReq.getTradeNo());
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e){
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private boolean canModify(int loginNo,Trade trade) {
		int writer = trade.getUserNo();
		return writer == loginNo;
	}
}
