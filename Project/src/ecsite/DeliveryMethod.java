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

import beans.MyDeliveryMethodDataBeans;
import beans.MyItemDataBeans;
import dao.MyDeliveryMethodDAO;

/**
 * Servlet implementation class DeliveryMethod
 */
@WebServlet("/DeliveryMethod")
public class DeliveryMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryMethod() {
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
			int dmId = Integer.parseInt(request.getParameter("id"));
			MyDeliveryMethodDataBeans dmdb = MyDeliveryMethodDAO.getDeliveryMethodDataBeansByID(dmId);
			ArrayList<MyItemDataBeans> cart = (ArrayList<MyItemDataBeans>) session.getAttribute("cart");
			int subTotalPrice = EcHelper.getTotalItemPrice(cart);
			int totalPrice = dmdb.getPrice() + subTotalPrice;
			NumberFormat nfCur = NumberFormat.getCurrencyInstance();
			String formatTotalPrice = nfCur.format(totalPrice);

			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("formatTotalPrice", formatTotalPrice);
			session.setAttribute("dmdb", dmdb);
			request.getRequestDispatcher(EcHelper.BUY_PAGE).forward(request, response);

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
	}

}
