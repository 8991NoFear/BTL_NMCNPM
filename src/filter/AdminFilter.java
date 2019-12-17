package filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import util.UserUtil;

@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {

    public AdminFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User loginedUser = UserUtil.getUserInSession(session);
		String url = req.getRequestURL().toString();
//		if(isAdminURL(req)) {
//			if(loginedUser.isAdmin()) {
//				chain.doFilter(request, response);
//			}
//			HttpServletResponse res = (HttpServletResponse) response;
//			res.sendRedirect(req.getServletContext() + "/login");
//		}
//		chain.doFilter(request, response);
		if(loginedUser != null && loginedUser.isAdmin()) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	private boolean isAdminURL(HttpServletRequest request) {
        // 
        // Servlet Url-pattern: /spath/*
        // 
        // => /spath
        String servletPath = request.getServletPath();
        // => /abc/mnp
        String pathInfo = request.getPathInfo();
 
        String urlPattern = servletPath;
 
        if ((pathInfo != null) && (pathInfo.contains("/admin"))) {
        	urlPattern = servletPath + "/admin/*";
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

}
