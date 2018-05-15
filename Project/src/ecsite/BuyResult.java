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

import beans.MyBuyDataBeans;
import beans.MyBuyDetailDataBeans;
import beans.MyDeliveryMethodDataBeans;
import beans.MyItemDataBeans;
import beans.MyUserDataBeans;
import dao.MyBuyDAO;
import dao.MyBuyDetailDAO;

/**
 * Servlet implementation class BuyResult
 */
@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyResult() {
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
			Boolean isLogin = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin")
					: false;
			if (!isLogin) {
				session.setAttribute("returnStrUrl", "Buy");
				response.sendRedirect("Login");
			} else {
				MyUserDataBeans udb = (MyUserDataBeans) EcHelper.cutSessionAttribute(session, "udb");
				ArrayList<MyItemDataBeans> cart = (ArrayList<MyItemDataBeans>) EcHelper.cutSessionAttribute(session,
						"cart");
				MyDeliveryMethodDataBeans dmdb = (MyDeliveryMethodDataBeans) EcHelper.cutSessionAttribute(session,
						"dmdb");
				int totalPrice = dmdb.getPrice() + EcHelper.getTotalItemPrice(cart);
				MyBuyDataBeans buydb = new MyBuyDataBeans();
				buydb.setUserId(udb.getId());
				buydb.setDelivertMethodId(dmdb.getId());
				buydb.setTotalPrice(totalPrice);
				int buyId = MyBuyDAO.insertBuy(buydb);
				for (MyItemDataBeans cartInItem : cart) {
					MyBuyDetailDataBeans buyddb = new MyBuyDetailDataBeans();
					buyddb.setBuyId(buyId);
					buyddb.setItemId(cartInItem.getId());
					MyBuyDetailDAO.insertBuyDetail(buyddb);
				}
				MyBuyDataBeans buyResult = MyBuyDAO.getBuyDataBeansByBuyId(buyId);
				request.setAttribute("buyResult", buyResult);
				ArrayList<MyItemDataBeans> buyItemList = MyBuyDetailDAO.getItemDataBeansListByBuyId(buyId);
				int subtotalPrice = 0;
				for (MyItemDataBeans idb : buyItemList) {
					subtotalPrice += idb.getPrice();
				}
				NumberFormat nfCur = NumberFormat.getCurrencyInstance();
				String formatintSubtotalPrice = nfCur.format(subtotalPrice);
				request.setAttribute("formatintSubtotalPrice", formatintSubtotalPrice);
				request.setAttribute("buyItemList", buyItemList);
				request.getRequestDispatcher(EcHelper.BUY_RESULT_PAGE).forward(request, response);
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
		doGet(request, response);
	}

}
