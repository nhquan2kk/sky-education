package Controller;

import java.io.IOException;
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

@MultipartConfig()
@WebServlet("/AdminUpdateExaminationController")
public class AdminUpdateExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminUpdateExaminationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		int examinationId = Integer.parseInt(request.getParameter("examinationId"));
		try {
			Examination examinationData = ExaminationDAO.DetailExamination(conn, examinationId);
			List<Level> listLevel = LevelDAO.ShowLevelList(conn);
			request.setAttribute("listLevel", listLevel);
			request.setAttribute("examinationData", examinationData);
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageExamination/UpdateExamination.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}

		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");
		int levelId = Integer.parseInt(request.getParameter("level"));
		int examinationId = Integer.parseInt(request.getParameter("examinationId"));
		Part part = request.getPart("imgFile");
		String realPath = request.getServletContext().getRealPath("/img/examination");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);

		Examination examination = new Examination();
		examination.setExaminationId(examinationId);
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
			boolean kt = ExaminationDAO.UpdateExamination(conn, examination, levelId);
			ExaminationQuizDAO.UpdateQuizExcel(conn, examinationId, address);
			if (kt) {
				response.sendRedirect("AdminExaminationController?pageId=1");
			} else {
				request.setAttribute("msg", "create failed!");
				RequestDispatcher rd = request.getRequestDispatcher("AdminUpdateExaminationController");
				rd.forward(request, response);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
