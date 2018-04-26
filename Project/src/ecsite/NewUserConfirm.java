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
 * Servlet implementation class NewUserConfirm
 */
@WebServlet("/NewUserConfirm")
public class NewUserConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserConfirm() {
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
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			String name = request.getParameter("name");
			String date = request.getParameter("birthDate");
			String address = request.getParameter("address");
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");
			String passwordCon = request.getParameter("passwordCon");
			MyUserDataBeans udb = new MyUserDataBeans();

			Date birthDate = format.parse(date);
			udb.setName(name);
			udb.setBirthDate(birthDate);
			udb.setAddress(address);
			udb.setLoginId(loginId);
			udb.setPassword(password);

			String validationMessage = "";

			if(!password.equals(passwordCon)) {
				validationMessage += "パスワードと確認パスワードが違います";
			}
			if (!EcHelper.isLoginIdValidation(udb.getLoginId())) {
				validationMessage += "半角英数とハイフン、アンダースコアのみ入力できます";
			}
			if (MyUserDAO.isOverlapLoginId(udb.getLoginId())) {
				validationMessage += "ほかのユーザーが使用中のログインIDです";
			}

			if (validationMessage.length() == 0) {
				request.setAttribute("udb", udb);
				request.getRequestDispatcher(EcHelper.NEW_USER_CONFIRM).forward(request, response);
			} else {
				session.setAttribute("udb", udb);
				session.setAttribute("validationMessage", validationMessage);
				response.sendRedirect("NewUser");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
