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
import DAO.GrammarDAO;
import DB.DBConnection;

@WebServlet("/AdminGrammarController")
public class AdminGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminGrammarController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();	
		try {
		System.out.print("GET VALUE: "+request.getParameter("pageId"));
			int pageId = Integer.parseInt(request.getParameter("pageId") != null ? request.getParameter("pageId") : "1");
			int start = pageId;
			int COUNT = 10;
			if (start == 0)
				System.out.println("nothing here");
			else {
				start = start - 1;
				start = start * COUNT + 1;
			}
			List<Grammar> list = GrammarDAO.DisplayGrammar(start, COUNT, conn);
			int sumRow = GrammarDAO.CountRow(conn);
			int maxPageId = 0;

			if ((sumRow / COUNT) % 2 == 0) {
				maxPageId = sumRow / COUNT;
			} else
				maxPageId = (sumRow / COUNT) + 1;
			request.setAttribute("maxPageId", maxPageId);
			request.setAttribute("listGrammar", list);
			request.setAttribute("numberPage", pageId);
			System.out.println("max page id " + maxPageId + "number page : " + pageId);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageGrammar/ListGrammar.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int grammarId = Integer.parseInt(request.getParameter("grammarId"));
		Connection conn = DBConnection.CreatConnection();
		System.out.println("grammarId : "+grammarId+" conn : "+conn);
		
		try {
			boolean isDelete = GrammarDAO.DeleteGrammar(conn, grammarId);
			if(isDelete){
				response.sendRedirect("AdminGrammarController?pageId=1"); 	
				/*
				 * RequestDispatcher rd =
				 * request.getRequestDispatcher("AdminGrammarController?pageId=1");
				 * rd.forward(request, response);
				 */
			}else {
				request.setAttribute("msg", "Delete Failed");
				RequestDispatcher rd = request.getRequestDispatcher("AdminGrammarController?pageId=1");
				rd.forward(request, response);
			}
			conn.close();
		}catch(SQLException error) {
			error.printStackTrace();
		}
				
	}

}
