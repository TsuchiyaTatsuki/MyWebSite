package ecsite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ログイン失敗時に使用するため
		HttpSession session = request.getSession();
		try {
			Boolean isLogin = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin")
					: false;
			if (isLogin) {
				response.sendRedirect("Top");
			} else {
				String inputLoginId = session.getAttribute("loginId") != null
						? (String) EcHelper.cutSessionAttribute(session, "loginId")
						: "";
				String loginErrorMessage = (String) EcHelper.cutSessionAttribute(session, "loginErrorMessage");
				request.setAttribute("inputLoginId", inputLoginId);
				request.setAttribute("loginErrorMessage", loginErrorMessage);
				request.getRequestDispatcher(EcHelper.LOGIN_PAGE).forward(request, response);
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
	}

}
