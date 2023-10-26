package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Comment;
import BEAN.Grammar;
import BEAN.GrammarDetail;
import DAO.CommentDAO;
import DAO.DetailGrammarDAO;
import DAO.GrammarDAO;
import DB.DBConnection;

@WebServlet("/DetailGrammarController")
public class DetailGrammarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailGrammarController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int grammarId = Integer.parseInt(request.getParameter("grammarId"));
		Connection conn = DBConnection.CreatConnection();
		Grammar guide = DetailGrammarDAO.GrammarDetail(grammarId, conn);
		request.setAttribute("grammarData", guide);
		List<Comment> listComments = CommentDAO.DisplayComment(conn, grammarId);
		List<GrammarDetail> relatedGrammarList = GrammarDAO.RelatedGrammar(conn, grammarId);
		int commentsCount = CommentDAO.commentsCount(conn, grammarId);
		request.setAttribute("listCommentGrammar", listComments);
		request.setAttribute("commentsMain", commentsCount);
		request.setAttribute("relatedGrammarList", relatedGrammarList);
		request.setAttribute("grammarId", grammarId);
		request.setAttribute("characterDatabase", "\n");
		request.setAttribute("characterHTML", "<br/>");
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Grammar/DetailGrammar.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
