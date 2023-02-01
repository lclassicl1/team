package freeboard.service;

import java.sql.Connection;

import freeboard.dao.FreeBoardDAO;
import jdbc.conn.ConnectionProvider;

//article_no 생성 service
public class ArticleBoardService {
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	public int freeArticleInsert(int userNo){
		System.out.println("freeArticleInsert()진입");
		
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			int articleNo = freeBoardDAO.freeArticleCreate(conn,userNo);
			return articleNo;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
