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
import DAO.DetailGrammarDAO;
import DAO.GrammarDAO;
import DAO.LevelDAO;
import DB.DBConnection;

@MultipartConfig()
@WebServlet("/AdminUpdateGrammarController")
public class AdminUpdateGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AdminUpdateGrammarController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		int grammarId = Integer.parseInt(request.getParameter("grammarId"));
		try {
			Grammar grammarData = DetailGrammarDAO.GrammarDetail(grammarId, conn);
			List<Level> listLevel = LevelDAO.ShowLevelList(conn);
			request.setAttribute("listLevel", listLevel);
			request.setAttribute("grammarId", grammarId);
			request.setAttribute("grammarData", grammarData);
			conn.close();
		}catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageGrammar/UpdateGrammar.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		PrintWriter out = response.getWriter();
		Connection conn = DBConnection.CreatConnection();
		Part part = request.getPart("file");
		int levelId = Integer.parseInt(request.getParameter("level"));
		int grammarId = Integer.parseInt(request.getParameter("grammarId"));
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String realPath = request.getServletContext().getRealPath("/img/grammar");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);

		System.out.println("file path : "+realPath+"/"+fileName);
		Grammar grammar = new Grammar();
		grammar.setName(name);
		grammar.setImage(fileName);
		grammar.setContent(content);
		try {
			boolean kt = GrammarDAO.UpdateGrammar(request, conn, grammar, grammarId, levelId);
			System.out.println("UPDATE GRAMMAR STATUS : "+kt);
			if (kt) {
				 response.sendRedirect("AdminGrammarController?pageId=1"); 
			} else {
				out.print("failed");
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
