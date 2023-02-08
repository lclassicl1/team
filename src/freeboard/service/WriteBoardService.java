package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import article.DAO.ArticleDAO;
import article.model.ArticleRequest;
import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeWriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteBoardService {

	
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	private ArticleDAO articleDAO = new ArticleDAO();
	
	public int writetBoard(ArticleRequest articleReq,String categorySearch) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleNo = articleDAO.articleReq(conn, articleReq);
			
			if(articleNo==0) {
				throw new ArticleNullException();
			}
			
			FreeWriterRequest freeWriterReq = new FreeWriterRequest(articleNo,articleReq.getArticleCategory(),articleReq.getUserNo(),categorySearch);
		
			freeBoardDAO.insert(conn, freeWriterReq);
			conn.commit();
			return articleNo;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}









