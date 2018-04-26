package ecsite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MyUserDataBeans;
import dao.MyUserDAO;

/**
 * Servlet implementation class NewUserResult
 */
@WebServlet("/NewUserResult")
public class NewUserResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

			String name = request.getParameter("name");
			String date = request.getParameter("birthDate");
			String address = request.getParameter("address");
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");
			MyUserDataBeans udb = new MyUserDataBeans();

			Date birthDate = format.parse(date);
			udb.setName(name);
			udb.setBirthDate(birthDate);
			udb.setAddress(address);
			udb.setLoginId(loginId);
			udb.setPassword(password);

			String confirmed = request.getParameter("confirm_button");

			switch (confirmed) {
			case "0":
				session.setAttribute("udb", udb);
				response.sendRedirect("NewUser");
				break;

			case "1":

				MyUserDAO.getInstance().insertUser(udb);
				request.setAttribute("udb", udb);
				request.getRequestDispatcher(EcHelper.NEW_USER_RESULT).forward(request, response);
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}

	}

}
