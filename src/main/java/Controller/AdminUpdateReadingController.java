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

import BEAN.Reading;
import BEAN.Vocabulary;
import DAO.ExaminationQuizDAO;
import DAO.ReadingDAO;
import DAO.ReadingQuizDAO;
import DAO.VocabularyDAO;
import DB.DBConnection;

@MultipartConfig
@WebServlet("/AdminUpdateReadingController")
public class AdminUpdateReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateReadingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		int readingId = Integer.parseInt(request.getParameter("readingId"));
		try {
			Reading readingData = ReadingDAO.DetailReading(conn, readingId);
			request.setAttribute("readingData", readingData);
			conn.close();
		}catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageReading/UpdateReading.jsp");
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
		String content = request.getParameter("content");
		int readingId = Integer.parseInt(request.getParameter("readingId"));
		Part part = request.getPart("imgFile");
		String realPath = request.getServletContext().getRealPath("/img/reading");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);

		Reading reading = new Reading();
		reading.setName(name);
		reading.setImage(fileName);
		reading.setContent(content);
		reading.setReadingId(readingId);
		
		part = request.getPart("excelFile");
		realPath = request.getServletContext().getRealPath("/excel/reading");
		fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		String address = realPath + "/" + fileName;
		part.write(address);

		try {
			boolean kt = ReadingDAO.UpdateReading(conn, reading);
			ReadingQuizDAO.UpdateQuizExcel(conn, readingId, address);
			if (kt) {
				response.sendRedirect("AdminReadingController?pageId=1");
			} else {
				request.setAttribute("msg", "create failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminUpdateReadingController");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		};
	}

}
