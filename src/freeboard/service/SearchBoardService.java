package freeboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import freeboard.dao.FreeBoardDAO;
import freeboard.model.FreeBoard;
import freeboard.model.FreeBoardList;
import freeboard.model.FreePage;
import freeboard.model.FreeSearch;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
   
public class SearchBoardService {

	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	private int size = 10;
	
	public FreePage search(int pageNum,String category,String input) {
		Connection conn = null;
		
	try {
		conn = ConnectionProvider.getConnection();
		
		int total = freeBoardDAO.selectCount(conn);
		
		FreeSearch search =  new FreeSearch((pageNum-1)*size,size,category,input);
		
		List<FreeBoardList> freeBoardList = freeBoardDAO.searchBoard(conn,search);
		
		return new FreePage(total,pageNum,size,freeBoardList);
		
		
		} catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	public FreeBoard mypageSearch(String categorySearch,String input,String loginName) {
		
		List<FreeBoardList> list = freeBoardDAO.mypageSearchBoard(categorySearch,input,loginName);
		
			return new FreeBoard(list);
		}
}
