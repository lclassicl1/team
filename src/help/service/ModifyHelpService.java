package help.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import article.DAO.ArticleDAO;
import help.dao.HelpDAO;
import help.model.Help;
import article.model.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyHelpService {

	HelpDAO helpDAO = new HelpDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void modify(ModifyRequest modReq,String modCategory) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Help help = helpDAO.selectByNo(conn, modReq.getArticleNo());
			if(help == null) {
				throw new HelperNotFoundException();
			}
			if(!canModify(modReq.getUserNo(),help)) {
				throw new PermissionDeniedException();
			}
			
			articleDAO.update(conn,modReq);
			helpDAO.update(conn, modReq,modCategory);
			
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
	private boolean canModify(int loginNo,Help help) {
		int writer = help.getUserNo();
		return writer == loginNo;
	}
}
