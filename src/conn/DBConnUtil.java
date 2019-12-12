package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
	public static final String USERNAME = "admin";
	public static final String PASSWORD = "admin123";
	public static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BTL_NMCNPM";
	public static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}
	
	public static void closeConnection(Connection conn) throws SQLException {
			conn.close();
	}
	
	public static void rollback(Connection conn) throws SQLException {
			conn.rollback();
	}
}
