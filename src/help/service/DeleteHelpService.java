package help.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.PermissionDeniedException;
import help.dao.HelpDAO;
import help.model.Help;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class DeleteHelpService {

	HelpDAO helpDAO = new HelpDAO();
	
	public void delete(int helpNo,int userNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Help help = helpDAO.selectByNo(conn, helpNo);
			
			//작성자의 유저 번호와 로그인한 유저번호를 매치 불일치시 에러  
			if(!(help.getUserNo()==userNo)) {
				throw new PermissionDeniedException();
			}
			
			helpDAO.isshow(conn, helpNo);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
