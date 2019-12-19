package controller.client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Order;
import bean.Product;
import bean.User;
import model.Cart;
import util.DBUtil;
import util.OrderUtil;
import util.ProductUtil;
import util.UserUtil;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_CART = "NAME_CART";
	
	private LinkedList<Order> listOrder;
       
    public Checkout() {
        super();
        listOrder = new LinkedList<Order>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute(NAME_CART);
		request.setAttribute(NAME_CART, cart);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/CheckoutView.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBUtil.getStoredConnection(request);
		HttpSession session = request.getSession();
		User user = UserUtil.getUserInSession(session);
		String name = request.getParameter("name");
		Integer phone = Integer.valueOf(request.getParameter("phone"));
		String address = request.getParameter("address");
		
		Cart cart = (Cart) session.getAttribute("NAME_CART");
		
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else if(cart != null) {
			for(Product product: cart.getListProduct()) {
				Order order = new Order();
				order.setAddress(address);
				order.setConfirm(false);
				order.setDateCreated(new Timestamp(System.currentTimeMillis()));
				order.setName(name);
				order.setPhone(phone);
				order.setProductID(product.getProductID());
				order.setQuantity(product.getQuantity());
				order.setUsername(user.getUsername());
				listOrder.add(order);
				
				Product productInDB;
				try {
					productInDB = ProductUtil.findProduct(conn, product.getProductID());
					productInDB.setQuantity(productInDB.getQuantity() - product.getQuantity());
					ProductUtil.updateProduct(conn, product.getProductID(), productInDB);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			cart.getListProduct().clear();
			
			for(Order order: listOrder) {
				try {
					OrderUtil.insertOrder(conn, order);
				} catch (SQLException e) {
					e.printStackTrace();
				};
			}
			listOrder.clear();
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}
