package ecsite;

import java.io.IOException;
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
 * Servlet implementation class Item
 */
@WebServlet("/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Item() {
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
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			MyItemDataBeans itemdb = MyItemDAO.getItemByItemID(itemId);
			ArrayList<MyItemDataBeans> relationItemList = MyItemDAO.getRandItemByCategory(6, itemdb.getCategoryId());
			ArrayList<MyItemDataBeans> readItemList;
			if (session.getAttribute("readItemList") == null) {
				readItemList = new ArrayList<MyItemDataBeans>();
			} else {
				readItemList = (ArrayList<MyItemDataBeans>) session.getAttribute("readItemList");
			}
			readItemList.add(0, itemdb);
			while (readItemList.size() >= 13) {
				readItemList.remove(12);
			}
			session.setAttribute("readItemList", readItemList);
			session.setAttribute("itemdb", itemdb);
			request.setAttribute("relationItemList", relationItemList);
			request.getRequestDispatcher(EcHelper.ITEM_PAGE).forward(request, response);

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
