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

import bean.User;
import util.DBUtil;
import util.UserUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward tới trang /WEB-INF/view/RegisterView.jsp
        // (Người dùng không thể truy cập trực tiếp
        // vào các trang JSP đặt trong thư mục WEB-INF).
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/RegisterView.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = (rememberMeStr != null) ? true : false;
		 
        User user = null;
        boolean hasError = false;
        String errorString = null;
        
        // Bắt lỗi
        if (username == null || username.length() == 0 ||
        	email == null || email.length() == 0 ||
        	password == null || password.length() == 0 ||
        	confirmPassword == null || confirmPassword.length() == 0||
        	!password.equals(confirmPassword)) {
        	
        	hasError = true;
        	errorString = "Required username, email, password, confirm password and password must be the same as confirm password!";
        	
        } else {
        	 Connection conn = UserUtil.getStoredConnection(request);
        	 try {
                 // Tìm user trong DB.
                 user = DBUtil.findUser(conn, username);
  
                 if (user != null) {
                     hasError = true;
                     errorString = "username is existed, please choose another username!";
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
                 hasError = true;
                 errorString = e.getMessage();
             }
        }
        
        // Trong trường hợp có lỗi,
        // forward (chuyển hướng) tới /WEB-INF/view/RegisterView.jsp
        
        user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        if (hasError) {
            // Lưu các thông tin vào request attribute trước khi forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward (Chuyển tiếp) tới trang /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/RegisterView.jsp");
            dispatcher.forward(request, response);
        }
        
        // Trường hợp không có lỗi.
        // Lưu thông tin người dùng vào DB.
        // Và chuyển hướng sang trang LoginView
        else {
        	Connection conn = UserUtil.getStoredConnection(request);
        	try {
				DBUtil.addUser(conn, user);
				// Nếu người dùng chọn tính năng "Remember Me".
	            if (remember) {
	                UserUtil.storeUserInCookie(response, user);
	            }
	            // Ngược lại xóa Cookie
	            else {
	                UserUtil.deleteUserCookie(request, response, user);
	            }
	            
	            response.sendRedirect(request.getContextPath() + "/login");
			} catch (SQLException e) {
				e.printStackTrace();
				// Lưu các thông tin vào request attribute trước khi forward.
	            request.setAttribute("errorString", errorString);
	            request.setAttribute("user", user);
	 
	            // Forward (Chuyển tiếp) tới trang /WEB-INF/views/login.jsp
	            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/RegisterView.jsp");
	            dispatcher.forward(request, response);
			}
        }
		
	}

}
