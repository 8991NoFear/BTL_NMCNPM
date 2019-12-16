package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.Order;
import bean.Product;
import bean.User;
import util.CategoryUtil;
import util.DBUtil;
import util.OrderUtil;
import util.ProductUtil;
import util.UserUtil;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_LIST_CATEGORY = "NAME_LIST_CATEGORY";
	private final String NAME_LIST_PRODUCT = "NAME_LIST_PRODUCT";
	private final String NAME_LIST_USER = "NAME_LIST_USER";
	private final String NAME_LIST_ALL_ORDER = "NAME_LIST_ALL_ORDER";
	private final String NAME_LIST_NEW_ORDER = "NAME_LIST_NEW_ORDER";
	
	private LinkedList<User> listUser;
	private LinkedList<Product> listProduct;
	private LinkedList<Order> listOrder;
	private LinkedList<Order> listNewOrder;
	private LinkedList<Category> listCategory;
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User loginedUser = UserUtil.getUserInSession(session);
			 
	        // Check user is admin?
	       
	        
			Connection conn = DBUtil.getStoredConnection(request);
			listUser = UserUtil.getListUser(conn);
			listProduct = ProductUtil.getListProduct(conn);
			listOrder = OrderUtil.getListOrder(conn);
			listNewOrder = OrderUtil.getListNewOrder(conn);
			listCategory = CategoryUtil.getListCategory(conn);
			
			request.setAttribute(NAME_LIST_USER, listUser);
			request.setAttribute(NAME_LIST_PRODUCT, listProduct);
			request.setAttribute(NAME_LIST_ALL_ORDER, listOrder);
			request.setAttribute(NAME_LIST_NEW_ORDER, listNewOrder);
			request.setAttribute(NAME_LIST_CATEGORY, listCategory);
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/AdminView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
