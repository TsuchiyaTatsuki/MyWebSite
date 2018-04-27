package ecsite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class MItemImageUpdate
 */
@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig
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
		String destination = request.getParameter("destination");
		Part part = request.getPart("file");
		String fileName = getFielName(part);
		Path filePath = Paths.get(destination + File.separator + fileName);
		// アップしたファイルを取得して、保存
		InputStream in = part.getInputStream();
		Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
		// 画面遷移先で保存したファイルパスを表示
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Upload</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>File upload result</h1>");
			out.println("<div>");
			out.println("upload succeed[file path:" + filePath + "]");
			out.println("<div>");
			out.println("</body>");
			out.println("</html>");

		}

	}

	private String getFielName(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("***" + header);
		String[] split = header.split(";");
		// headerは、以下の内容になっているので、ここからfilenameである「fileupload.png」を取得
		// form-data; name="file"; filename="fileupload.png"
		String fileName = Arrays.asList(split).stream()
				.filter(s -> s.trim().startsWith("filename"))
				.collect(Collectors.joining());
		return fileName.substring(fileName.indexOf("=") + 1).replace("\"", "");
	}

}
