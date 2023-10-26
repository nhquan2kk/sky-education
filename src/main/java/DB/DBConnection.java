package DB;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection {
	static String url = "jdbc:mysql://localhost:3306/sky_education";
	static String username = "root";
	static String password = "4238";
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
