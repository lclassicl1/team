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
		try {
			Connection conn = ConnectionProvider.getConnection();
			
		List<FreeBoardList> list = freeBoardDAO.selectAll();
		return new FreeBoard(list);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
