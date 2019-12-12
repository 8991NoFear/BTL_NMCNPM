package util;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

public class UserUtil {
	// giá trị phải là 1 chuỗi ko có kí tự lạ
	
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_STORING_CONNECTION";
	public static final String ATT_NAME_USER_KEY = "ATTRIBUTE_FOR_STORING_NAME_OF_USER_IN_COOKIE";
	
	// Lưu trữ connection vào attribute của request
	
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		return (Connection) request.getAttribute(ATT_NAME_CONNECTION);
	}
	
	// Lưu trữ thông tin người dùng đã login vào Session.
	
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        // Trên JSP có thể truy cập thông qua ${loginedUser}
    	
        session.setAttribute("loginedUser", loginedUser);
    }
 
    // Lấy thông tin người dùng lưu trữ trong Session
    
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }
	
	// Lưu trữ thông tin người dùng vào cookie
	
	public static void storeUsernameInCookie(HttpServletResponse response, User user) {
		Cookie cookie = new Cookie(ATT_NAME_USER_KEY, user.getUsername());
		cookie.setMaxAge(24*60*60);
		response.addCookie(cookie);
	}
	
	public static String getUsernameInCookie(HttpServletRequest request) {
		Cookie[] cookie = request.getCookies();
		if(cookie != null) {
			for(Cookie ck: cookie) {
				if(ck.getName().equals(ATT_NAME_USER_KEY)) {
					return ck.getValue();
				}
			}
		}
		return null;
	}
	
	// Xóa cookie của người dùng
	
	public static void deleteUserCookie(HttpServletResponse response, User user) {
		System.out.print("delete user cookie");
		Cookie cookie = new Cookie(ATT_NAME_USER_KEY, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
}

