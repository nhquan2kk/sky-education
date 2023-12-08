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
import util.constant;
import util.helpers;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		System.out.println("LOGIN CONTROLLER");
		if(httpSession.getAttribute(constant.ESession.MEMBERID.name()) != null) {
			if((int)httpSession.getAttribute(constant.ESession.MEMBERROLE.name()) == constant.ERole.ADMIN.getValue()) 
				response.sendRedirect("AdminHomeController");
			else 
				response.sendRedirect("HomeController");
			return;
		}
		request.setAttribute("url", request.getParameter("url"));
		RequestDispatcher rd = request.getRequestDispatcher("View/Shared/Login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession httpSession = request.getSession(true);
		Connection conn = DBConnection.CreatConnection();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Member acc = new Member();
		acc.setUsername(username);
		acc.setPassword(helpers.getMd5(password));
		try {
			boolean isAuth = LoginDAO.AuthenticationMember(conn, acc);
			if (isAuth) {
				int roleId = LoginDAO.AuthorticationMember(conn, acc);
				httpSession.setAttribute("sessionMemberId", LoginDAO.GetMemberId(conn, acc));
				httpSession.setAttribute(constant.ESession.MEMBERID.name(), LoginDAO.GetMemberId(conn, acc));
				httpSession.setAttribute(constant.ESession.MEMBERROLE.name(), LoginDAO.GetRoleId(conn, acc));
				httpSession.setAttribute(constant.ESession.MEMBERNAME.name(), username);
				httpSession.setAttribute("sessionUser", username);
				
				conn.close();
				String uri = request.getParameter("url");
				if (uri.length() > 0 && uri != null) {
					uri = uri.substring(15);
					response.sendRedirect(uri);
					return;
				}
				response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "0");
				if (roleId == 1) {
					response.sendRedirect("HomeController"); // use this method that replace 'LoginController' url to HomeController url
//					RequestDispatcher rd = request.getRequestDispatcher("HomeController"); // use this method don't make url different
//					rd.forward(request, response);
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
