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
			session.removeAttribute("searchWord");
			session.removeAttribute("genderId");
			session.removeAttribute("categoryName");
			session.removeAttribute("category");
			session.removeAttribute("sortId");

			ArrayList<MyItemDataBeans> cateList = MyItemDAO.getCategoryByGender(2);
			ArrayList<MyItemDataBeans> itemList = MyItemDAO.getRandItem(8, 2);
			ArrayList<MyItemDataBeans> rankGender0 = MyItemDAO.getRankingByGender(0,4);
			ArrayList<MyItemDataBeans> rankGender1 = MyItemDAO.getRankingByGender(1,4);
			request.setAttribute("cateList", cateList);
			request.setAttribute("itemList", itemList);
			request.setAttribute("rankGender0", rankGender0);
			request.setAttribute("rankGender1", rankGender1);
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
