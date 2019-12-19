package controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;

@WebServlet("/deleteProduct")
public class DeleteProductFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteProductFromCart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer index = Integer.valueOf(request.getParameter("index"));
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("NAME_CART");
		System.out.println(index);
		cart.getListProduct().remove(index.intValue());
		session.setAttribute("NAME_CART", cart);
		response.sendRedirect(request.getContextPath() + "/cart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
