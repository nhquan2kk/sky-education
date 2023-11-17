package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.ExaminationQuiz;
import BEAN.Vocabulary;
import BEAN.VocabularyWord;
import DAO.ExaminationQuizDAO;
import DAO.VocabularyDAO;
import DAO.VocabularyWordDAO;
import DB.DBConnection;

/**
 * Servlet implementation class DetailVocabularyController
 */
@WebServlet("/DetailVocabularyController")
public class DetailVocabularyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailVocabularyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int vocabularyId = Integer.parseInt(request.getParameter("vocabularyId"));
		Connection conn = DBConnection.CreatConnection();
		
		Vocabulary vocabularyData = VocabularyDAO.DetailVocabulary(conn, vocabularyId); 
		List<VocabularyWord> vocabularyWordsList = VocabularyWordDAO.DetailVocabulary(conn, vocabularyId);
		System.out.println("size : "+ vocabularyWordsList.size());
		request.setAttribute("vocabularyData", vocabularyData);
		request.setAttribute("vocabularyWordsList", vocabularyWordsList);
		request.setAttribute("vocabularyId", vocabularyId);
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Vocabulary/DetailVocabulary.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
