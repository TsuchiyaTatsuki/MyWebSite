package ecsite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MyUserDataBeans;
import dao.MyUserDAO;

/**
 * Servlet implementation class UserPassChange
 */
@WebServlet("/UserPassChange")
public class UserPassChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPassChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(EcHelper.USER_PASS_CHANGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		try {
			MyUserDataBeans lud = (MyUserDataBeans) session.getAttribute("lud");
			MyUserDataBeans udb = MyUserDAO.getUserDataBeansByUserId(lud.getId());

			String newPassword = request.getParameter("newPassword");
			String password = request.getParameter("password");
			String passwordCon = request.getParameter("passwordCon");


			String validationMessage = "";

			if(!EcHelper.getSha256(password).equals(udb.getPassword())) {
				validationMessage += "現在のパスワードが正しくありません<br>";
			}
			if(!password.equals(passwordCon)) {
				validationMessage += "パスワードと確認パスワードが違います<br>";
			}
			if (validationMessage.length() == 0) {
				MyUserDAO.getInstance().passChangeUser(lud.getId(), newPassword);
				String updateMesse = "パスワードの更新が完了しました";
				request.setAttribute("updateMesse", updateMesse);
				request.setAttribute("udb", udb);
				request.getRequestDispatcher(EcHelper.USER_INFO).forward(request, response);
			} else {
				request.setAttribute("validationMessage", validationMessage);
				request.getRequestDispatcher(EcHelper.USER_PASS_CHANGE).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
