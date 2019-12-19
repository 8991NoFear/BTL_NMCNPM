package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.Product;

public class ProductUtil {
	public static LinkedList<Product> getListProduct(Connection conn) throws SQLException {
		String sql = "Select product_id, category_id, name, quantity, price, description, image, is_trending from PRODUCT;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		LinkedList<Product> list = new LinkedList<Product>();
		while (rs.next()) {
	    	Product product = new Product();
	    	product.setProductID(rs.getInt("product_id"));
	    	product.setCategoryID(rs.getInt("category_id"));
	    	product.setName(rs.getString("name"));
	    	product.setQuantity(rs.getInt("quantity"));
	    	product.setPrice(rs.getFloat("price"));
	    	product.setDescription(rs.getString("description"));
	    	product.setImage(rs.getString("image"));
	    	product.setTrending(rs.getBoolean("is_trending"));
	    	list.add(product);
      }
      return list;
	}
	
	public static LinkedList<Product> getListTrendingProduct(Connection conn) throws SQLException {
		String sql = "Select product_id, category_id, name, quantity, price, description, image, is_trending from PRODUCT where is_trending = 1;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		LinkedList<Product> list = new LinkedList<Product>();
		while (rs.next()) {
	    	Product product = new Product();
	    	product.setProductID(rs.getInt("product_id"));
	    	product.setCategoryID(rs.getInt("category_id"));
	    	product.setName(rs.getString("name"));
	    	product.setQuantity(rs.getInt("quantity"));
	    	product.setPrice(rs.getFloat("price"));
	    	product.setDescription(rs.getString("description"));
	    	product.setImage(rs.getString("image"));
	    	product.setTrending(rs.getBoolean("is_trending"));
	    	list.add(product);
      }
      return list;
	}
	
	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String sql = "Insert into PRODUCT (product_id, category_id, name, quantity, price, description, image, is_trending) values (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, product.getProductID());
	    pstmt.setInt(2, product.getCategoryID());
	    pstmt.setString(3, product.getName());
	    pstmt.setInt(4, product.getQuantity());
	    pstmt.setFloat(5, product.getPrice());
	    pstmt.setString(6, product.getDescription());
	    pstmt.setString(7, product.getImage());
	    pstmt.setBoolean(8,product.isTrending());
	    pstmt.executeUpdate();
	}
	
	public static void updateProduct(Connection conn, int olProductID, Product product) throws SQLException {
		String sql = "Update PRODUCT set product_id = ?, category_id = ?, name = ?, quantity = ?,  price = ?, description = ?, image = ?, is_trending = ? where product_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, product.getProductID());
	    pstmt.setInt(2, product.getCategoryID());
	    pstmt.setString(3, product.getName());
	    pstmt.setInt(4, product.getQuantity());
	    pstmt.setFloat(5, product.getPrice());
	    pstmt.setString(6, product.getDescription());
	    pstmt.setString(7, product.getImage());
	    pstmt.setBoolean(8,product.isTrending());
	    pstmt.setInt(9, olProductID);
	    pstmt.executeUpdate();
	}
	
	public static void deleteProduct(Connection conn, int productID) throws SQLException {
		String sql = "Delete from PRODUCT where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productID);
		pstmt.executeUpdate();
	}
	
	public static Product findProduct(Connection conn, int productID) throws SQLException {
		String sql = "Select product_id, category_id, name, quantity, price, description, image, is_trending  from PRODUCT where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productID);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Product product = new Product();
	    	product.setProductID(rs.getInt("product_id"));
	    	product.setCategoryID(rs.getInt("category_id"));
	    	product.setName(rs.getString("name"));
	    	product.setQuantity(rs.getInt("quantity"));
	    	product.setPrice(rs.getFloat("price"));
	    	product.setDescription(rs.getString("description"));
	    	product.setImage(rs.getString("image"));
	    	product.setTrending(rs.getBoolean("is_trending"));
	    	return product;
		}
		return null;
	}
}