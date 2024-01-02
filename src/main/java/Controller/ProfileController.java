package Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import BEAN.Member;
import DAO.ProfileDAO;
import DB.DBConnection;
import util.constant;

/**
 * Servlet implementation class ProfileController
 */
@MultipartConfig
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBConnection.CreatConnection();
			HttpSession session = request.getSession();
			Member userInfo = ProfileDAO.GetUserInfo(conn, (String)session.getAttribute(constant.ESession.MEMBERNAME.name()));
			System.out.println("CREATE AT: "+userInfo.getCreateAt());
			request.setAttribute("userInfo", userInfo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("View/Main/Profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreatConnection();
		Part part = request.getPart("avatar");
		String realPath = request.getServletContext().getRealPath("/img/profile");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		System.out.println("file name : " + fileName + " realPath : " + realPath);
		part.write(realPath + "/" + fileName);
		try {
			HttpSession session = request.getSession();
			String username = session.getAttribute(constant.ESession.MEMBERNAME.name()).toString();
			boolean isSucess = ProfileDAO.UploadUserAvatar(conn, username, fileName);
			if(isSucess) {
				session.setAttribute(constant.ESession.MEMBERAVATAR.name(), fileName);
				response.sendRedirect("ProfileController");
			}else {
				request.setAttribute("msg", "Upload failed");
				request.getRequestDispatcher("ProfileController").forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
