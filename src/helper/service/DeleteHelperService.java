package helper.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.PermissionDeniedException;
import article.DAO.ArticleDAO;
import helper.dao.HelperDAO;
import helper.model.Helper;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class DeleteHelperService {

	HelperDAO helperDAO = new HelperDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void delete(int articleNo,int userNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Helper helper = helperDAO.selectByNo(conn, articleNo);
			
			//작성자의 유저 번호와 로그인한 유저번호를 매치 불일치시 에러  
			if(!(helper.getUserNo()==userNo)) {
				throw new PermissionDeniedException();
			}
			
			articleDAO.isshow(conn, articleNo);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
