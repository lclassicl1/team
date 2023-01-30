package helperComment.service;

import java.sql.Connection;
import java.sql.SQLException;

import Exception.CommentNotFoundException;
import Exception.canNotDeleteException;
import helperComment.DAO.CommentDAO;
import helperComment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class DeleteCommentService {

	CommentDAO commentDAO = new CommentDAO();
	
	public void commDelete(int commNo,String loginId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Comment comment = commentDAO.selectByNo(conn, commNo);//댓글번호로부터 받은 데이터
			//댓글번호로부터 가져온 데이터가 없은경우 
			if(comment==null) {
				throw new CommentNotFoundException();
			}
			//댓글번호로부터 가져온 유저정보와 로그인데이터가 맞지 않은경우
			if(!comment.getUserId().equals(loginId)) {
				throw new canNotDeleteException();
			}
			//사용자 권한으로 댓글삭제시 
			commentDAO.isshow(conn, commNo);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
