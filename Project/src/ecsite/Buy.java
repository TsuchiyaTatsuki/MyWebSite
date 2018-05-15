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
import beans.MyUserDataBeans;
import dao.MyDeliveryMethodDAO;
import dao.MyUserDAO;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Buy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try {
			MyUserDataBeans lud = (MyUserDataBeans) session.getAttribute("lud");
			Boolean isLogin = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin")
					: false;
			if (!isLogin) {
				session.setAttribute("returnStrUrl", "Buy");
				response.sendRedirect("Login");
			} else {
				ArrayList<MyDeliveryMethodDataBeans> dmList = MyDeliveryMethodDAO.getAllDeliveryMethodDataBeans();
				MyUserDataBeans udb = MyUserDAO.getUserDataBeansByUserId(lud.getId());
				MyDeliveryMethodDataBeans dmdb = MyDeliveryMethodDAO.getDeliveryMethodDataBeansByID(1);
				ArrayList<MyItemDataBeans> cart = (ArrayList<MyItemDataBeans>) session.getAttribute("cart");
				int subTotalPrice = EcHelper.getTotalItemPrice(cart);

				NumberFormat nfCur = NumberFormat.getCurrencyInstance();
				String formatSubTotalPrice = nfCur.format(subTotalPrice);

				session.setAttribute("subTotalPrice", subTotalPrice);
				session.setAttribute("formatSubTotalPrice", formatSubTotalPrice);
				session.setAttribute("dmdb", dmdb);
				session.setAttribute("udb", udb);
				session.setAttribute("dmList", dmList);
				request.getRequestDispatcher(EcHelper.BUY_PAGE).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
