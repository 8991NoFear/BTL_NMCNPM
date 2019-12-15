package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.ProductDetail;

public class ProductDetailUtil {
	public static LinkedList<ProductDetail> getListProductDetail(Connection conn) throws SQLException {
		String sql = "Select product_id, description_detail, image1, image2, image3 from PRODUCT_DETAIL;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql);
		LinkedList<ProductDetail> list = new LinkedList<ProductDetail>();
		while (rs.next()) {
	    	ProductDetail productDetail = new ProductDetail();
	    	productDetail.setProductID(rs.getInt("product_id"));
	    	productDetail.setDescriptionDetail(rs.getString("description_detail"));
	    	productDetail.setImage1(rs.getString("image1"));
	    	productDetail.setImage2(rs.getString("image2"));
	    	productDetail.setImage3(rs.getString("image3"));
	    	list.add(productDetail);
		}
      return list;
	}
	
	public static void insertProductDetail(Connection conn, ProductDetail productDetail) throws SQLException {
		String sql = "Insert into PRODUCT_DETAIL (product_id, description_detail, image1, image2, image3) values (?, ?, ?, ?, ?);";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, productDetail.getProductID());
	    pstmt.setString(2, productDetail.getDescriptionDetail());
	    pstmt.setString(3, productDetail.getImage1());
	    pstmt.setString(4, productDetail.getImage2());
	    pstmt.setString(5, productDetail.getImage3());
	    pstmt.executeUpdate();
	}
	
	public static void updateProductDetail(Connection conn, int productID, ProductDetail productDetail) throws SQLException {
		String sql = "Update PRODUCT set product_id = ?, description_detail = ?, image1 = ?, image2 = ?, image3 = ? where product_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productDetail.getProductID());
	    pstmt.setString(2, productDetail.getDescriptionDetail());
	    pstmt.setString(3, productDetail.getImage1());
	    pstmt.setString(4, productDetail.getImage2());
	    pstmt.setString(5, productDetail.getImage3());
	    pstmt.setInt(6, productID);
	    pstmt.executeUpdate();
	}
	
	public static void deleteProductDetail(Connection conn, int productID) throws SQLException {
		String sql = "Delete from PRODUCT_DETAIL where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productID);
		pstmt.executeUpdate();
	}
	
	public static ProductDetail findProductDetail(Connection conn, int productID) throws SQLException {
		String sql = "Select product_id, description_detail, image1, image2, image3 from PRODUCT_DETAIL where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productID);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			ProductDetail productDetail = new ProductDetail();
			productDetail.setProductID(rs.getInt("product_id"));
			productDetail.setDescriptionDetail(rs.getString("description_detail"));
			productDetail.setImage1(rs.getString("image1"));
			productDetail.setImage2(rs.getString("image2"));
			productDetail.setImage3(rs.getString("image3"));
			return productDetail;
		} else {
			return null;
		}
	}
}
