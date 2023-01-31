package help.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import help.dao.HelpDAO;
import help.model.Help;
import help.model.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyHelpService {

	HelpDAO helpDAO = new HelpDAO();
	
	public void modify(ModifyRequest modReq) {
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
			
			helpDAO.update(conn, modReq.getModTitle(), modReq.getModContent(), modReq.getModCategory(), modReq.getArticleNo());
			
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
