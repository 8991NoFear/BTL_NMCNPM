package util;

import java.sql.Connection;

import javax.servlet.ServletRequest;

public class DBUtil {
	public static final String NAME_CONNECTION = "NAME_CONNECTION";
	
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(NAME_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		return (Connection) request.getAttribute(NAME_CONNECTION);
	}
	
}
