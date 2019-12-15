package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.Order;

public class OrderUtil {
	public void insertOrder(Connection conn, Order order) throws SQLException {
		String sql = "Insert into [ORDER] values (?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, order.getOrderID());
		pstmt.setString(2,  order.getUsername());
		pstmt.setInt(3,  order.getProductID());
		pstmt.setInt(4, order.getQuantity());
		pstmt.setTimestamp(5,  order.getDateCreated());
		pstmt.setInt(6,  order.getConfirmationNumber());
		pstmt.executeUpdate();
	}
	
	public Order findOrder(Connection conn, int orderId) throws SQLException {
		String sql = "Select * from [ORDER] where order_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Order order = new Order();
			order.setOrderID(rs.getInt("order_id"));
			order.setUsername(rs.getString("username"));
			order.setProductID(rs.getInt("product_id"));
			order.setQuantity(rs.getInt("quantity"));
			order.setDateCreated(rs.getTimestamp("date_created"));
			order.setConfirmationNumber(rs.getInt("confirmation_number"));
			return order;
		} else {
			return null;
		}
	}
	
	public LinkedList<Order> getListOrder(Connection conn) throws SQLException{
		String sql = "Select * from [ORDER];";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql);
		LinkedList<Order> list = new LinkedList<Order>();
		while(rs.next()) {
			Order order = new Order();
			order.setOrderID(rs.getInt("order_id"));
			order.setUsername(rs.getString("username"));
			order.setProductID(rs.getInt("product_id"));
			order.setQuantity(rs.getInt("quantity"));
			order.setDateCreated(rs.getTimestamp("date_created"));
			order.setConfirmationNumber(rs.getInt("confirmation_number"));
			list.add(order);
		}
		return list;
	}
	
}
