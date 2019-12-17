package controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Product;
import util.DBUtil;
import util.ProductUtil;

@WebServlet("/admin/createProduct")
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME_ERROR = "NAME_ERROR";
	private static final String NAME_PRODUCT = "NAME_PRODUCT";
	private static final String SAVED_DIRECTORY = "product";
	
	private Integer productID;
	private Integer categoryID;
	private String name;
	private Float price;
	private Integer quantity;
	private String description;
	private String image;
	private Boolean isTrending;
	
	private boolean hasError;
    private String error;
    private int turn = 1;
    Product product;
       
    public CreateProductServlet() {
        super();
        product = new Product();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateProductView1.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// First: load info from upload text form
		if(turn == 1) {
			turn = 2;
			checkError(request);
			if(hasError) {
				product.setCategoryID(categoryID);
				product.setProductID(productID);
				product.setName(name);
				product.setPrice(price);
				product.setQuantity(quantity);
				product.setDescription(description);
				product.setTrending(isTrending);
				
				request.setAttribute(NAME_ERROR, error);
		        request.setAttribute(NAME_PRODUCT, product);
		        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateProductView1.jsp");
		        dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateProductView2.jsp");
		        dispatcher.forward(request, response);
			}
			
		}
		
		// Last: load file from upload file form
		else {
			turn = 1;
			try {
			    // Đường dẫn tuyệt đối tới thư mục gốc của web app.
				String appPath = request.getServletContext().getRealPath("");
			    appPath = appPath.replace('\\', '/');
			  
			    // Thư mục để save file tải lên.
			    String fullSavePath = null;
			    if (appPath.endsWith("/")) {
			    	fullSavePath = appPath + SAVED_DIRECTORY;
			    } else {
			    	fullSavePath = appPath + "WebContent/img/" + SAVED_DIRECTORY;
			    }
			  
	            // Tạo thư mục nếu nó không tồn tại.
	            File fileSaveDir = new File(fullSavePath);
	            if (!fileSaveDir.exists()) {
	                fileSaveDir.mkdir();
	            }
	  
			    // Danh mục các phần đã upload lên (Có thể là nhiều file).
	        	Part part = request.getPart("image");
	            String fileName = extractFileName(part);
	            if (fileName != null && fileName.length() > 0) {
	                String filePath = fullSavePath + File.separator + fileName;
	               
	             // Ghi vào file.
	            part.write(filePath);
	            image = fileName;
		        }
		        product.setImage(image);
			    // Upload thành công.
				Connection conn = DBUtil.getStoredConnection(request);
				ProductUtil.insertProduct(conn, product);
				response.sendRedirect(request.getContextPath() + "/admin");
			} catch (SQLException e) {
				error = e.getMessage();
				e.printStackTrace();
				request.setAttribute(NAME_ERROR, error);
		        request.setAttribute(NAME_PRODUCT, product);
		        
		        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateProductView2.jsp");
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
	
	private boolean checkProductInDB(HttpServletRequest request) {
		try {
			Connection conn = DBUtil.getStoredConnection(request);
		    Product product = ProductUtil.findProduct(conn, productID);
		    if (product != null) {
		        error = "Product with id you've given is existed!";
		        return true;
		    } else {
            	error = "";
            	return false;
            }
		} catch (SQLException e) {
		    error = e.getMessage();
		    e.printStackTrace();
		    return true;
		}
	}
	
	private void checkError(HttpServletRequest request) {
		boolean loadParameterError = loadParameter(request);
		boolean checkProductInDBError = checkProductInDB(request);
		hasError = loadParameterError || checkProductInDBError;
	}
	
//	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		checkError(request);
//		Product product = new Product();
//		product.setCategoryID(categoryID);
//		product.setProductID(productID);
//		product.setName(name);
//		product.setPrice(price);
//		product.setQuantity(quantity);
//		product.setDescription(description);
//		product.setImage(image);
//		product.setTrending(isTrending);
//		if(hasError) {
//	        request.setAttribute(NAME_ERROR, error);
//	        request.setAttribute(NAME_PRODUCT, product);
//	 
//	        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateProductView.jsp");
//	        dispatcher.forward(request, response);
//		} else {
//			try {
//			    // Đường dẫn tuyệt đối tới thư mục gốc của web app.
//				String appPath = request.getServletContext().getRealPath("");
//			    appPath = appPath.replace('\\', '/');
//			  
//			    // Thư mục để save file tải lên.
//			    String fullSavePath = null;
//			    if (appPath.endsWith("/")) {
//			    	fullSavePath = appPath + SAVED_DIRECTORY;
//			    } else {
//			    	fullSavePath = appPath + "WebContent/img/" + SAVED_DIRECTORY;
//			    }
//			  
//	            // Tạo thư mục nếu nó không tồn tại.
//	            File fileSaveDir = new File(fullSavePath);
//	            if (!fileSaveDir.exists()) {
//	                fileSaveDir.mkdir();
//	            }
//	  
//			    // Danh mục các phần đã upload lên (Có thể là nhiều file).
//		        for (Part part : request.getParts()) {
//		            String fileName = extractFileName(part);
//		            if (fileName != null && fileName.length() > 0) {
//		                String filePath = fullSavePath + File.separator + fileName;
//		               
//		             // Ghi vào file.
//		                part.write(filePath);
//		             }
//		            image = fileName;
//		        }
//			  
//			    // Upload thành công.
//				Connection conn = DBUtil.getStoredConnection(request);
//				ProductUtil.insertProduct(conn, product);
//				response.sendRedirect(request.getContextPath() + "/admin");
//			} catch (SQLException e) {
//				error = e.getMessage();
//				e.printStackTrace();
//				request.setAttribute(NAME_ERROR, error);
//		        request.setAttribute(NAME_PRODUCT, product);
//		        
//		        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateProductView.jsp");
//		        dispatcher.forward(request, response);
//			}
//		}
//	}
	
	private String extractFileName(Part part) {
	       // form-data; name="file"; filename="C:\file1.zip"
	       // form-data; name="file"; filename="C:\Note\file2.zip"
	       String contentDisp = part.getHeader("content-disposition");
	       String[] items = contentDisp.split(";");
	       for (String s : items) {
	           if (s.trim().startsWith("filename")) {
	               // C:\file1.zip
	               // C:\Note\file2.zip
	               String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	               clientFileName = clientFileName.replace("\\", "/");
	               int i = clientFileName.lastIndexOf('/');
	               // file1.zip
	               // file2.zip
	               return clientFileName.substring(i + 1);
	           }
	       }
	       return null;
	   }
}
