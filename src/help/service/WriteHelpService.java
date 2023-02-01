package help.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import auth.model.Article;
import help.dao.HelpDAO;
import help.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteHelpService {

	HelpDAO helpDAO = new HelpDAO();
	public int articleReq(int userNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int articleNo = helpDAO.articleReq(conn, userNo);
			
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
			
			helpDAO.insert(conn, writerReq);
			
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
