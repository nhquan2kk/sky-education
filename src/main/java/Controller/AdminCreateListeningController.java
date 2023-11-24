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

import BEAN.Listening;
import DAO.ListeningDAO;
import DAO.ListeningQuizDAO;
import DB.DBConnection;

@MultipartConfig
@WebServlet("/AdminCreateListeningController")
public class AdminCreateListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminCreateListeningController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageListening/CreateListening.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");
		int levelId = Integer.parseInt(request.getParameter("level"));
		Part part = request.getPart("imgFile");
		String realPath = request.getServletContext().getRealPath("/img/listening");
		String imgName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + imgName);
	
		part = request.getPart("audioFile");
		 realPath = request.getServletContext().getRealPath("/audio/listening");
			 String audioName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			part.write(realPath + "/" + audioName);
		
		Listening listening = new Listening();
		listening.setName(name);
		listening.setImage(imgName);	
		listening.setAudioMp3(audioName);
		
		part = request.getPart("excelFile");
		realPath = request.getServletContext().getRealPath("/excel/listening");
		String excelName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		String address = realPath + "/" + excelName;
		part.write(address);
		
		
		try {
			boolean kt = ListeningDAO.CreateListening(conn, listening);
			int listeningId = ListeningDAO.FindId(conn);
			
			ListeningQuizDAO.ImportQuizExcel(conn, listeningId, address);		
			if (kt) {
				response.sendRedirect("AdminListeningController?pageId=1"); 
			} else {
				request.setAttribute("msg", "create failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminCreateListeningController");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
