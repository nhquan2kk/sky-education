package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VocabularyDAO;
import DB.DBConnection;

@WebServlet("/AdminDeleteVocabularyController")
public class AdminDeleteVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDeleteVocabularyController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vocabularyId = Integer.parseInt(request.getParameter("vocabularyId"));
		Connection conn = DBConnection.CreatConnection();

		try {
			boolean isDelete = VocabularyDAO.DeleteVocabulary(conn, vocabularyId);
			if (isDelete) {
				response.sendRedirect("AdminVocabularyController?pageId=1");
			} else {
				request.setAttribute("msg", "Delete Failed");
				response.sendRedirect("AdminVocabularyController?pageId=1");
			}
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

}
