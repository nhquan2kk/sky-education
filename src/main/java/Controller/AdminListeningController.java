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

import BEAN.Listening;
import DAO.ListeningDAO;
import DB.DBConnection;


@WebServlet("/AdminListeningController")
public class AdminListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminListeningController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			List<Listening> listeningList = ListeningDAO.ListeningPagination(start, COUNT, conn);
			int sumRow = ListeningDAO.CountRow(conn);
			int maxPageId = 0;

			if ((sumRow / COUNT) % 2 == 0) {
				maxPageId = sumRow / COUNT;
			} else
				maxPageId = (sumRow / COUNT) + 1;

			request.setAttribute("maxPageId", maxPageId);
			request.setAttribute("listeningList", listeningList);
			request.setAttribute("numberPage", pageId);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageListening/ListListening.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
