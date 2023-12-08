package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Reading;
import DAO.ReadingDAO;
import DB.DBConnection;

/**
 * Servlet implementation class ReadingController
 */
@WebServlet("/ReadingController")
public class ReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			int start = pageId;
			int COUNT = 10;
			if (start == 0)
				System.out.println("nothing here");
			else {
				start = start - 1;
				start = start * COUNT + 1;
			}
			Connection conn = DBConnection.CreatConnection();
			List<Reading> readingsList = ReadingDAO.ReadingPagination(start, COUNT, conn);
			int sumRow = ReadingDAO.CountRow(conn);
			int maxPageId = 0;

			if ((sumRow / COUNT) % 2 == 0) {
				maxPageId = sumRow / COUNT;
			} else
				maxPageId = (sumRow / COUNT) + 1;

			request.setAttribute("maxPageId", maxPageId);
			request.setAttribute("readingsList", readingsList);
			request.setAttribute("numberPage", pageId);
			System.out.println("max page id " + maxPageId + "number page : " + pageId);
			RequestDispatcher rd = request.getRequestDispatcher("View/Main/Reading/ListReading.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
