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

import bean.Category;
import bean.Order;
import bean.Product;
import bean.ProductDetail;
import bean.User;
import util.CategoryUtil;
import util.DBUtil;
import util.OrderUtil;
import util.ProductDetailUtil;
import util.ProductUtil;
import util.UserUtil;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_LIST_CATEGORY = "NAME_LIST_CATEGORY";
	private final String NAME_LIST_PRODUCT = "NAME_LIST_PRODUCT";
	private final String NAME_LIST_PRODUCT_DETAIL = "NAME_LIST_PRODUCT_DETAIL";
	private final String NAME_LIST_USER = "NAME_LIST_USER";
	private final String NAME_LIST_ORDER = "NAME_LIST_ORDER";
	
	LinkedList<User> listUser;
	LinkedList<Product> listProduct;
	LinkedList<ProductDetail> listProductDetail;
	LinkedList<Order> listOrder;
	LinkedList<Category> listCategory;
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBUtil.getStoredConnection(request);
			
			listUser = UserUtil.getListUser(conn);
			listProduct = ProductUtil.getListProduct(conn);
			listProductDetail = ProductDetailUtil.getListProductDetail(conn);
			listOrder = OrderUtil.getListOrder(conn);
			listCategory = CategoryUtil.getListCategory(conn);
			
			request.setAttribute(NAME_LIST_USER, listUser);
			request.setAttribute(NAME_LIST_PRODUCT, listProduct);
			request.setAttribute(NAME_LIST_PRODUCT_DETAIL, listProductDetail);
			request.setAttribute(NAME_LIST_ORDER, listOrder);
			request.setAttribute(NAME_LIST_CATEGORY, listCategory);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/AdminView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
