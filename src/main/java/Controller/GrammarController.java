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

import BEAN.Grammar;
import BEAN.GrammarDetail;
import DAO.GrammarDAO;
import DB.DBConnection;

@WebServlet("/GrammarController")
public class GrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GrammarController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageId = Integer.parseInt(request.getParameter("pageId"));
		int start = pageId;
		int COUNT = 3;
		if (start == 0)
			System.out.println("nothing here");
		else {
			start = start - 1;
			start = start * COUNT + 1;
		}
		Connection conn = DBConnection.CreatConnection();
		try {
			List<GrammarDetail> list = GrammarDAO.DisplayGrammarDetailList(start, COUNT, conn);
			int sumRow = GrammarDAO.CountRow(conn);
			int maxPageId = 0;
			if ((sumRow / COUNT) % 2 == 0) {
				maxPageId = sumRow / COUNT;
			} else
				maxPageId = (sumRow / COUNT) + 1;
			if(maxPageId < pageId) {
				request.setAttribute("msg", "NOT FOUND");
			}
			request.setAttribute("maxPageId", maxPageId);
			request.setAttribute("listGrammar", list);
			request.setAttribute("numberPage", pageId);
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Grammar/ListGrammar.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		String name = request.getParameter("name");
		List<Grammar> list = GrammarDAO.DisplayResult(name, conn, request);
		request.setAttribute("listGrammar", list);
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Grammar/ListGrammar.jsp");
		rd.forward(request, response);
	}

}
