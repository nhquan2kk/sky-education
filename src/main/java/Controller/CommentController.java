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

import BEAN.Comment;
import DAO.CommentDAO;


@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
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
		if(request.getCharacterEncoding() != null) {
			request.setCharacterEncoding("UTF-8");
		}
		try {
			String content = request.getParameter("content");
			String userId = request.getParameter("userId");
			String grammarId = request.getParameter("grammarId");
			int memberId = Integer.parseInt(userId);
			int grammarGuideId = Integer.parseInt(grammarId);
			System.out.println("userId : "+userId);
			System.out.println("grammarGuideId : "+grammarId);
			Connection conn = DB.DBConnection.CreatConnection();
			Comment cmt = new Comment();
			cmt.setContent(content);
			cmt.setGrammarId(grammarGuideId);
			cmt.setMemberId(memberId);
			request.setAttribute("grammarId", grammarGuideId);
			boolean isSuccess = CommentDAO.InsertData(conn, cmt, grammarGuideId, memberId);
			if(isSuccess) {
				List<Comment> listComments = CommentDAO.DisplayComment(conn, grammarGuideId);
				request.setAttribute("listCommentGrammar", listComments);
				RequestDispatcher rd = request.getRequestDispatcher("View/Main/Shared/ListComments.jsp");
				rd.forward(request, response);
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
