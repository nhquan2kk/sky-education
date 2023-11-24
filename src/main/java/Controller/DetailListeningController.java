package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Answer;
import BEAN.Listening;
import BEAN.ListeningQuiz;
import DAO.ListeningDAO;
import DAO.ListeningQuizDAO;
import DB.DBConnection;

/**
 * Servlet implementation class DetailListeningController
 */
@WebServlet("/DetailListeningController")
public class DetailListeningController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailListeningController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int listenId = Integer.parseInt(request.getParameter("listenId"));
		try {
			Connection conn = DBConnection.CreatConnection();
			Listening listeningData = ListeningDAO.DetailListening(conn, listenId);
			List<ListeningQuiz> listeningQuizData = ListeningQuizDAO.DetailListeningQuiz(conn, listenId);
			request.setAttribute("listeningData", listeningData);
			request.setAttribute("listenId", request.getParameter("listenId"));
			request.setAttribute("listeningQuizData", listeningQuizData);
			request.setAttribute("characterDatabase", "\n");
			request.setAttribute("characterHTML", "<br/>");
			RequestDispatcher rd = request.getRequestDispatcher("View/Main/Listening/DetailListening.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		int listenId = Integer.parseInt(request.getParameter("listenId"));
		int countRows = ListeningQuizDAO.CountRow(conn, listenId);
		List<ListeningQuiz> listCorrectAnswer = ListeningQuizDAO.DetailListeningQuiz(conn, listenId);

		List<Answer> listAnswer = new ArrayList<Answer>();
		int points = 0;
		for (int i = 1; i <= countRows; i++) {
			String Answer = request.getParameter("ans[" + i + "]");
			if (Answer != null) {
				Answer answer = new Answer();
				answer.setNumber(i);
				answer.setAnswer(Answer);
				listAnswer.add(answer);
				String correctAnswer = ListeningQuizDAO.ResultQuiz(conn, listenId, i);
				if (Answer.equals(correctAnswer)) {
					points++;
				}
			} else {
				Answer au = new Answer();
				au.setNumber(i);
				au.setAnswer("");
				listAnswer.add(au);
			}
		}
		int numberWrongAnswer = countRows - points;
		request.setAttribute("listCorrectAnswer", listCorrectAnswer);
		request.setAttribute("listAnswer", listAnswer);
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Listening/ResultListening.jsp");
		rd.forward(request, response);
	}

}
