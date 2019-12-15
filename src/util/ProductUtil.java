package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.Product;

public class ProductUtil {
	public static LinkedList<Product> getListProduct(Connection conn) throws SQLException {
		String sql = "Select * from PRODUCT;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql);
		LinkedList<Product> list = new LinkedList<Product>();
      while (rs.next()) {
    	  Product product = new Product();
    	  product.setProductID(rs.getInt("product_id"));
    	  product.setCategoryID(rs.getInt("category_id"));
    	  product.setName(rs.getString("name"));
    	  product.setPrice(rs.getFloat("price"));
    	  product.setDescription(rs.getString("description"));
    	  product.setImage(rs.getString("image"));
    	  list.add(product);
      }
      return list;
	}
	
	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String sql = "Insert into PRODUCT values (?, ?, ?, ?, ?, ?);";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, product.getProductID());
	    pstmt.setInt(2, product.getCategoryID());
	    pstmt.setString(3, product.getName());
	    pstmt.setFloat(4, product.getPrice());
	    pstmt.setString(5, product.getDescription());
	    pstmt.setString(6, product.getImage());
	    pstmt.executeUpdate();
	}
	
	public static void updateProduct(Connection conn, int productID, Product product) throws SQLException {
		String sql = "Update PRODUCT set product_id = ?, category_id = ?, name = ?, price = ?, description = ?, image = ? where product_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, product.getProductID());
	    pstmt.setInt(2, product.getCategoryID());
	    pstmt.setString(3, product.getName());
	    pstmt.setFloat(4, product.getPrice());
	    pstmt.setString(5, product.getDescription());
	    pstmt.setString(6, product.getImage());
	    pstmt.setInt(7, productID);
	    pstmt.executeUpdate();
	}
	
	public static void deleteProduct(Connection conn, int productID) throws SQLException {
		String sql = "Delete from PRODUCT where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productID);
		pstmt.executeUpdate();
	}
	
	public static Product findProduct(Connection conn, int productID) throws SQLException {
		String sql = "Select * from PRODUCT where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productID);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Product product = new Product();
			product.setProductID(rs.getInt("product_id"));
			product.setCategoryID(rs.getInt("category_id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getFloat("price"));
			product.setDescription(rs.getString("description"));
			product.setImage(rs.getString("image"));
			return product;
		} else {
			return null;
		}
		
	}
}
