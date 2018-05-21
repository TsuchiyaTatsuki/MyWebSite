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
 * Servlet implementation class UserInfoChange
 */
@WebServlet("/UserInfoChange")
public class UserInfoChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoChange() {
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
			if (lud == null) {
				session.setAttribute("returnStrUrl", "UserInfoChange");
				response.sendRedirect("Login");
			} else {
				MyUserDataBeans udb = MyUserDAO.getUserDataBeansByUserId(lud.getId());
				request.setAttribute("udb", udb);
				request.getRequestDispatcher(EcHelper.USER_INFO_CHANGE).forward(request, response);
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
			if (lud == null) {
				session.setAttribute("returnStrUrl", "UserInfoChange");
				response.sendRedirect("Login");
			} else {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				String name = request.getParameter("name");
				String date = request.getParameter("birthDate");
				String address = request.getParameter("address");
				String loginId = request.getParameter("loginId");
				MyUserDataBeans udb = new MyUserDataBeans();

				Date birthDate = format.parse(date);
				udb.setName(name);
				udb.setBirthDate(birthDate);
				udb.setAddress(address);
				udb.setLoginId(loginId);

				String validationMessage = "";

				if (!EcHelper.isLoginIdValidation(udb.getLoginId())) {
					validationMessage += "半角英数とハイフン、アンダースコアのみ入力できます<br>";
				}
				if (MyUserDAO.isOverlapLoginId(udb.getLoginId())) {
					validationMessage += "ほかのユーザーが使用中のログインIDです<br>";
				}
				if (validationMessage.length() == 0) {
					request.setAttribute("udb", udb);
					request.getRequestDispatcher(EcHelper.USER_INFO_CHANGE_CONFIRM).forward(request, response);
				} else {
					session.setAttribute("udb", udb);
					request.setAttribute("validationMessage", validationMessage);
					request.getRequestDispatcher(EcHelper.USER_INFO_CHANGE).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
