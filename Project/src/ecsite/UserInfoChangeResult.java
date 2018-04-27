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
 * Servlet implementation class UserInfoChangeResult
 */
@WebServlet("/UserInfoChangeResult")
public class UserInfoChangeResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoChangeResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");

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
			udb.setId(lud.getId());

			String confirmed = request.getParameter("confirm_button");

			switch (confirmed) {
			case "0":
				session.setAttribute("udb", udb);
				response.sendRedirect("UserInfoChange");
				break;

			case "1":

				MyUserDAO.getInstance().infoChangeUser(udb);
				MyUserDataBeans updateudb = MyUserDAO.getUserDataBeansByUserId(lud.getId());
				String updateMesse = "登録情報の更新が完了しました";
				request.setAttribute("udb", updateudb);
				request.setAttribute("updateMesse", updateMesse);
				request.getRequestDispatcher(EcHelper.USER_INFO).forward(request, response);
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
