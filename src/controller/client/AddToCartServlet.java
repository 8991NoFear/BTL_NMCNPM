package controller.client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import bean.User;
import model.Cart;
import util.DBUtil;
import util.ProductUtil;
import util.UserUtil;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_CART = "NAME_CART";
	
    private Cart cart;   
    public AddToCartServlet() {
        super();
        cart = null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = UserUtil.getUserInSession(session);
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			if(cart == null) {
				cart = new Cart();
			}
			try {
				Integer productID = Integer.valueOf(request.getParameter("productID"));
				Connection conn = DBUtil.getStoredConnection(request);
				Product product = ProductUtil.findProduct(conn, productID);
				cart.getListProduct().add(product);
				session.setAttribute(NAME_CART, cart);
				response.sendRedirect(request.getContextPath() + "/home");
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
