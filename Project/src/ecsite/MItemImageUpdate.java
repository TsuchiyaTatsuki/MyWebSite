package ecsite;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.MyUserDataBeans;

/**
 * Servlet implementation class MItemImageUpdate
 */
@WebServlet("/MItemImageUpdate")
@MultipartConfig(location = "C:\\Users\\USER\\Documents\\MyWebSite\\Project\\WebContent\\img", maxFileSize = 1048576)
public class MItemImageUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MItemImageUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			MyUserDataBeans lud = (MyUserDataBeans) session.getAttribute("lud");
			if (lud == null || lud.getId() != 1) {
				response.sendRedirect("Top");
			} else {
				String errorMesse = "画像を選択してください";

				Part part = request.getPart("file");
				String name = this.getFileName(part);
				if (name.length() != 0) {
					part.write(name);
					request.setAttribute("fileName", name);
					if (request.getParameter("newItem") != null) {
						request.getRequestDispatcher(EcHelper.M_NEW_ITEM).forward(request, response);
					}
					request.getRequestDispatcher(EcHelper.M_ITEM_UPDATE).forward(request, response);
				} else {
					request.setAttribute("errorMesse", errorMesse);
					if (request.getParameter("newItem") != null) {
						request.getRequestDispatcher(EcHelper.M_NEW_ITEM).forward(request, response);
					}
					request.getRequestDispatcher(EcHelper.M_ITEM_UPDATE).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}

}
