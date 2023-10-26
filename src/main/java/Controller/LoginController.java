package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Member;
import DAO.LoginDAO;
import DB.DBConnection;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Shared/Login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		if(session.getAttribute("sessionMemberId") != null) {
			response.sendRedirect("AdminHomeController");
			return;
		}
		Connection conn = DBConnection.CreatConnection();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Member acc = new Member();
		acc.setUsername(username);
		acc.setPassword(password);
		try {
			boolean isAuth = LoginDAO.AuthenticationMember(conn, acc);
			if (isAuth) {
				int roleId = LoginDAO.AuthorticationMember(conn, acc);
				session.setAttribute("sessionMemberId", LoginDAO.GetMemberId(conn, acc));
				session.setAttribute("sessionUser", username);
				conn.close();
				if (roleId == 1) {
//					response
					RequestDispatcher rd = request.getRequestDispatcher("HomeController");
					rd.forward(request, response);
				} else {
					response.sendRedirect("AdminHomeController");
//					RequestDispatcher rd = request.getRequestDispatcher("AdminHomeController");
//					rd.forward(request, response);
				}
			} else {
				conn.close();
				request.setAttribute("msg", "Invalid username or password");
				RequestDispatcher rd = request.getRequestDispatcher("View/Shared/Login.jsp");
				rd.forward(request, response);
			}
			
		} catch (SQLException error) {
			error.printStackTrace();
		}

	}

}
