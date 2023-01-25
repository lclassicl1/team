package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//jdbcutil 클래스, 객체를 닫고, 롤백에 대한 기능을 제공하는 클래스
public class JdbcUtil {
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				 rs.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if( stmt != null) {
			try {
				 stmt.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public static void close(Connection conn) {
		if( conn != null) {
			try {
				 conn.close();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void rollback(Connection conn) {
		if( conn != null) {
			try {
				 conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
} //class의 끝

