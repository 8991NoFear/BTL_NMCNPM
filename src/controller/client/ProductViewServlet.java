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

import bean.Product;
import util.DBUtil;
import util.ProductUtil;

@WebServlet("/product")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_PRODUCT = "NAME_PRODUCT";
       
    public ProductViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer productID = Integer.valueOf(request.getParameter("productID"));
			Connection conn = DBUtil.getStoredConnection(request);
			Product product = ProductUtil.findProduct(conn, productID);
			request.setAttribute(NAME_PRODUCT, product);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/client/ProductView.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
