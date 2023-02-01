package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import Exception.ArticleNullException;
import tradeboard.DAO.TradeDAO;
import tradeboard.model.Trade;
import tradeboard.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	
	public int articleReq(int userNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleNo = tradeDAO.articleReq(conn, userNo);
			
			if(articleNo==0) {
				throw new ArticleNullException();
			}
			
			conn.commit();
			return articleNo;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	public void write(WriterRequest writerReq) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			tradeDAO.insert(conn, writerReq);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
