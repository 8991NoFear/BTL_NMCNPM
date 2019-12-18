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

import bean.Category;
import util.CategoryUtil;
import util.DBUtil;

@WebServlet("/admin/editCategory")
@MultipartConfig
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME_ERROR = "NAME_ERROR";
	private static final String NAME_CATEGORY = "NAME_CATEGORY";
	private static final String SAVE_DIRECTORY = "category";
	
	private Integer categoryID;
	private Integer oldCategoryID;
	private String name;
	private String image;
	
	private boolean hasError;
	private String error;
	private Category category;
	
    public EditCategory() {
        super();
        category = new Category();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			oldCategoryID = Integer.valueOf(request.getParameter("categoryID"));
			Connection conn = DBUtil.getStoredConnection(request);
			category = CategoryUtil.findCategory(conn, oldCategoryID);
			request.setAttribute(NAME_CATEGORY, category);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/admin/EditCategoryView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkError(request);
		category.setCategoryID(categoryID);
		category.setName(name);
		if(hasError) {
			request.setAttribute(NAME_ERROR, error);
			request.setAttribute(NAME_CATEGORY, category);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateCategoryView.jsp");
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
			
			    // PART
			    Part part = request.getPart("image");
			    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			    image = fileName;
			    category.setImage(image);
			    
			    // UPLOAD SUCCEDD
			    if (fileName != null && fileName.length() > 0) {
			    	Connection conn = DBUtil.getStoredConnection(request);
			    	CategoryUtil.updateCategory(conn, oldCategoryID, category);
			        String filePath = fullSavePath + File.separator + fileName;
			        part.write(filePath);
			        response.sendRedirect(request.getContextPath() + "/admin");
			    } else {
			    	error = "file error!";
			    	request.setAttribute(NAME_ERROR, error);
			    	request.setAttribute(NAME_CATEGORY, category);
			    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateCategoryView.jsp");
				    dispatcher.forward(request, response);
			    }
			    
			} catch (Exception e) {
			    e.printStackTrace();
			    error = e.getMessage();
			    request.setAttribute(NAME_ERROR, error);
			    request.setAttribute(NAME_CATEGORY, category);
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/admin/CreateCategoryView.jsp");
			    dispatcher.forward(request, response);
			}
		}
		
	}
	
	private boolean loadParameter(HttpServletRequest request) {
		try {
			categoryID = Integer.valueOf(request.getParameter("categoryID"));
			name = request.getParameter("name");
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
