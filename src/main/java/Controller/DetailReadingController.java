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
import BEAN.Reading;
import BEAN.ReadingQuiz;
import DAO.ReadingDAO;
import DAO.ReadingQuizDAO;
import DB.DBConnection;

/**
 * Servlet implementation class DetailReadingController
 */
@WebServlet("/DetailReadingController")
public class DetailReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailReadingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int readingId = Integer.parseInt(request.getParameter("readingId"));
		try {
			Connection conn = DBConnection.CreatConnection();
			Reading readingData = ReadingDAO.DetailReading(conn, readingId);
			List<ReadingQuiz> readingQuizData = ReadingQuizDAO.DetailReadingQuiz(conn, readingId);
			request.setAttribute("readingData", readingData);
			request.setAttribute("readingId", request.getParameter("readingId"));
			request.setAttribute("readingQuizData", readingQuizData);
			request.setAttribute("characterDatabase", "\n");
			request.setAttribute("characterHTML", "<br/>");
			RequestDispatcher rd = request.getRequestDispatcher("View/Main/Reading/DetailReading.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		int readingId = Integer.parseInt(request.getParameter("readingId"));
		int countRows = ReadingQuizDAO.CountRow(conn, readingId);
		List<ReadingQuiz> listCorrectAnswer = ReadingQuizDAO.DetailReadingQuiz(conn, readingId);

		List<Answer> listAnswer = new ArrayList<Answer>();
		int points = 0;
		for (int i = 1; i <= countRows; i++) {
			String Answer = request.getParameter("ans[" + i + "]");
			if (Answer != null) {
				Answer answer = new Answer();
				answer.setNumber(i);
				answer.setAnswer(Answer);
				listAnswer.add(answer);
				String correctAnswer = ReadingQuizDAO.ResultQuiz(conn, readingId, i);
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
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Reading/ResultReading.jsp");
		rd.forward(request, response);
	}

}
