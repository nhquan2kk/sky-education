package Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import BEAN.Vocabulary;
import DAO.VocabularyDAO;
import DAO.VocabularyWordDAO;
import DB.DBConnection;

@MultipartConfig
@WebServlet("/AdminUpdateVocabularyController")
public class AdminUpdateVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminUpdateVocabularyController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		int vocabularyId = Integer.parseInt(request.getParameter("vocabularyId"));
		try {
			Vocabulary vocabularyData = VocabularyDAO.DetailVocabulary(conn, vocabularyId);
			request.setAttribute("vocabularyData", vocabularyData);
			conn.close();
		}catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageVocabulary/UpdateVocabulary.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");
		int vocabularyId = Integer.parseInt(request.getParameter("vocabularyId"));
		Part part = request.getPart("imgFile");
		String realPath = request.getServletContext().getRealPath("/img/vocabulary");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);

		Vocabulary vocabulary = new Vocabulary();
		vocabulary.setVocabularyId(vocabularyId);
		vocabulary.setName(name);
		vocabulary.setImage(fileName);
		
		part = request.getPart("excelFile");
		realPath = request.getServletContext().getRealPath("/excel/vocabulary");
		fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		String address = realPath + "/" + fileName;
		part.write(address);

		try {
			boolean kt = VocabularyDAO.UpdateVocabulary(conn, vocabulary);
			VocabularyWordDAO.UpdateQuizExcel(conn, vocabularyId, address);
			if (kt) {
				response.sendRedirect("AdminVocabularyController?pageId=1");
			} else {
				request.setAttribute("msg", "create failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminUpdateVocabularyController");
				rd.forward(request, response);
			}
			System.out.println("The end of update vocabulary");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
