package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import notice.DAO.NoticeDAO;
import notice.model.NoticePage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class ListNoticeService {

	private NoticeDAO noticeDAO = new NoticeDAO();
	private int size = 10;
	
	public NoticePage getNoticePage(int pageNum) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = noticeDAO.selectCount(conn);
			
			List<Article> noticeList = noticeDAO.select(conn, (pageNum-1)*size, size);
			return new NoticePage(total,pageNum,size,noticeList);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
