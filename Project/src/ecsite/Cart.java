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
import dao.MyItemDAO;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
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
			if (cart == null) {
				cart = new ArrayList<MyItemDataBeans>();
			}
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try {
			ArrayList<MyItemDataBeans> cart = (ArrayList<MyItemDataBeans>) session.getAttribute("cart");
			if(cart == null) {
				cart = new ArrayList<MyItemDataBeans>();
			}

			int itemId = Integer.parseInt(request.getParameter("itemId"));
			MyItemDataBeans itemdb = MyItemDAO.getItemByItemID(itemId);
			cart.add(itemdb);
			NumberFormat nfCur = NumberFormat.getCurrencyInstance();
			int totalPrice = EcHelper.getTotalItemPrice(cart);
			String formatSubTotalPrice = nfCur.format(totalPrice);

			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("formatSubTotalPrice", formatSubTotalPrice);
			session.setAttribute("cart", cart);
			request.getRequestDispatcher(EcHelper.CART_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
