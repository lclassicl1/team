package helper.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import article.DAO.ArticleDAO;
import article.model.ModifyRequest;
import helper.dao.HelperDAO;
import helper.model.Helper;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyHelperService {

	HelperDAO helperDAO = new HelperDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void modify(ModifyRequest modReq,String modCategory) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Helper helper = helperDAO.selectByNo(conn, modReq.getArticleNo());
			if(helper == null) {
				throw new HelperNotFoundException();
			}
			if(!canModify(modReq.getUserNo(),helper)) {
				throw new PermissionDeniedException();
			}
			articleDAO.update(conn, modReq);
			helperDAO.update(conn,modReq,modCategory);
			
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
	private boolean canModify(int loginNo,Helper helper) {
		int writer = helper.getUserNo();
		return writer == loginNo;
	}
}
