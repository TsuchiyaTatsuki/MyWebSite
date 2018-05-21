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
 * Servlet implementation class ItemSearchResult
 */
@WebServlet("/ItemSearchResult")
public class ItemSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static int PAGE_MAX_ITEM_COUNT = 16;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemSearchResult() {
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
			int sortId;
			String searchWord;
			int genderId;
			String categoryName;
			int page;
			double itemCount;
			int pageMax;
			if (request.getParameter("sortId") == null) {
				sortId = session.getAttribute("sortId") == null ? 0 : (int) session.getAttribute("sortId");
			} else {
				sortId = Integer.parseInt(request.getParameter("sortId"));
			}
			if (request.getParameter("searchWord") == null) {
				searchWord = session.getAttribute("searchWord") == null ? ""
						: (String) session.getAttribute("searchWord");
			} else {
				searchWord = request.getParameter("searchWord");
			}
			if (request.getParameter("categoryName") == null) {
				categoryName = session.getAttribute("categoryName") == null ? ""
						: (String) session.getAttribute("categoryName");
			} else {
				categoryName = request.getParameter("categoryName");
			}
			if (request.getParameter("genderId") == null) {
				genderId = session.getAttribute("genderId") == null ? 2 : (int) session.getAttribute("genderId");
			} else {
				genderId = Integer.parseInt(request.getParameter("genderId"));
			}

			ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(genderId);
			page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
			itemCount = MyItemDAO.getItemCount(searchWord, categoryName, genderId);
			pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

			ArrayList<MyItemDataBeans> itemList = MyItemDAO.getInstance().getItems(searchWord, categoryName, genderId,
					sortId, page, PAGE_MAX_ITEM_COUNT);

			session.setAttribute("sortId", sortId);
			session.setAttribute("searchWord", searchWord);
			session.setAttribute("categoryName", categoryName);
			session.setAttribute("genderId", genderId);
			request.setAttribute("itemList", itemList);
			request.setAttribute("cateList", cateList);
			request.setAttribute("itemCount", (int) itemCount);
			request.setAttribute("page", page);
			request.setAttribute("pageMax", pageMax);
			request.getRequestDispatcher(EcHelper.SEARCH_RESULT_PAGE).forward(request, response);
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
			int sortId;
			String searchWord;
			int genderId;
			String categoryName;
			int page;
			double itemCount;
			int pageMax;
			if (request.getParameter("close") != null) {
				switch (request.getParameter("close")) {
				case "searchWordClose":
					session.removeAttribute("searchWord");
					break;
				case "genderClose":
					session.removeAttribute("genderId");
					break;
				case "categoryClose":
					session.removeAttribute("categoryName");
					break;
				case "allClose":
					session.removeAttribute("searchWord");
					session.removeAttribute("genderId");
					session.removeAttribute("categoryName");
					break;
				default:
					break;
				}
			}
			if (request.getParameter("sortId") == null) {
				sortId = session.getAttribute("sortId") == null ? 0 : (int) session.getAttribute("sortId");
			} else {
				sortId = Integer.parseInt(request.getParameter("sortId"));
			}
			if (request.getParameter("searchWord") == null) {
				searchWord = session.getAttribute("searchWord") == null ? ""
						: (String) session.getAttribute("searchWord");
			} else {
				searchWord = request.getParameter("searchWord");
			}
			if (request.getParameter("categoryName") == null) {
				categoryName = session.getAttribute("categoryName") == null ? ""
						: (String) session.getAttribute("categoryName");
			} else {
				categoryName = request.getParameter("categoryName");
			}
			if (request.getParameter("genderId") == null) {
					genderId = session.getAttribute("genderId") == null ? 2 : (int)session.getAttribute("genderId");
			} else {
				genderId = Integer.parseInt(request.getParameter("genderId"));
			}

			ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(genderId);
			page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
			itemCount = MyItemDAO.getItemCount(searchWord, categoryName, genderId);
			pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

			ArrayList<MyItemDataBeans> itemList = MyItemDAO.getInstance().getItems(searchWord, categoryName, genderId,
					sortId, page, PAGE_MAX_ITEM_COUNT);

			session.setAttribute("sortId", sortId);
			session.setAttribute("searchWord", searchWord);
			session.setAttribute("categoryName", categoryName);
			session.setAttribute("genderId", genderId);
			request.setAttribute("itemList", itemList);
			request.setAttribute("cateList", cateList);
			request.setAttribute("itemCount", (int) itemCount);
			request.setAttribute("page", page);
			request.setAttribute("pageMax", pageMax);
			request.getRequestDispatcher(EcHelper.SEARCH_RESULT_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
