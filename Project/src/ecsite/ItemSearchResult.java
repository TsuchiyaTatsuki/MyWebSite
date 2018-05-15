package ecsite;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MyCategoryDataBeans;
import beans.MyItemDataBeans;
import dao.MyCategoryDAO;
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
			int categoryId;
			int page;
			double itemCount;
			int pageMax;
			if (request.getParameter("sortId") == null) {
				if (session.getAttribute("sortId") == null) {
					sortId = 0;
				} else {
					sortId = (int) session.getAttribute("sortId");
				}
			} else {
				sortId = Integer.parseInt(request.getParameter("sortId"));
			}
			if (request.getParameter("searchWord") == null) {
				if (session.getAttribute("searchWord") == null) {
					searchWord = "";
				} else {
					searchWord = (String) session.getAttribute("searchWord");
				}
			} else {
				searchWord = request.getParameter("searchWord");
			}
			if (request.getParameter("categoryId") == null) {
				if (session.getAttribute("categoryId") == null) {
					categoryId = 0;
				} else {
					categoryId = (int) session.getAttribute("categoryId");
				}
			} else {
				categoryId = Integer.parseInt(request.getParameter("categoryId"));
			}
			if (request.getParameter("genderId") == null) {
				if (session.getAttribute("genderId") == null) {
					genderId = 2;
				} else {
					genderId = (int) session.getAttribute("genderId");
				}
			} else {
				genderId = Integer.parseInt(request.getParameter("genderId"));
			}

			ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(genderId);
			page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
			itemCount = MyItemDAO.getItemCount(searchWord, categoryId, genderId);
			pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

			ArrayList<MyItemDataBeans> itemList = MyItemDAO.getInstance().getItems(searchWord, categoryId, genderId,
					sortId, page, PAGE_MAX_ITEM_COUNT);

			if (categoryId != 0) {
				MyCategoryDataBeans category = MyCategoryDAO.getCategory(categoryId);
				session.setAttribute("category", category);
			}
			session.setAttribute("sortId", sortId);
			session.setAttribute("searchWord", searchWord);
			session.setAttribute("categoryId", categoryId);
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
			if (request.getParameter("close") != null) {
				switch (request.getParameter("close")) {
				case "searchWordClose":
					session.removeAttribute("searchWord");
					break;
				case "genderClose":
					session.removeAttribute("genderId");
					break;
				case "categoryClose":
					session.removeAttribute("categoryId");
					session.removeAttribute("category");
					break;
				default:
					break;
				}
			}
			int sortId;
			String searchWord;
			int genderId;
			int categoryId;
			int page;
			double itemCount;
			int pageMax;
			if (request.getParameter("sortId") == null) {
				if (session.getAttribute("sortId") == null) {
					sortId = 0;
				} else {
					sortId = (int) session.getAttribute("sortId");
				}
			} else {
				sortId = Integer.parseInt(request.getParameter("sortId"));
			}
			if (request.getParameter("searchWord") == null) {
				if (session.getAttribute("searchWord") == null) {
					searchWord = "";
				} else {
					searchWord = (String) session.getAttribute("searchWord");
				}
			} else {
				searchWord = request.getParameter("searchWord");
			}
			if (request.getParameter("categoryId") == null) {
				if (session.getAttribute("categoryId") == null) {
					categoryId = 0;
				} else {
					categoryId = (int) session.getAttribute("categoryId");
				}
			} else {
				categoryId = Integer.parseInt(request.getParameter("categoryId"));
			}
			if (request.getParameter("genderId") == null) {
				if (session.getAttribute("genderId") == null) {
					genderId = 2;
				} else {
					genderId = (int) session.getAttribute("genderId");
				}
			} else {
				genderId = Integer.parseInt(request.getParameter("genderId"));
			}

			ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(genderId);
			page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
			itemCount = MyItemDAO.getItemCount(searchWord, categoryId, genderId);
			pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

			ArrayList<MyItemDataBeans> itemList = MyItemDAO.getInstance().getItems(searchWord, categoryId, genderId,
					sortId, page, PAGE_MAX_ITEM_COUNT);

			if (categoryId != 0) {
				MyCategoryDataBeans category = MyCategoryDAO.getCategory(categoryId);
				session.setAttribute("category", category);
			}
			session.setAttribute("sortId", sortId);
			session.setAttribute("searchWord", searchWord);
			session.setAttribute("categoryId", categoryId);
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
