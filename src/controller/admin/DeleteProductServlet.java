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
import util.ProductUtil;

@WebServlet("/admin/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_ERROR = "NAME_ERROR";
	
    public DeleteProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer productID = Integer.valueOf(request.getParameter("productID"));
			Connection conn = DBUtil.getStoredConnection(request);
			ProductUtil.deleteProduct(conn, productID);
			response.sendRedirect(request.getContextPath() + "/admin");
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
