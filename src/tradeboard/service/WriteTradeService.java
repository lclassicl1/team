package tradeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import article.DAO.ArticleDAO;
import article.model.ArticleRequest;
import tradeboard.DAO.TradeDAO;
import tradeboard.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteTradeService {

	TradeDAO tradeDAO = new TradeDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void writer(ArticleRequest articleReq, String tradeCategory) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleNo = articleDAO.articleReq(conn, articleReq);
			
			if(articleNo==0) {
				throw new ArticleNullException();
			}
			
			WriterRequest writerReq = new WriterRequest(articleNo,articleReq.getArticleCategory(),articleReq.getUserNo(),tradeCategory);
			
			tradeDAO.insert(conn, writerReq);
			
			conn.commit();
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
