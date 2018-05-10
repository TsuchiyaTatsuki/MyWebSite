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
 * Servlet implementation class MNewItem
 */
@WebServlet("/MNewItem")
public class MNewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MNewItem() {
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
			session.removeAttribute("newItem");

			ArrayList<MyItemDataBeans> menCate = MyItemDAO.getCategoryByGender(0);
			ArrayList<MyItemDataBeans> womenCate = MyItemDAO.getCategoryByGender(1);

			session.setAttribute("menCate", menCate);
			session.setAttribute("womenCate", womenCate);
			request.getRequestDispatcher(EcHelper.M_NEW_ITEM).forward(request, response);

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

			String name = request.getParameter("name");
			String detail = request.getParameter("detail");
			int price = Integer.parseInt(request.getParameter("price"));

			MyItemDataBeans newItem = new MyItemDataBeans();
			newItem.setName(name);
			newItem.setDetail(detail);
			newItem.setPrice(price);

			if(request.getParameter("fileName").equals("0")) {
				String fileName = null;
				newItem.setFileName(fileName);
			} else {
				String fileName = request.getParameter("fileName");
				newItem.setFileName(fileName);
			}

			if(Integer.parseInt(request.getParameter("menCate")) != 0) {
				int category = Integer.parseInt(request.getParameter("menCate"));
				newItem.setGender(0);
				newItem.setCategoryId(category);

			} else if(Integer.parseInt(request.getParameter("womenCate")) != 0) {
				int category = Integer.parseInt(request.getParameter("womenCate"));
				newItem.setGender(1);
				newItem.setCategoryId(category);

			} else {
				String error = "カテゴリーを選択してください";
				request.setAttribute("newItem", newItem);
				request.setAttribute("error", error);
				request.getRequestDispatcher(EcHelper.M_NEW_ITEM).forward(request, response);
			}

			MyItemDAO.getInstance().addNewItem(newItem);

			String updateMesse = "アイテムの追加が完了しました";

			ArrayList<MyItemDataBeans>itemList = MyItemDAO.getAllItem();
			request.setAttribute("itemList", itemList);
			request.setAttribute("updateMesse", updateMesse);
			request.getRequestDispatcher(EcHelper.M_ITEM_LIST).forward(request, response);


		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
