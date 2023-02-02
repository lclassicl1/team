package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.HelperNotFoundException;
import Exception.PermissionDeniedException;
import article.DAO.ArticleDAO;
import notice.DAO.NoticeDAO;
import notice.model.Notice;
import article.model.ModifyRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyNoticeService {

	NoticeDAO noticeDAO = new NoticeDAO();
	ArticleDAO articleDAO = new ArticleDAO();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Notice notice = noticeDAO.selectByNo(conn, modReq.getArticleNo());
			if(notice == null) {
				throw new HelperNotFoundException();
			}
			if(!canModify(modReq.getUserNo(),notice)) {
				throw new PermissionDeniedException();
			}
			articleDAO.update(conn,modReq);
			
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
	private boolean canModify(int loginNo,Notice notice) {
		int writer = notice.getUserNo();
		return writer == loginNo;
	}
}
