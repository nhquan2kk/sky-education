package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.GrammarDAO;
import DB.DBConnection;

/**
 * Servlet implementation class AdminDeleteGrammarController
 */
@WebServlet("/AdminDeleteGrammarController")
public class AdminDeleteGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteGrammarController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int grammarId = Integer.parseInt(request.getParameter("grammarId"));
		Connection conn = DBConnection.CreatConnection();
		System.out.println("grammarId : "+grammarId+" conn : "+conn);
		
//		try {
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
	}

}
