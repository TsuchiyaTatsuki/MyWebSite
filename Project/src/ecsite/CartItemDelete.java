package ecsite;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MyItemDataBeans;

/**
 * Servlet implementation class CartItemDelete
 */
@WebServlet("/CartItemDelete")
public class CartItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartItemDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try {
			ArrayList<MyItemDataBeans> cart = (ArrayList<MyItemDataBeans>) session.getAttribute("cart");

			cart.remove(Integer.parseInt(request.getParameter("number")));
			NumberFormat nfCur = NumberFormat.getCurrencyInstance();
			int totalPrice = EcHelper.getTotalItemPrice(cart);
			String formatSubTotalPrice = nfCur.format(totalPrice);

			session.setAttribute("cart", cart);
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("formatSubTotalPrice", formatSubTotalPrice);
			request.getRequestDispatcher(EcHelper.CART_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
