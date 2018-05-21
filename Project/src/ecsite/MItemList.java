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
 * Servlet implementation class MItemList
 */
@WebServlet("/MItemList")
public class MItemList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static int PAGE_MAX_ITEM_COUNT = 25;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MItemList() {
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
				int MsortId;
				String MsearchWord;
				int MgenderId;
				String McategoryName;
				int page;
				double itemCount;
				int pageMax;

				if (request.getParameter("MsortId") == null) {
					MsortId = session.getAttribute("MsortId") == null ? 0 : (int) session.getAttribute("MsortId");
				} else {
					MsortId = Integer.parseInt(request.getParameter("MsortId"));
				}
				if (request.getParameter("MsearchWord") == null) {
					MsearchWord = session.getAttribute("MsearchWord") == null ? ""
							: (String) session.getAttribute("MsearchWord");
				} else {
					MsearchWord = request.getParameter("MsearchWord");
				}
				if (request.getParameter("McategoryName") == null) {
					McategoryName = session.getAttribute("McategoryName") == null ? ""
							: (String) session.getAttribute("McategoryName");
				} else {
					McategoryName = request.getParameter("McategoryName");
				}
				if (request.getParameter("MgenderId") == null) {
					MgenderId = session.getAttribute("MgenderId") == null ? 2 : (int) session.getAttribute("MgenderId");
				} else {
					MgenderId = Integer.parseInt(request.getParameter("MgenderId"));
				}

				ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(MgenderId);
				itemCount = MyItemDAO.getItemCount(MsearchWord, McategoryName, MgenderId);
				pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);
				page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
				ArrayList<MyItemDataBeans> itemList = MyItemDAO.getInstance().MgetItems(MsearchWord,
						MgenderId, MsortId, McategoryName, page, PAGE_MAX_ITEM_COUNT);

				session.setAttribute("MsortId", MsortId);
				session.setAttribute("MsearchWord", MsearchWord);
				session.setAttribute("McategoryName", McategoryName);
				session.setAttribute("MgenderId", MgenderId);
				request.setAttribute("itemList", itemList);
				request.setAttribute("cateList", cateList);
				request.setAttribute("itemCount", (int) itemCount);
				request.setAttribute("page", page);
				request.setAttribute("pageMax", pageMax);
				request.getRequestDispatcher(EcHelper.M_ITEM_LIST).forward(request, response);
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
			MyUserDataBeans lud = (MyUserDataBeans) session.getAttribute("lud");
			if (lud == null || lud.getId() != 1) {
				response.sendRedirect("Top");
			} else {
				int MsortId;
				String MsearchWord;
				int MgenderId;
				String McategoryName;
				int page;
				double itemCount;
				int pageMax;
				if (request.getParameter("close") != null) {
					switch (request.getParameter("close")) {
					case "searchWordClose":
						session.removeAttribute("MsearchWord");
						break;
					case "genderClose":
						session.removeAttribute("MgenderId");
						break;
					case "categoryClose":
						session.removeAttribute("McategoryName");
						break;
					case "allClose":
						session.removeAttribute("MgenderId");
						session.removeAttribute("MsearchWord");
						session.removeAttribute("McategoryName");
						session.removeAttribute("MsortId");
						break;
					default:
						break;
					}
				}

				if (request.getParameter("MsortId") == null) {
					MsortId = session.getAttribute("MsortId") == null ? 0 : (int) session.getAttribute("MsortId");
				} else {
					MsortId = Integer.parseInt(request.getParameter("MsortId"));
				}
				if (request.getParameter("MsearchWord") == null) {
					MsearchWord = session.getAttribute("MsearchWord") == null ? ""
							: (String) session.getAttribute("MsearchWord");
				} else {
					MsearchWord = request.getParameter("MsearchWord");
				}
				if (request.getParameter("MgenderId") == null) {
					MgenderId = session.getAttribute("MgenderId") == null ? 2 : (int) session.getAttribute("MgenderId");
				} else {
					MgenderId = Integer.parseInt(request.getParameter("MgenderId"));
				}
				if (request.getParameter("McategoryName") == null) {
					McategoryName = session.getAttribute("McategoryName") == null ? ""
							: (String) session.getAttribute("McategoryName");
				} else {
					McategoryName = request.getParameter("McategoryName");
				}
				ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(MgenderId);
				itemCount = MyItemDAO.getItemCount(MsearchWord, McategoryName, MgenderId);
				pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);
				page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
				ArrayList<MyItemDataBeans> itemList = MyItemDAO.getInstance().MgetItems(MsearchWord,
						MgenderId, MsortId, McategoryName, page, PAGE_MAX_ITEM_COUNT);

				session.setAttribute("MsortId", MsortId);
				session.setAttribute("MsearchWord", MsearchWord);
				session.setAttribute("McategoryName", McategoryName);
				session.setAttribute("MgenderId", MgenderId);
				request.setAttribute("itemList", itemList);
				request.setAttribute("cateList", cateList);
				request.setAttribute("itemCount", (int) itemCount);
				request.setAttribute("page", page);
				request.setAttribute("pageMax", pageMax);
				request.getRequestDispatcher(EcHelper.M_ITEM_LIST).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
