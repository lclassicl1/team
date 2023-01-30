package helper.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import helper.dao.HelperDAO;
import helper.model.Helper;
import helper.model.WriterRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class WriteHelperService {

	HelperDAO helperDAO = new HelperDAO();
	
	public void write(WriterRequest writerReq) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Helper helper = toHelper(writerReq);
			Helper saveHelper = helperDAO.insert(conn, helper);
			if(saveHelper == null) {
				throw new RuntimeException("fail to insert Helper");
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
	private Helper toHelper(WriterRequest writerReq) {
		Date now = new Date();
		return new Helper(null,writerReq.getUserNo(),writerReq.getTitle(),writerReq.getContent()
							,now,now,writerReq.getCategory(),0,writerReq.getUserName(),"Y");
	}
}
