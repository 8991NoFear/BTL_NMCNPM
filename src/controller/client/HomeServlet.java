package controller.client;

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
import bean.Product;
import util.CategoryUtil;
import util.DBUtil;
import util.ProductUtil;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_LIST_CATEGORY = "NAME_LIST_CATEGORY";
	private final String NAME_LIST_TRENDING_PRODUCT = "NAME_LIST_TRENDING_PRODUCT";
	private final String NAME_LIST_ALL_PRODUCT = "NAME_LIST_ALL_PRODUCT";
	
	private LinkedList<Category> listCategory;
	private LinkedList<Product> listTrendingProduct;
	private LinkedList<Product> listAllProduct;
	
    public HomeServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBUtil.getStoredConnection(request);
			listCategory = CategoryUtil.getListCategory(conn);
			listTrendingProduct = ProductUtil.getListTrendingProduct(conn);
	    	listAllProduct = ProductUtil.getListProduct(conn);
	    	request.setAttribute(NAME_LIST_CATEGORY, listCategory);
	    	request.setAttribute(NAME_LIST_TRENDING_PRODUCT, listTrendingProduct);
	    	request.setAttribute(NAME_LIST_ALL_PRODUCT, listAllProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/HomeView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
