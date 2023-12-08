package Controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import DAO.ReadingDAO;
import DAO.ReadingQuizDAO;
import DB.DBConnection;

@MultipartConfig
@WebServlet("/AdminCreateReadingController")
public class AdminCreateReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCreateReadingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageReading/CreateReading.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		PrintWriter out = response.getWriter();
		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");
		String content = request.getParameter("content");
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
		
		part = request.getPart("excelFile");
		realPath = request.getServletContext().getRealPath("/excel/reading");
		fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		String address = realPath + "/" + fileName;
		part.write(realPath + "/" + fileName);
		
		
		try {
			boolean kt = ReadingDAO.CreateReading(conn, reading);
			int readingId = ReadingDAO.FindId(conn);
			System.out.println("Anh id : "+readingId+" address : "+address);
			
			
			ReadingQuizDAO.ImportQuizExcel(conn, readingId, address);		
			if (kt) {
				response.sendRedirect("AdminReadingController?pageId=1"); 
			} else {
				out.print("failed");
				request.setAttribute("msg", "create failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminCreateReadingController");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
