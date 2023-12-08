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
import java.util.Locale;
import BEAN.Examination;
import BEAN.Grammar;
import DAO.ExaminationDAO;
import DAO.GrammarDAO;
import DB.DBConnection;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		Locale locale = request.getLocale();
		String language = locale.getLanguage();
		String country = locale.getCountry();
		System.out.println(locale + language + country);
		try {
			List<Examination> examinationList = ExaminationDAO.NewExamination(conn);
			List<Grammar> grammarList = GrammarDAO.NewGrammar(conn);
			request.setAttribute("examinationList", examinationList);
			request.setAttribute("grammarList", grammarList);
			response.setHeader("Content-Language", "es");
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
