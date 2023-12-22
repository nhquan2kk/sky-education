package DB;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection {
	static String url = "jdbc:mysql://127.0.0.1:3306/sky_education?useUnicode=yes&characterEncoding=UTF-8";
	static String username = "root";
	static String password = "0000";
	public static Connection CreatConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
