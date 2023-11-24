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

import BEAN.Examination;
import BEAN.Listening;
import DAO.ExaminationDAO;
import DAO.ExaminationQuizDAO;
import DAO.ListeningDAO;
import DAO.ListeningQuizDAO;
import DB.DBConnection;

@MultipartConfig
@WebServlet("/AdminUpdateListeningController")
public class AdminUpdateListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminUpdateListeningController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		int listenId = Integer.parseInt(request.getParameter("listenId"));
		try {
			Listening  listeningData = ListeningDAO.DetailListening(conn, listenId);
			request.setAttribute("listeningData", listeningData);
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageListening/UpdateListening.jsp");
		rd.forward(request, response);;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");
		int listenId = Integer.parseInt(request.getParameter("listenId"));
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
			boolean kt = ListeningDAO.UpdateListening(conn, listening);
			ListeningQuizDAO.UpdateQuizExcel(conn, listenId, address);
			if (kt) {
				response.sendRedirect("AdminListeningController?pageId=1");
			} else {
				request.setAttribute("msg", "update failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminUpdateListeningController");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
