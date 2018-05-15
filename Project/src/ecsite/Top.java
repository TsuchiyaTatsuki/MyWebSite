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
 * Servlet implementation class Top
 */
@WebServlet("/Top")
public class Top extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Top() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try {
			if(session.getAttribute("searchWord") != null) {
				session.removeAttribute("searchWord");
			}
			if(session.getAttribute("genderId") != null) {
				session.removeAttribute("genderId");
			}
			if(session.getAttribute("categoryId") != null) {
				session.removeAttribute("categoryId");
			}
			if(session.getAttribute("category") != null) {
				session.removeAttribute("category");
			}
			if(session.getAttribute("sortId") != null) {
				session.removeAttribute("sortId");
			}

			ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(2);
			ArrayList<MyItemDataBeans>itemList = MyItemDAO.getRandItem(12, 2);
			request.setAttribute("cateList", cateList);
			request.setAttribute("itemList", itemList);
			request.getRequestDispatcher(EcHelper.TOP_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
