package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import notice.DAO.NoticeDAO;
import notice.model.NoticePage;
import notice.model.SearchNotice;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SearchNoticeService {

	NoticeDAO noticeDAO = new NoticeDAO();
	private int size = 10;
	
	public NoticePage search(int pageNum, String input){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			int total = noticeDAO.selectCount(conn);
			
			SearchNotice search = new SearchNotice((pageNum-1)*size,size,input);
			
			List<Article> noticeList = noticeDAO.search(conn, search);
			
			
			return new NoticePage(total,pageNum,size,noticeList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
		
	}
}
