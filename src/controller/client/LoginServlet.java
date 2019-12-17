package controller.client;

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
    
    private User user;
    private boolean hasError;;
    private String error;;
       
    public LoginServlet() {
        super();
        user = null;
        hasError = false;
        error = null;
    }

    // Displaying login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/view/LoginView.jsp
        // (because user can not direct access to any jsp pages in WEB-INF directory)
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/LoginView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // When user enter username & password and Submit.
    // this method will execute
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Load Parameter from login form
    	loadParameter(request);
        
        // Check error
        checkError(request);
        
        // Process
        process(request, response);
    }
    
    private void loadParameter(HttpServletRequest request) {
    	username = request.getParameter("username");
        password = request.getParameter("password");
        System.out.println(username + password);
    }
    
    private boolean checkUserInDB(HttpServletRequest request) {
    	// Find user in DB
        try {
        	Connection conn = DBUtil.getStoredConnection(request);
            user = UserUtil.findUser(conn, username, password);
            if (user == null) {
            	error = "username or password invalid!";
            	return true;
            } else {
            	return false;
            }
        } catch (SQLException e) {
            error = e.getMessage();
            return true;
        }
    }
    
    private void checkError(HttpServletRequest request) {
    	boolean userError = checkUserInDB(request);
    	hasError = userError;
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// If has error, forward to /WEB-INF/view/Login.jsp
        if (hasError) {
        	User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            // save info to request attribute before forward. In JSP can access like ${NAME_ERROR}
            request.setAttribute(NAME_ERROR, error);
            request.setAttribute(NAME_USER, user);
 
            // Forward to /WEB-INF/views/client/login.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/LoginView.jsp");
            dispatcher.forward(request, response);
        }
        
        // Else, save user info in session and redirect to /userinfo
        else {
            HttpSession session = request.getSession();
            UserUtil.storeUserInSession(session, user);
            if(user.isAdmin()) {
            	response.sendRedirect(request.getContextPath() + "/admin");
            } else {
            	response.sendRedirect(request.getContextPath() + "/userinfo");
            }
        }
    }

}
