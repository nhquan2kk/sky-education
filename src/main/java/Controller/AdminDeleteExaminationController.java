package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ExaminationDAO;
import DB.DBConnection;

/**
 * Servlet implementation class AdminDeleteExaminationController
 */
@WebServlet("/AdminDeleteExaminationController")
public class AdminDeleteExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteExaminationController() {
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
		int examinationId = Integer.parseInt(request.getParameter("examinationId"));
		Connection conn = DBConnection.CreatConnection();
		
		try {
			boolean isDelete = ExaminationDAO.DeleteExamination(conn, examinationId);
			if(isDelete){
				response.sendRedirect("AdminExaminationController?pageId=1"); 	
			}else {
				System.out.println("Delete faild");
				
				request.setAttribute("msg", "Delete Failed");
				response.sendRedirect("AdminExaminationController?pageId=1");
			}
			conn.close();
		}catch(SQLException error) {
			error.printStackTrace();
		}
	}

}
