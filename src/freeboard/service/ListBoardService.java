package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;
import freeboard.model.FreePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListBoardService {
	
	private FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	private int size = 10;
	
	public FreePage getBoardListAll(int pageNum) {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = freeBoardDAO.selectCount(conn);
			
			
			List<FreeBoardList> freeBoardlist = freeBoardDAO.selectAll(conn,(pageNum-1)*size,size);
			return new FreePage(total,pageNum,size,freeBoardlist);
			} catch (SQLException e) {
				throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
