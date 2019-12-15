package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import conn.DBConnUtil;
import util.DBUtil;

public class JDBCFilter implements Filter {
	private int count = 0;

    public JDBCFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	// Kiểm tra mục tiêu của request hiện tại là 1 Servlet?
    private boolean needJDBC(HttpServletRequest request) {
        // 
        // Servlet Url-pattern: /spath/*
        // 
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();
 
        String urlPattern = servletPath;
 
        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }
 
        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
 
        // Tập hợp tất cả các Servlet trong WebApp của bạn.
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	System.out.println("[" + (++count) +"]\n- JDBC Filter");
        HttpServletRequest req = (HttpServletRequest) request;
 
        // Chỉ mở connection (kết nối) đối với các request có đường dẫn đặc biệt.
        // (Chẳng hạn đường dẫn tới các servlet, jsp, ..)
        // 
        // Tránh tình trạng mở Connection với các yêu cầu thông thường.
        // (Chẳng hạn image, css, javascript,... )
        
        if (this.needJDBC(req)) {
 
            System.out.println("Open Connection for: " + req.getServletPath());
 
            Connection conn = null;
            try {
                // Tạo đối tượng Connection kết nối database.
                conn = DBConnUtil.getConnection();
                
                // Sét tự động commit false, để chủ động điều khiển.
                conn.setAutoCommit(false);
                
                // Lưu trữ đối tượng Connection vào attribute của request.
                DBUtil.storeConnection(request, conn);
 
                // Cho phép request đi tiếp.
                // (Đi tới Filter tiếp theo hoặc đi tới mục tiêu).
                chain.doFilter(request, response);
 
                // Gọi phương thức commit() để hoàn thành giao dịch với DB.
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
					DBConnUtil.rollback(conn);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                throw new ServletException();
            } finally {
                try {
					DBConnUtil.closeConnection(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
        
        // Với các request thông thường (image,css,html,..)
        // không cần mở connection.
        
        else {
            // Cho phép request đi tiếp.
            // (Đi tới Filter tiếp theo hoặc đi tới mục tiêu).
        	
            chain.doFilter(request, response);
        }

    }
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
