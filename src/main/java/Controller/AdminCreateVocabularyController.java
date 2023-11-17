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

@MultipartConfig()
@WebServlet("/AdminCreateVocabularyController")
public class AdminCreateVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCreateVocabularyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageVocabulary/CreateVocabulary.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");

		Part part = request.getPart("imgFile");
		String realPath = request.getServletContext().getRealPath("/img/vocabulary");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);

		Vocabulary vocabulary = new Vocabulary();
		vocabulary.setName(name);
		vocabulary.setImage(fileName);	
		
		part = request.getPart("excelFile");
		realPath = request.getServletContext().getRealPath("/excel/vocabulary");
		fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		String address = realPath + "/" + fileName;
		part.write(realPath + "/" + fileName);
		System.out.println("file name : " + fileName + " realPath : " + realPath);
		
		try {
			boolean kt = VocabularyDAO.CreateVocabulary(conn, vocabulary);
			int vocabularyId = VocabularyDAO.FindId(conn);
			System.out.println("Anh id : "+vocabularyId+" address : "+address);
			
			
			VocabularyWordDAO.ImportQuizExcel(conn, vocabularyId, address);		
			if (kt) {
				response.sendRedirect("AdminVocabularyController?pageId=1"); 
				/*
				 * RequestDispatcher rd =
				 * request.getRequestDispatcher("AdminvocabularyController"); rd.forward(request,
				 * response);
				 */
			} else {
				request.setAttribute("msg", "create failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminCreateVocabularyController");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("file name : " + fileName + " realPath : " + realPath);
	}

}
