package freeboard.service;

import java.sql.Connection;

import freeboard.dao.FreeBoardDAO;
import jdbc.conn.ConnectionProvider;

//article_no 생성 service
public class ArticleBoardService {
	FreeBoardDAO freeBoardDAO = new FreeBoardDAO();
	public int freeArticleInsert(String articleCategory,String title,String loginName,String content,int userNo){
		try {
			Connection conn = ConnectionProvider.getConnection();
			int articleNo = freeBoardDAO.freeArticleCreate(conn,articleCategory,title,loginName,content,userNo);
			return articleNo;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ArticleBoardService error");
		}
	}
	
	
	
	
	
	
}
