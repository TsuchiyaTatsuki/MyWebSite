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
				ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(gender);
				ArrayList<MyItemDataBeans>itemList = MyItemDAO.getRandItem(8, gender);
				ArrayList<MyItemDataBeans>rankGender0 = MyItemDAO.getRankingByGender(0, 4);
				ArrayList<MyItemDataBeans>rankGender1 = MyItemDAO.getRankingByGender(1, 4);

				session.setAttribute("genderId", gender);
				request.setAttribute("cateList", cateList);
				request.setAttribute("itemList", itemList);
				request.setAttribute("rankGender0", rankGender0);
				request.setAttribute("rankGender1", rankGender1);
				request.getRequestDispatcher(EcHelper.TOP_PAGE).forward(request, response);
			} else {
				gender = Integer.parseInt(request.getParameter("gender"));
				ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(gender);
				ArrayList<MyItemDataBeans>itemList = MyItemDAO.getRandItem(8, gender);
				ArrayList<MyItemDataBeans>rankGender = MyItemDAO.getRankingByGender(gender, 8);

				session.setAttribute("genderId", gender);
				request.setAttribute("cateList", cateList);
				request.setAttribute("itemList", itemList);
				request.setAttribute("rankGender", rankGender);
				request.getRequestDispatcher(EcHelper.TOP_PAGE).forward(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
