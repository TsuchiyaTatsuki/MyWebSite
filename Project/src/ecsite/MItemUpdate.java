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
 * Servlet implementation class MItemUpdate
 */
@WebServlet("/MItemUpdate")
public class MItemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MItemUpdate() {
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
			if (lud == null || lud.getId() != 1) {
				response.sendRedirect("Top");
			} else {
				int itemId = Integer.parseInt(request.getParameter("itemId"));
				int gender = Integer.parseInt(request.getParameter("gender"));
				MyItemDataBeans idb = MyItemDAO.getItemByItemID(itemId);
				ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(gender);
				session.setAttribute("idb", idb);
				session.setAttribute("cateList", cateList);
				request.getRequestDispatcher(EcHelper.M_ITEM_UPDATE).forward(request, response);
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
			MyItemDataBeans idb = (MyItemDataBeans) session.getAttribute("idb");

			String name = request.getParameter("name");
			int gender = Integer.parseInt(request.getParameter("gender"));
			int category = Integer.parseInt(request.getParameter("category"));
			String detail = request.getParameter("detail");
			int price = Integer.parseInt(request.getParameter("price"));
			String fileName = request.getParameter("fileName");

			MyItemDataBeans updateidb = new MyItemDataBeans();
			updateidb.setId(idb.getId());
			updateidb.setName(name);
			updateidb.setGender(gender);
			updateidb.setCategoryId(category);
			updateidb.setDetail(detail);
			updateidb.setPrice(price);
			updateidb.setFileName(fileName);

			MyItemDAO.getInstance().itemUpdate(updateidb);
			String updateMesse = "アイテムID: " + idb.getId() + " の更新が完了しました";

			session.setAttribute("updateMesse", updateMesse);
			response.sendRedirect("MItemList");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
