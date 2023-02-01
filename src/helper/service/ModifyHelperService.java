package helper.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import helper.dao.HelperDAO;
import helper.model.Helper;
import helper.model.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyHelperService {

	HelperDAO helperDAO = new HelperDAO();
	
	public void modify(ModifyRequest modReq) {
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
			
			helperDAO.update(conn, modReq.getModTitle(), modReq.getModContent(), modReq.getModCategory(), modReq.getArticleNo());
			
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
