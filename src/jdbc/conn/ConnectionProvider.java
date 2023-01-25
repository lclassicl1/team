package jdbc.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//web.xml에서 설정한 poolName=board으로 지정한 board를 풀 이름으로 사용하여 Connection을 제공하는 클래스
public class ConnectionProvider {
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:gosu");
		
	}
	
}
