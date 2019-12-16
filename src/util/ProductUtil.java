package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.Product;

public class ProductUtil {
	public static LinkedList<Product> getListProduct(Connection conn) throws SQLException {
		String sql = "Select product_id, category_id, name, quantity, price, description, description_detail, image, image1, image2, image3, accessory, is_trending from PRODUCT;";
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
	    	product.setDescriptionDetail(rs.getString("description_detail"));
	    	product.setImage(rs.getString("image"));
	    	product.setImage1(rs.getString("image1"));
	    	product.setImage2(rs.getString("image2"));
	    	product.setImage3(rs.getString("image3"));
	    	product.setAccessory(rs.getString("accessory"));
	    	product.setTrending(rs.getBoolean("is_trending"));
	    	list.add(product);
      }
      return list;
	}
	
	public static LinkedList<Product> getListTrendingProduct(Connection conn) throws SQLException {
		String sql = "Select product_id, category_id, name, quantity, price, description, description_detail, image, image1, image2, image3, accessory, is_trending from PRODUCT where is_trending = 1;";
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
	    	product.setDescriptionDetail(rs.getString("description_detail"));
	    	product.setImage(rs.getString("image"));
	    	product.setImage1(rs.getString("image1"));
	    	product.setImage2(rs.getString("image2"));
	    	product.setImage3(rs.getString("image3"));
	    	product.setAccessory(rs.getString("accessory"));
	    	product.setTrending(rs.getBoolean("is_trending"));
	    	list.add(product);
      }
      return list;
	}
	
	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String sql = "Insert into PRODUCT (product_id, category_id, name, quantity, price, description, description_detail, image, image1, image2, image3, accessory, is_trending) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, product.getProductID());
	    pstmt.setInt(2, product.getCategoryID());
	    pstmt.setString(3, product.getName());
	    pstmt.setInt(4, product.getQuantity());
	    pstmt.setFloat(5, product.getPrice());
	    pstmt.setString(6, product.getDescription());
	    pstmt.setString(7, product.getDescriptionDetail());
	    pstmt.setString(8, product.getImage());
	    pstmt.setString(9,product.getImage1());
	    pstmt.setString(10,product.getImage1());
	    pstmt.setString(11,product.getImage1());
	    pstmt.setString(12,product.getAccessory());
	    pstmt.setBoolean(13,product.isTrending());
	    pstmt.executeUpdate();
	}
	
	public static void updateProduct(Connection conn, int productID, Product product) throws SQLException {
		String sql = "Update PRODUCT set product_id = ?, category_id = ?, name = ?, quantity = ?,  price = ?, description = ?, description_detail = ?, image = ?, image1 = ?, image2 = ?, image3 = ?, accessory = ?, is_trending = ? where product_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, product.getProductID());
	    pstmt.setInt(2, product.getCategoryID());
	    pstmt.setString(3, product.getName());
	    pstmt.setInt(4, product.getQuantity());
	    pstmt.setFloat(5, product.getPrice());
	    pstmt.setString(6, product.getDescription());
	    pstmt.setString(7, product.getDescriptionDetail());
	    pstmt.setString(8, product.getImage());
	    pstmt.setString(9,product.getImage1());
	    pstmt.setString(10,product.getImage1());
	    pstmt.setString(11,product.getImage1());
	    pstmt.setString(12,product.getAccessory());
	    pstmt.setBoolean(13,product.isTrending());
	    pstmt.setInt(14, productID);
	    pstmt.executeUpdate();
	}
	
	public static void deleteProduct(Connection conn, int productID) throws SQLException {
		String sql = "Delete from PRODUCT where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productID);
		pstmt.executeUpdate();
	}
	
	public static Product findProduct(Connection conn, int productID) throws SQLException {
		String sql = "Select product_id, category_id, name, quantity, price, description, description_detail, image, image1, image2, image3, accessory, is_trending  from PRODUCT where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Product product = new Product();
	    	product.setProductID(rs.getInt("product_id"));
	    	product.setCategoryID(rs.getInt("category_id"));
	    	product.setName(rs.getString("name"));
	    	product.setQuantity(rs.getInt("quantity"));
	    	product.setPrice(rs.getFloat("price"));
	    	product.setDescription(rs.getString("description"));
	    	product.setDescriptionDetail(rs.getString("description_detail"));
	    	product.setImage(rs.getString("image"));
	    	product.setImage1(rs.getString("image1"));
	    	product.setImage2(rs.getString("image2"));
	    	product.setImage3(rs.getString("image3"));
	    	product.setAccessory(rs.getString("accessory"));
	    	product.setTrending(rs.getBoolean("is_trending"));
	    	return product;
		}
		return null;
	}
}
