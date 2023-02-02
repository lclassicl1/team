package helper.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import Exception.HelperNotFoundException;
import Exception.UserNotFoundException;
import article.DAO.ArticleDAO;
import article.model.Article;
import auth.DAO.UserDAO;
import auth.model.User;
import helper.dao.HelperDAO;
import helper.model.Helper;
import helper.model.UserInfoHelperInfo;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ReadHelperService {

	HelperDAO helperDAO = new HelperDAO();
	UserDAO userDAO = new UserDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public UserInfoHelperInfo getHelper(int articleNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Helper helper = helperDAO.selectByNo(conn, articleNo);
			Article article = articleDAO.selectByNo(conn, articleNo);
			
			if(helper == null) {
				throw new HelperNotFoundException();
			}
			if(article == null) {
				throw new ArticleNullException();
			}
			
			int writerNo = helper.getUserNo();
			User user = userDAO.selectByNo(conn, writerNo);
			
			if(user == null) {
				throw new UserNotFoundException();
			}
			
			if(incrementReadCnt) {
				articleDAO.incrementReadCnt(conn, articleNo);
			}
			
			
			conn.commit();
			return new UserInfoHelperInfo(user,article,helper);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
