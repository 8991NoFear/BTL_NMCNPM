package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.Order;

public class OrderUtil {
	public static void insertOrder(Connection conn, Order order) throws SQLException {
		String sql = "Insert into [ORDER] (username, product_id, name, phone, address, quantity, date_created, is_confirm) values (?, ?, ?, ?, ?, ?, ?, 0);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, order.getUsername());
		pstmt.setInt(2, order.getProductID());
		pstmt.setString(3, order.getName());
		pstmt.setInt(4, order.getPhone());
		pstmt.setString(5, order.getAddress());
		pstmt.setInt(6, order.getQuantity());
		pstmt.setTimestamp(7,  order.getDateCreated());
		pstmt.executeUpdate();
	}
	
	public static Order findOrder(Connection conn, int orderId) throws SQLException {
		String sql = "Select order_id, username, product_id, [name], phone, [address], quantity, date_created, is_confirm from [ORDER] where order_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Order order = new Order();
			order.setOrderID(rs.getInt("order_id"));
			order.setUsername(rs.getString("username"));
			order.setProductID(rs.getInt("product_id"));
			order.setName(rs.getString("name"));
			order.setPhone(rs.getInt("phone"));
			order.setAddress(rs.getString("address"));
			order.setQuantity(rs.getInt("quantity"));
			order.setDateCreated(rs.getTimestamp("date_created"));
			order.setConfirm(rs.getBoolean("is_confirm"));
			return order;
		} else {
			return null;
		}
	}
	
	public static LinkedList<Order> getListOrder(Connection conn) throws SQLException{
		String sql = "Select * from [ORDER];";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		LinkedList<Order> list = new LinkedList<Order>();
		while(rs.next()) {
			Order order = new Order();
			order.setOrderID(rs.getInt("order_id"));
			order.setUsername(rs.getString("username"));
			order.setProductID(rs.getInt("product_id"));
			order.setName(rs.getString("name"));
			order.setPhone(rs.getInt("phone"));
			order.setAddress(rs.getString("address"));
			order.setQuantity(rs.getInt("quantity"));
			order.setDateCreated(rs.getTimestamp("date_created"));
			order.setConfirm(rs.getBoolean("is_confirm"));
			list.add(order);
		}
		return list;
	}
	
	public static LinkedList<Order> getListNewOrder(Connection conn) throws SQLException{
		String sql = "Select * from [ORDER] where is_confirm = 0;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		LinkedList<Order> list = new LinkedList<Order>();
		while(rs.next()) {
			Order order = new Order();
			order.setOrderID(rs.getInt("order_id"));
			order.setUsername(rs.getString("username"));
			order.setProductID(rs.getInt("product_id"));
			order.setName(rs.getString("name"));
			order.setPhone(rs.getInt("phone"));
			order.setAddress(rs.getString("address"));
			order.setQuantity(rs.getInt("quantity"));
			order.setDateCreated(rs.getTimestamp("date_created"));
			order.setConfirm(rs.getBoolean("is_confirm"));
			list.add(order);
		}
		return list;
	}
	
	public static void confirmNewOrder(Connection conn, int newOrderID) throws SQLException {
		String sql = "Update [ORDER] set is_confirm = 1 where order_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, newOrderID);
		pstmt.executeUpdate();
	}
	
}
