package help.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import help.dao.HelpDAO;
import help.model.Help;
import help.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteHelpService {

	HelpDAO helpDAO = new HelpDAO();
	
	public void write(WriterRequest writerReq) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Help help = toHelp(writerReq);
			Help savehelp = helpDAO.insert(conn, help);
			if(savehelp == null) {
				throw new RuntimeException("fail to insert help");
			}
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(Exception e){
			JdbcUtil.rollback(conn);
			throw e;
		}
		finally {
			JdbcUtil.close(conn);
		}
	}
	private Help toHelp(WriterRequest writerReq) {
		Date now = new Date();
		return new Help(null,writerReq.getUserNo(),writerReq.getTitle(),writerReq.getContent()
							,now,now,writerReq.getCategory(),0,writerReq.getUserName(),"Y");
	}
}
