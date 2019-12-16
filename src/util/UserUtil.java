package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

public class UserUtil {
	// cookie <name, value>
	public static final String NAME_USERNAME = "NAME_USERNAME";
	public static final String NAME_PASSWORD = "NAME_PASSWORD";
	
	// session <key, value>
	public static final String KEY_LOGINED_USER = "KEY_LOGINED_USER";
	
	// FOR SESSION
    public static void storeUserInSession(HttpSession session, User loginedUser) {
        // Trên JSP có thể truy cập thông qua ${KEY_LOGINED_USER}
        session.setAttribute(KEY_LOGINED_USER, loginedUser);
    }
 
    public static User getUserInSession(HttpSession session) {
        User loginedUser = (User) session.getAttribute(KEY_LOGINED_USER);
        return loginedUser;
    }
	
	// FOR COOKIE
	public static void storeUserInCookie(HttpServletResponse response, User user) {
		Cookie cookieForUsername = new Cookie(NAME_USERNAME, user.getUsername());
		Cookie cookieForPassword = new Cookie(NAME_PASSWORD, user.getPassword());
		cookieForUsername.setMaxAge(24*60*60);
		cookieForPassword.setMaxAge(24*60*60);
		response.addCookie(cookieForUsername);
		response.addCookie(cookieForPassword);
	}
	
	public static String getUsernameInCookie(HttpServletRequest request) {
		Cookie cookie = getCookie(request, NAME_USERNAME);
		if(cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}
	
	public static String getPasswordInCookie(HttpServletRequest request) {
		Cookie cookie = getCookie(request, NAME_PASSWORD);
		if(cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}
	
	private static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] arrCookie = request.getCookies();
		if(arrCookie != null) {
			for(Cookie cookie: arrCookie) {
				if(cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	// Xóa cookie của người dùng
	public static void deleteUserCookie(HttpServletRequest request, HttpServletResponse response, User user) {
		System.out.print("delete user cookie");
		Cookie cookieForUsername = getCookie(request, NAME_USERNAME);
		Cookie cookieForPassword = getCookie(request, NAME_PASSWORD);
		cookieForUsername.setMaxAge(0);
		cookieForPassword.setMaxAge(0);
		response.addCookie(cookieForUsername);
		response.addCookie(cookieForPassword);
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * */
	
	public static User findUser(Connection conn, String username, String password) throws SQLException {
        String sql = "Select username, email, password, is_admin from [USER] where username = ? and password = ?;";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setAdmin(rs.getBoolean("is_admin"));
            return user;
        }
        return null;
	}
	 
	public static User findUser(Connection conn, String username) throws SQLException {
		String sql = "Select username, email, password, is_admin from [USER] where username = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
		    User user = new User();
		    user.setUsername(rs.getString("username"));
		    user.setPassword(rs.getString("password"));
		    user.setEmail(rs.getString("email"));
		    user.setAdmin(rs.getBoolean("is_admin"));
		    return user;
        }
        return null;
	}
	
	public static void insertUser(Connection conn, User user) throws SQLException {
		String sql = "Insert into [USER] ( username, email, password, is_admin) values (?, ?, ?, 0)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getPassword());
		pstmt.executeUpdate();
	}
	
	public static void deleteUser(Connection conn, String username) throws SQLException {
		String sql = "Delete from [USER] where username = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		pstmt.executeUpdate();
	}
	
	public static LinkedList<User> getListUser(Connection conn) throws SQLException{
		String sql = "Select username, email, password, is_admin from [USER];";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		LinkedList<User> list = new LinkedList<User>();
		while(rs.next()) {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setAdmin(rs.getBoolean("is_admin"));
			list.add(user);
		}
		return list;
	}
	
}

