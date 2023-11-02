package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Examination;
import DAO.ExaminationDAO;
import DB.DBConnection;


@WebServlet("/AdminExaminationController")
public class AdminExaminationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AdminExaminationController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int pageId = Integer.parseInt(request.getParameter("pageId"));
			int start = pageId;
			int COUNT = 10;
			if (start == 0)
				System.out.println("nothing here");
			else {
				start = start - 1;
				start = start * COUNT + 1;
			}
			Connection conn = DBConnection.CreatConnection();
			List<Examination> examinationsList = ExaminationDAO.ExaminationPagination(start, COUNT, conn);
			int sumRow = ExaminationDAO.CountRow(conn);
			int maxPageId = 0;

			if ((sumRow / COUNT) % 2 == 0) {
				maxPageId = sumRow / COUNT;
			} else
				maxPageId = (sumRow / COUNT) + 1;

			request.setAttribute("maxPageId", maxPageId);
			request.setAttribute("examinationsList", examinationsList);
			request.setAttribute("numberPage", pageId);
			System.out.println("max page id " + maxPageId + "number page : " + pageId);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ManageExamination/ListExamination.jsp");
			rd.forward(request, response);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int examinationId = Integer.parseInt(request.getParameter("examinationId"));
		Connection conn = DBConnection.CreatConnection();
		
//		try {
			boolean isDelete = ExaminationDAO.DeleteExamination(conn, examinationId);
			if(isDelete){
				response.sendRedirect("AdminExaminationController?pageId=1"); 	
			}else {
				request.setAttribute("msg", "Delete Failed");
				RequestDispatcher rd = request.getRequestDispatcher("AdminExaminationController?pageId=1");
				rd.forward(request, response);
			}
//			conn.close();
//		}catch(SQLException error) {
//			error.printStackTrace();
//		}
	}

}
