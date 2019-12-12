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
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = (rememberMeStr != null) ? true : false;
 
        User user = null;
        boolean hasError = false;
        String errorString = null;
        
        // Bắt lỗi
        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = UserUtil.getStoredConnection(request);
            try {
                // Tìm user trong DB.
                user = DBUtil.findUser(conn, username, password);
 
                if (user == null) {
                    hasError = true;
                    errorString = "username or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        
        // Trong trường hợp có lỗi,
        // forward (chuyển hướng) tới /WEB-INF/view/Login.jsp
        if (hasError) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
 
            // Lưu các thông tin vào request attribute trước khi forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward (Chuyển tiếp) tới trang /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/LoginView.jsp");
            dispatcher.forward(request, response);
        }
        
        // Trường hợp không có lỗi.
        // Lưu thông tin người dùng vào Session.
        // Và chuyển hướng sang trang userInfo.
        else {
            HttpSession session = request.getSession();
            UserUtil.storeLoginedUser(session, user);
 
            // Nếu người dùng chọn tính năng "Remember Me".
            if (remember) {
                UserUtil.storeUsernameInCookie(response, user);
            }
            // Ngược lại xóa Cookie
            else {
                UserUtil.deleteUserCookie(response, user);
            }
 
            // Redirect (Chuyển hướng) sang trang /UserInfo.
            response.sendRedirect(request.getContextPath() + "/userinfo");
        }
    }

}
