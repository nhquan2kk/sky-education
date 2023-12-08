package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ReadingDAO;
import DB.DBConnection;

/**
 * Servlet implementation class AdminDeleteReadingController
 */
@WebServlet("/AdminDeleteReadingController")
public class AdminDeleteReadingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteReadingController() {
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
		int readingId = Integer.parseInt(request.getParameter("readingId"));
		Connection conn = DBConnection.CreatConnection();
		
		try {
			boolean isDelete = ReadingDAO.DeleteReading(conn, readingId);
			if(isDelete){
				response.sendRedirect("AdminReadingController?pageId=1"); 	
			}else {
				request.setAttribute("msg", "Delete Failed");
				response.sendRedirect("AdminReadingController?pageId=1");
			}
			conn.close();
		}catch(SQLException error) {
			error.printStackTrace();
		}
	}

}
