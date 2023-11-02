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

import BEAN.Examination;
import BEAN.Level;
import DAO.ExaminationDAO;
import DAO.ExaminationQuizDAO;
import DAO.LevelDAO;
import DB.DBConnection;

@MultipartConfig
@WebServlet("/AdminCreateExaminationController")
public class AdminCreateExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminCreateExaminationController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBConnection.CreatConnection();
			List<Level> listLevel = LevelDAO.ShowLevelList(conn);
			request.setAttribute("listLevel", listLevel);
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageExamination/CreateExamination.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		PrintWriter out = response.getWriter();
		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");
		int levelId = Integer.parseInt(request.getParameter("level"));
		Part part = request.getPart("imgFile");
		String realPath = request.getServletContext().getRealPath("/img/examination");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);

		Examination examination = new Examination();
		examination.setName(name);
		examination.setImage(fileName);	
		
		part = request.getPart("excelFile");
		realPath = request.getServletContext().getRealPath("/excel/examination");
		fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		String address = realPath + "/" + fileName;
		part.write(realPath + "/" + fileName);
		
		
		try {
			boolean kt = ExaminationDAO.CreateExamination(conn, examination, levelId);
			int examinationId = ExaminationDAO.FindId(conn);
			System.out.println("Anh id : "+examinationId+" address : "+address);
			
			
			ExaminationQuizDAO.ImportQuizExcel(conn, examinationId, address);		
			if (kt) {
				response.sendRedirect("AdminExaminationController?pageId=1"); 
				/*
				 * RequestDispatcher rd =
				 * request.getRequestDispatcher("AdminexaminationController"); rd.forward(request,
				 * response);
				 */
			} else {
				out.print("failed");
				request.setAttribute("msg", "create failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminExaminationController?pageId=1");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("file name : " + fileName + " realPath : " + realPath);
	}

}
