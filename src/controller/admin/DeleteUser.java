package controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;
import util.UserUtil;

@WebServlet("/admin/deleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_ERROR = "NAME_ERROR";
	
    public DeleteUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			if(!username.equals("admin")) {
				Connection conn = DBUtil.getStoredConnection(request);
				UserUtil.deleteUser(conn, username);
				response.sendRedirect(request.getContextPath() + "/admin");
			} else {
				request.setAttribute(NAME_ERROR, "Can not delete account for admin!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute(NAME_ERROR, e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/ErrorPage.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
