package help.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import article.DAO.ArticleDAO;
import article.model.ArticleRequest;
import help.dao.HelpDAO;
import help.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteHelpService {

	private HelpDAO helpDAO = new HelpDAO();
	private ArticleDAO articleDAO = new ArticleDAO();
	
	public int writer(ArticleRequest articleReq,String helpCategory) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleNo = articleDAO.articleReq(conn, articleReq);
			
			if(articleNo==0) {
				throw new ArticleNullException();
			}
			
			WriterRequest writerReq = new WriterRequest(articleNo,articleReq.getArticleCategory(),articleReq.getUserNo(),helpCategory);
			
			helpDAO.insert(conn, writerReq);
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
