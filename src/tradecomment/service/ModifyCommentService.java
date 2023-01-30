package tradecomment.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.CantModiException;
import Exception.CommentNotFoundException;
import tradecomment.DAO.CommentDAO;
import tradecomment.model.Comment;
import tradecomment.model.ModifyCommRequest;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ModifyCommentService {

	CommentDAO commentDAO =new CommentDAO();
	
	public void modifyComm(ModifyCommRequest modiCommReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Comment comment = commentDAO.selectByNo(conn, modiCommReq.getCommNo());
			
			if(comment==null) {
				throw new CommentNotFoundException();
			}
			if(!comment.getUserId().equals(modiCommReq.getLoginId())) {
				throw new CantModiException();
			}
			
			
			commentDAO.modify(conn, modiCommReq);
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	public Comment selectByNo(int commNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			Comment comment = commentDAO.selectByNo(conn, commNo);
			
			if(comment==null) {
				throw new CommentNotFoundException();
			}
			return comment;
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
