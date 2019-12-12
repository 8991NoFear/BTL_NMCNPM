package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class DBUtil {
	public static User findUser(Connection conn, String username, String password) throws SQLException {
	 
        String sql = "Select * from [USER] "
                + " where username = ? and password = ?;";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String email = rs.getString("email");
            Boolean isAdmin = rs.getBoolean("isAdmin");
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setIsAdmin(isAdmin);
            return user;
        }
        return null;
	}
	 
	public static User findUser(Connection conn, String username) throws SQLException {
	 
		String sql = "Select * from [USER] "
				+ " where username = ?;";
		 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
 
        ResultSet rs = pstmt.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("password");
			String email = rs.getString("email");
			Boolean isAdmin = rs.getBoolean("isAdmin");
		    User user = new User();
		    user.setUsername(username);
		    user.setPassword(password);
		    user.setEmail(email);
		    user.setIsAdmin(isAdmin);
		    return user;
        }
        return null;
	}
	
	public static void addUser(Connection conn, User user) throws SQLException {
		String sql = "Insert into [USER] values (?, ?, ?, 0)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getPassword());
		
		pstmt.executeUpdate();
	}
	    
	    
	 
	    /*
	    public static List<Product> listProduct(Connection conn) throws SQLException {
	        String sql = "Select * from PRODUCT;";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Product> list = new ArrayList<Product>();
	        while (rs.next()) {
	            String code = rs.getString("CODE");
	            String name = rs.getString("NAME");
	            float price = rs.getFloat("PRICE");
	            Product product = new Product();
	            product.setCode(code);
	            product.setName(name);
	            product.setPrice(price);
	            list.add(product);
	        }
	        return list;
	    }
	 
	    public static Product findProduct(Connection conn, String code) throws SQLException {
	        String sql = "Select p.CODE, p.NAME, p.PRICE from PRODUCT p where p.CODE = ?;";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, code);
	 
	        ResultSet rs = pstm.executeQuery();
	 
	        if (rs.next()) {
	            String name = rs.getString("NAME");
	            float price = rs.getFloat("PRICE");
	            Product product = new Product(code, name, price);
	            return product;
	        }
	        return null;
	    }
	 
	    public static void updateProduct(Connection conn, Product product) throws SQLException {
	        String sql = "Update PRODUCT set NAME = ?, PRICE = ? where CODE = ?;";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, product.getName());
	        pstm.setFloat(2, product.getPrice());
	        pstm.setString(3, product.getCode());
	        pstm.executeUpdate();
	    }
	 
	    public static void insertProduct(Connection conn, Product product) throws SQLException {
	        String sql = "Insert into Product(CODE, NAME, PRICE) values (? , ?, ?);";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, product.getCode());
	        pstm.setString(2, product.getName());
	        pstm.setFloat(3, product.getPrice());
	 
	        pstm.executeUpdate();
	    }
	 
	    public static void deleteProduct(Connection conn, String code) throws SQLException {
	        String sql = "Delete From PRODUCT where CODE = ?;";
	 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	 
	        pstm.setString(1, code);
	 
	        pstm.executeUpdate();
	    }
	    
	    */
}
