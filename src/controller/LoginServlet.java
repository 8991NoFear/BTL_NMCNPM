package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import util.DBUtil;
import util.UserUtil;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_USER = "NAME_USER";
	private final String NAME_ERROR = "NAME_ERROR";
	
	// LOGIN INFO
	private String username;
    private String password;
    private boolean remember;
    
    private User user;
    private boolean hasError;
    private String error;
       
    public LoginServlet() {
        super();
    }

    // Hiển thị trang Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward tới trang /WEB-INF/view/LoginView.jsp
        // (Người dùng không thể truy cập trực tiếp
        // vào các trang JSP đặt trong thư mục WEB-INF).
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/LoginView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // Khi người nhập username & password, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Load Parameter from login form
    	loadParameter(request);
        
        // Check user in DB
        checkUser(request);
        
        // Process
        process(request, response);
    }
    
    private void loadParameter(HttpServletRequest request) {
    	username = request.getParameter("username");
        password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        remember = (rememberMeStr != null) ? true : false;
        
        user = null;
        hasError = false;
        error = null;
    }
    
    private void checkUser(HttpServletRequest request) {
    	// Find user in DB
        try {
        	Connection conn = UserUtil.getStoredConnection(request);
            user = DBUtil.findUser(conn, username, password);
            if (user == null) {
            	hasError = true;
            	error = "username or password invalid";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            hasError = true;
            error = e.getMessage();
        }
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// If has error, forward to /WEB-INF/view/Login.jsp
        if (hasError) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
 
            // save info to request attribute before forward. In JSP can access like ${NAME_ERROR}
            request.setAttribute(NAME_ERROR, error);
            request.setAttribute(NAME_USER, user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/LoginView.jsp");
            dispatcher.forward(request, response);
        }
        
        // Else, save user info in session and redirect to /userinfo
        else {
        	// auto login
        	if (remember) {
                UserUtil.storeUserInCookie(response, user);
            }
        	
            HttpSession session = request.getSession();
            UserUtil.storeUserInSession(session, user);
            response.sendRedirect(request.getContextPath() + "/userinfo");
        }
    }

}
