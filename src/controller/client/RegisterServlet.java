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

import bean.User;
import util.DBUtil;
import util.UserUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_USER = "NAME_USER";
	private final String NAME_ERROR = "NAME_ERROR";
	
	// Register form info
	private String username;
	private String email;
	private String password;
	private String confirmPassword;
	
    private boolean hasError;
    private String error;
       
    public RegisterServlet() {
        super();
        hasError = false;
        error = null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward tới trang /WEB-INF/view/RegisterView.jsp
        // (Người dùng không thể truy cập trực tiếp
        // vào các trang JSP đặt trong thư mục WEB-INF).
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/RegisterView.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Load parameter from register form
		loadParameter(request);
		
        // Check error
		checkError(request);
		
        // process
        process(request, response);
	}
	
	private void loadParameter(HttpServletRequest request) {
		username = request.getParameter("username");
		email = request.getParameter("email");
		password = request.getParameter("password");
		confirmPassword = request.getParameter("confirmPassword");
	}
	    
    private boolean checkUserInDB(HttpServletRequest request) {
		try {
			Connection conn = DBUtil.getStoredConnection(request);
		    User user = UserUtil.findUser(conn, username);
		    if (user != null) {
		        error = "username is existed, please choose another username!";
		        return true;
		    } else {
            	error = "";
            	return false;
            }
		} catch (SQLException e) {
		    error = e.getMessage();
		    return true;
		}
	}
    
    private boolean checkConfirmPassword() {
    	if(!password.equals(confirmPassword)) {
    		error = "confirm password must be the same as password!";
    		return true;
    	} else {
    		return false;
    	}
    }
    
    private void checkError(HttpServletRequest request) {
    	boolean userError = checkUserInDB(request);
    	boolean confirmPasswordError = checkConfirmPassword();
    	hasError = userError || confirmPasswordError;
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(false);
        if (hasError) {
            // save info in request attribute before forward
            request.setAttribute(NAME_ERROR, error);
            request.setAttribute(NAME_USER, user);
 
            // forward to /WEB-INF/views/RegisterView.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/RegisterView.jsp");
            dispatcher.forward(request, response);
        }
        
        // save user info to DB and redirect to /login
        else {
        	try {
        		Connection conn = DBUtil.getStoredConnection(request);
				UserUtil.insertUser(conn, user);
	            response.sendRedirect(request.getContextPath() + "/login");
			} catch (SQLException e) {
				error = e.getMessage();
				// save info in request attribute before forward
	            request.setAttribute(NAME_ERROR, error);
	            request.setAttribute(NAME_USER, user);
	 
	            // forward to /WEB-INF/views/login.jsp
	            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/RegisterView.jsp");
	            dispatcher.forward(request, response);
			}
        }
    }

}
