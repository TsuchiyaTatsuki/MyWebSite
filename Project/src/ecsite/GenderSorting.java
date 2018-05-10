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
 * Servlet implementation class GenderSorting
 */
@WebServlet("/GenderSorting")
public class GenderSorting extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenderSorting() {
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
			int gender;
			if(request.getParameter("gender") == null) {
				gender = 2;
			} else {
				gender = Integer.parseInt(request.getParameter("gender"));
			}

			ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(gender);

			session.setAttribute("genderId", gender);
			session.setAttribute("cateList", cateList);
			String searchWord = (String)session.getAttribute("searchWord");
			if(searchWord != null) {
				session.removeAttribute("searchWord");
			}
			ArrayList<MyItemDataBeans>itemList = MyItemDAO.getRandItem(4);
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

	}

}
