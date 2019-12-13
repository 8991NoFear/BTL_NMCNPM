package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.User;
import util.DBUtil;
import util.UserUtil;

public class CookieFilter implements Filter {

    public CookieFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("- Cookie Filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		// Kiểm tra xem người dùng có trong phiên làm việc hay không, nếu có thì cho đi tiếp
		User userInSession = UserUtil.getUserInSession(session);
		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}
		
		// Trường hợp người dùng mới lần đầu vào web app
		// Connection đã được tạo trong JDBCFilter.
		Connection conn = UserUtil.getStoredConnection(request);
	 
		// Cờ (flag) để kiểm tra Cookie.
		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null && conn != null) {
			String userName = UserUtil.getUsernameInCookie(req);
			try {
				User user = DBUtil.findUser(conn, userName);
				UserUtil.storeUserInSession(session, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		// Đánh dấu đã kiểm tra Cookie.
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

