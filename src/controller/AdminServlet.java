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

import bean.Product;
import bean.User;
import util.DBUtil;
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
	private final String NAME_LIST_CUSTOMER = "NAME_LIST_CUSTOMER";
	
	LinkedList<User> listUser;
	LinkedList<Product> listProduct;
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBUtil.getStoredConnection(request);
			
			listProduct = ProductUtil.getListProduct(conn);
			listUser = UserUtil.getListUser(conn);
			
			request.setAttribute(NAME_LIST_PRODUCT, listProduct);
			request.setAttribute(NAME_LIST_USER, listUser);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/AdminView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
