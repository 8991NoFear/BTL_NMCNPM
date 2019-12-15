package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.Category;

public class CategoryUtil {
	public static void insertCategory(Connection conn, Category category) throws SQLException {
		String sql = "Insert into CATEGORY values (?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  category.getCategoryID());
		pstmt.setString(2,  category.getName());
		pstmt.setString(3,  category.getImage());
		pstmt.executeUpdate();
	}
	
	public static void deleteCategory(Connection conn, String categoryID) throws SQLException {
		String sql = "Delete from CATEGORY where category_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, categoryID);
		pstmt.executeUpdate();
	}
	
	public static void updateCategory(Connection conn, int categoryID, Category category) throws SQLException {
		String sql = "Update CATEGORY set category_id = ?, name = ?, image = ? where category_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  category.getCategoryID());
		pstmt.setString(2,  category.getName());
		pstmt.setString(3,  category.getImage());
		pstmt.setInt(4,  categoryID);
		pstmt.executeUpdate();
	}
	
	public static LinkedList<Category> getListCategory(Connection conn) throws SQLException {
		String sql = "Select * from CATEGORY;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		LinkedList<Category> list = new LinkedList<Category>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategoryID(rs.getInt("category_id"));
			category.setName(rs.getString("name"));
			category.setImage(rs.getString("image"));
			list.add(category);
		}
		return list;
	}

}
