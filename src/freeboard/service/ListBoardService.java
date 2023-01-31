package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListBoardService {
	
	private FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	
	
	public FreeBoard getBoardListAll() {
		System.out.println("서비스 진입");
		try {
			Connection conn = ConnectionProvider.getConnection();
			System.out.println("db연결 확인");
			
		List<FreeBoardList> list = freeBoardDAO.selectAll();
		System.out.println("서비스 list"+list);
		return new FreeBoard(list);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public int freeArticleInsert(Connection conn, int userNo) {
		
		int articleNo = 0;
		try {
			conn = ConnectionProvider.getConnection();
			articleNo = freeBoardDAO.freeArticleCreate(conn, userNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return articleNo;
	}
}
