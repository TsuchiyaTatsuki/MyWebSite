package ecsite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MyBuyDataBeans;
import beans.MyItemDataBeans;
import beans.MyUserDataBeans;
import dao.MyBuyDAO;
import dao.MyBuyDetailDAO;

/**
 * Servlet implementation class UserBuyHistory
 */
@WebServlet("/UserBuyHistory")
public class UserBuyHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static int PAGE_MAX_HISTORY_COUNT = 5;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserBuyHistory() {
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
			if (lud == null ) {
				response.sendRedirect("Login");
			} else {
				int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
				int id = lud.getId();
				List<MyBuyDataBeans> buyList = MyBuyDAO.buyListSearch(id, page, PAGE_MAX_HISTORY_COUNT);
				ArrayList<ArrayList<MyItemDataBeans>> buyItemList = new ArrayList<ArrayList<MyItemDataBeans>>();
				for (MyBuyDataBeans buyDataBeans : buyList) {
					ArrayList<MyItemDataBeans> itemList = MyBuyDetailDAO
							.getItemDataBeansListByBuyId(buyDataBeans.getId());
					buyItemList.add(itemList);
				}
				double buyHistoryCount = MyBuyDAO.getBuyHistoryCount(id);
				int pageMax = (int) Math.ceil(buyHistoryCount / PAGE_MAX_HISTORY_COUNT);

				request.setAttribute("buyHistoryCount", (int) buyHistoryCount);
				request.setAttribute("page", page);
				request.setAttribute("pageMax", pageMax);
				request.setAttribute("buyList", buyList);
				request.setAttribute("buyItemList", buyItemList);
				request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY).forward(request, response);
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
