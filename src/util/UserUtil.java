package util;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

public class UserUtil {
	// cookie <name, value>
	public static final String NAME_CONNECTION = "NAME_CONNECTION";
	public static final String NAME_USERNAME = "NAME_USERNAME";
	public static final String NAME_PASSWORD = "NAME_PASSWORD";
	
	// session <key, value>
	public static final String KEY_LOGINED_USER = "KEY_LOGINED_USER";
	
	// FOR CONNECTION
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(NAME_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		return (Connection) request.getAttribute(NAME_CONNECTION);
	}
	
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
	
}

