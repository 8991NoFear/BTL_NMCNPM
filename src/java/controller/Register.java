package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
    
    @Resource(name = "jdbc/BTL_NMCNPM")
    private javax.sql.DataSource dataSource;
    Connection conn;
    Statement stmt;
    String sql;
    
    public Register() {
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            
            String username = request.getParameter("name");
            String password = request.getParameter("password");
            String confPass = request.getParameter("confirmPassword");
            String email = request.getParameter("email");
            
            if(hasUser(username)){
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
            } else if(password.equals(confPass)){
                sql = "insert into [USER](username, email, password, isAdmin) values (N'" + username + "', N'" + email + "', N'" + password + "', " + 0 + ");";
                stmt.execute(sql);
                RequestDispatcher rd = request.getRequestDispatcher("login.html");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
            }
            
        } catch (SQLException  ex) {
            ex.printStackTrace();
        }
        
    }
    
    public boolean hasUser(String username) throws SQLException{
        String sql = "select count(*) from [USER] where username = N'" + username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int num = rs.getInt(1);
        if (num == 1){
            return true;
        } else{  
            return false;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
