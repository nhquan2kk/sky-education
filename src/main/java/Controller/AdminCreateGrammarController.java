package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import BEAN.Grammar;
import BEAN.Level;
import DAO.GrammarDAO;
import DAO.LevelDAO;
import DB.DBConnection;

@MultipartConfig
@WebServlet("/AdminCreateGrammarController")
public class AdminCreateGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminCreateGrammarController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection conn = DBConnection.CreatConnection();
			List<Level> listLevel = LevelDAO.ShowLevelList(conn);
			request.setAttribute("listLevel", listLevel);
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageGrammar/CreateGrammar.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreatConnection();
		Part part = request.getPart("file");
		String name = request.getParameter("name");
		int levelId = Integer.parseInt(request.getParameter("level"));
		String content = request.getParameter("content");
		String realPath = request.getServletContext().getRealPath("/img/grammar");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		System.out.println("file name : " + fileName + " realPath : " + realPath);
		part.write(realPath + "/" + fileName);

		Grammar grammar = new Grammar();
		grammar.setName(name);
		grammar.setImage(fileName);
		grammar.setContent(content);
		try {
			boolean kt = GrammarDAO.AddGrammar(request, conn, grammar, levelId);
			if (kt) {
				response.sendRedirect("AdminGrammarController?pageId=1");
			} else {
				request.setAttribute("msg", "Add failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminGrammarController?pageId=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
