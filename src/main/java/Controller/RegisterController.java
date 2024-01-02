package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Member;
import DAO.RegisterDAO;
import DB.DBConnection;
import util.helpers;


@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegisterController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Shared/Register.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreatConnection();
		String fullName =request.getParameter("fullName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Member member = new Member();
		member.setFullName(fullName);
		member.setUsername(username);
		member.setPassword(helpers.getMd5(password));
		boolean test = RegisterDAO.InsertAccount(conn, member);
		System.out.println("REGISTER: "+helpers.getMd5(password)+" "+test);
		if(test) {
			request.setAttribute("msg", "Register successfull");
			response.sendRedirect("LoginController");
//			RequestDispatcher rd = request.getRequestDispatcher("View/Shared/Login.jsp");
//			rd.forward(request, response);
		}else {
			request.setAttribute("msg", "Register error");
			RequestDispatcher rd = request.getRequestDispatcher("RegisterController");
			rd.forward(request, response);
		}
	}

}
