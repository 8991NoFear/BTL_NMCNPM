package controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Product;
import util.DBUtil;
import util.ProductUtil;

@WebServlet("/admin/editProduct")
@MultipartConfig
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME_ERROR = "NAME_ERROR";
	private static final String NAME_PRODUCT = "NAME_PRODUCT";
	private static final String SAVE_DIRECTORY = "product";
	
	private Integer productID;
	private Integer oldProductID;
	private Integer categoryID;
	private String name;
	private Float price;
	private Integer quantity;
	private String description;
	private String image;
	private Boolean isTrending;
	
	private boolean hasError;
    private String error;
    private Product product;
       
    public EditProductServlet() {
        super();
        product = new Product();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			oldProductID = Integer.valueOf(request.getParameter("productID"));
			Connection conn = DBUtil.getStoredConnection(request);
			product = ProductUtil.findProduct(conn, oldProductID);
			request.setAttribute(NAME_PRODUCT, product);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/EditProductView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkError(request);
		product.setCategoryID(categoryID);
		product.setDescription(description);
		product.setName(name);
		product.setPrice(price);
		product.setProductID(productID);
		product.setQuantity(quantity);
		product.setTrending(isTrending);
		if(hasError) {
			 request.setAttribute(NAME_ERROR, error);
			 request.setAttribute(NAME_PRODUCT, product);
			 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/EditProductView.jsp");
			 dispatcher.forward(request, response);
		} else {
			try {
				// ABSOLUTE PATH
			    String appPath = request.getServletContext().getRealPath("");
			    appPath = appPath.replace('\\', '/');
			
			    // DIR
			    String fullSavePath = null;
			    if (appPath.endsWith("/")) {
			        fullSavePath = appPath + SAVE_DIRECTORY;
			    } else {
			        fullSavePath = appPath + "/img/" + SAVE_DIRECTORY;
			    }
			
			    // MAKE DIR
			    File fileSaveDir = new File(fullSavePath);
			    if (!fileSaveDir.exists()) {
			        fileSaveDir.mkdir();
			    }
			
			    /// PART
			    Part part = request.getPart("image");
			    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			    image = fileName;
			    product.setImage(image);
			    
			    // UPLOAD SUCCEDD
			    if (fileName != null && fileName.length() > 0) {
			    	Connection conn = DBUtil.getStoredConnection(request);
			    	ProductUtil.updateProduct(conn, oldProductID, product);
			        String filePath = fullSavePath + File.separator + fileName;
			        part.write(filePath);
			        response.sendRedirect(request.getContextPath() + "/admin");
			    } else {
			    	error = "file error!";
			    	request.setAttribute(NAME_ERROR, error);
			    	request.setAttribute(NAME_PRODUCT, product);
			    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/EditProductView.jsp");
				    dispatcher.forward(request, response);
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			    error = e.getMessage();
			    request.setAttribute(NAME_ERROR, error);
		    	request.setAttribute(NAME_PRODUCT, product);
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/EditProductView.jsp");
			    dispatcher.forward(request, response);
			}
		}
         
	}
	
	private boolean loadParameter(HttpServletRequest request) {
		try {
			productID = Integer.valueOf(request.getParameter("productID"));
			categoryID = Integer.valueOf(request.getParameter("categoryID"));
			name = request.getParameter("name");
			price = Float.valueOf(request.getParameter("price"));
			quantity = Integer.valueOf(request.getParameter("quantity"));
			description = request.getParameter("description");
			isTrending = (request.getParameter("isTrending") != null) ? true : false;
			return false;
		} catch (NumberFormatException ex) {
			error = ex.getMessage();
			ex.printStackTrace();
			return true;
		}
	}
	
	private void checkError(HttpServletRequest request) {
		boolean loadParameterError = loadParameter(request);
		hasError = loadParameterError;
	}

}
