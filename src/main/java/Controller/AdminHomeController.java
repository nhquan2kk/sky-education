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

import BEAN.Level;
import DAO.GrammarDAO;
import DAO.LevelDAO;
import DB.DBConnection;

/**
 * Servlet implementation class HomeAdminController
 */
@WebServlet("/AdminHomeController")
public class AdminHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminHomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBConnection.CreatConnection();
			int grammarTotal = GrammarDAO.CountRow(conn);
//			int examinationTotal = ExaminationDAO.CountRow(conn);
//			int vocabularyTotal = VocabularyDAO.CountRow(conn);
//			int readingTotal = ReadingDAO.CountRow(conn);
//			int listeningTotal = ListeningDAO.CountRow(conn);
			
			request.setAttribute("grammarTotal", grammarTotal);
//			request.setAttribute("examinationTotal", examinationTotal);
//			request.setAttribute("vocabularyTotal", vocabularyTotal);
//			request.setAttribute("readingTotal", readingTotal);
//			request.setAttribute("listeningTotal", listeningTotal);
			request.setAttribute("currentDay", java.time.LocalDate.now());
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
