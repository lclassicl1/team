package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import tradeboard.DAO.TradeDAO;
import tradeboard.model.Trade;
import article.DAO.ArticleDAO;
import article.model.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void modify(ModifyRequest modReq,String modCategory) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Trade trade = tradeDAO.selectByNo(conn, modReq.getArticleNo());
			if(trade == null) {
				throw new HelperNotFoundException();
			}
			if(!canModify(modReq.getUserNo(),trade)) {
				throw new PermissionDeniedException();
			}
			
			articleDAO.update(conn, modReq);
			tradeDAO.update(conn, modReq, modCategory);
			
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
