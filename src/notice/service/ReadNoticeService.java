package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.ArticleNullException;
import Exception.HelperNotFoundException;
import Exception.UserNotFoundException;
import article.DAO.ArticleDAO;
import article.model.Article;
import auth.DAO.UserDAO;
import auth.model.User;
import notice.DAO.NoticeDAO;
import notice.model.Notice;
import notice.model.UserInfoNoticeInfo;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ReadNoticeService {

	NoticeDAO noticeDAO = new NoticeDAO();
	UserDAO userDAO = new UserDAO();
	ArticleDAO articleDAO = new ArticleDAO(); 
	
	public UserInfoNoticeInfo getnotice(int articleNo,boolean incrementReadCnt) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Notice notice = noticeDAO.selectByNo(conn, articleNo);
			Article article = articleDAO.selectByNo(conn, articleNo);
			
			if(notice == null) {
				throw new HelperNotFoundException();
			}
			if(article == null) {
				throw new ArticleNullException();
			}
			int writerUserNo = notice.getUserNo();
			
			User user = userDAO.selectByNo(conn, writerUserNo);
			
			if(user == null) {
				throw new UserNotFoundException();
			}
			
			if(incrementReadCnt) {
				articleDAO.incrementReadCnt(conn, articleNo);
			}
			UserInfoNoticeInfo usernotice = new UserInfoNoticeInfo(user,article,notice);
			
			conn.commit();
			return usernotice;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
