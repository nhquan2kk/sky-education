package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ExaminationDAO;
import DB.DBConnection;

/**
 * Servlet implementation class AdminDeleteExaminationController
 */
@WebServlet("/AdminDeleteExaminationController")
public class AdminDeleteExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteExaminationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int examinationId = Integer.parseInt(request.getParameter("examinationId"));
		Connection conn = DBConnection.CreatConnection();

		try {
			boolean isDelete = ExaminationDAO.DeleteExamination(conn, examinationId);
			if (isDelete) {
				response.sendRedirect("AdminExaminationController?pageId=1");
			} else {
				request.setAttribute("msg", "Delete Failed");
				response.sendRedirect("AdminExaminationController?pageId=1");
			}
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

}
