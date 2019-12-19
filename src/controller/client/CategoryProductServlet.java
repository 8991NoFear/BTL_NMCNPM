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

@WebServlet("/categoryProduct")
public class CategoryProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String NAME_CATEGORY = "NAME_CATEGORY";
	private final String NAME_LIST_CATEGORY_PRODUCT = "NAME_LIST_CATEGORY_PRODUCT";
	
	private Category category;
	private LinkedList<Product> listProduct;
       
    public CategoryProductServlet() {
        super();
        category = new Category();
        listProduct = new LinkedList<Product>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer categoryID = Integer.valueOf(request.getParameter("categoryID"));
		Connection conn = DBUtil.getStoredConnection(request);
		try {
			category = CategoryUtil.findCategory(conn, categoryID);
			listProduct = ProductUtil.getListCategoryProduct(conn, categoryID);
			request.setAttribute(NAME_CATEGORY, category);
			request.setAttribute(NAME_LIST_CATEGORY_PRODUCT, listProduct);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/client/CategoryProductView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
