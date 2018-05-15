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
import beans.MyUserDataBeans;
import dao.MyItemDAO;

/**
 * Servlet implementation class MItemDelete
 */
@WebServlet("/MItemDelete")
public class MItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MItemDelete() {
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
			MyUserDataBeans lud = (MyUserDataBeans)session.getAttribute("lud");
			if (lud == null || lud.getId() != 1 ) {
				response.sendRedirect("Top");
			} else {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			MyItemDataBeans idb = MyItemDAO.getItemByItemID(itemId);

			session.setAttribute("idb", idb);

			request.getRequestDispatcher(EcHelper.M_ITEM_DELETE).forward(request, response);
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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			String confirmed = request.getParameter("deleteButton");

			switch (confirmed) {
			case "0":
				response.sendRedirect("MItemList");
				break;

			case "1":
				MyItemDAO.getInstance().itemDelete(itemId);
				String updateMesse = "アイテムID: " + itemId + " の削除が完了しました";
				ArrayList<MyItemDataBeans> itemList = MyItemDAO.getAllItem();
				request.setAttribute("itemList", itemList);
				request.setAttribute("updateMesse", updateMesse);
				request.getRequestDispatcher(EcHelper.M_ITEM_LIST).forward(request, response);
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

	}

}
