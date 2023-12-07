package Controller;

import java.io.IOException; 
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import BEAN.Answer;
import BEAN.ExaminationQuiz;
import BEAN.Result;
import DAO.ExaminationQuizDAO;
import DAO.ReadingQuizDAO;
import DAO.ResultDAO;
import DB.DBConnection;
import util.constant;

@WebServlet("/DetailExaminationController")
public class DetailExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailExaminationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int examinationId = Integer.parseInt(request.getParameter("examinationId"));
		Connection conn = DBConnection.CreatConnection();
		HttpSession session = request.getSession(true);
		if (session.getAttribute("sessionUser") != null) {
			System.out.print("STARTS");
			List<ExaminationQuiz> examinationQuizs = ExaminationQuizDAO.DetailExamination(conn, examinationId);
			request.setAttribute("examinationQuizs", examinationQuizs);
			request.setAttribute("examinationId", examinationId);
			session.setAttribute("userId", session.getAttribute("sessionMemberId"));
			RequestDispatcher rd = request.getRequestDispatcher("View/Main/Examination/DetailExamination.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("msgErr", "Login to do examination");
			RequestDispatcher rd = request.getRequestDispatcher("View/Main/Examination/DetailExamination.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SUBMIT EXAMINATION");
		Connection conn = DBConnection.CreatConnection();
		HttpSession session = request.getSession(true);
//		#int memberId = Integer.parseInt(session.getAttribute("sessionMemberId").toString());
		int memberId = (int)session.getAttribute(constant.ESession.MEMBERID.name());
		int examinationId = Integer.parseInt(request.getParameter("examinationId"));
		int countRows = ExaminationQuizDAO.CountRow(conn, examinationId);
		List<ExaminationQuiz> listCorrectAnswer = ExaminationQuizDAO.DetailExamination(conn, examinationId);

		List<Answer> listAnswer = new ArrayList<Answer>();
		int points = 0;
		for (int i = 1; i <= countRows; i++) {
			String Answer = request.getParameter("ans[" + i + "]");
			if (Answer != null) {
				Answer answer = new Answer();
				answer.setNumber(i);
				answer.setAnswer(Answer);
				listAnswer.add(answer);
				String correctAnswer = ExaminationQuizDAO.ResultQuiz(conn, examinationId, i);
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
		Date currentTime = new Date();
		int numberWrongAnswer = countRows - points;
		Result result = new Result();
		result.setCorrectAnswer(points);
		result.setIncorrectAnswer(numberWrongAnswer);
		result.setExamCompletionTime(currentTime);
		result.setExaminationId(examinationId);
		result.setMemberId(memberId);
		ResultDAO.ResultExamination(conn, result, examinationId, memberId);
		request.setAttribute("listCorrectAnswer", listCorrectAnswer);
		request.setAttribute("listAnswer", listAnswer);
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Examination/ResultExamination.jsp");
		rd.forward(request, response);
	}
}
