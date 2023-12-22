package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

import BEAN.MessageContact;
import DAO.ExaminationDAO;
import DAO.GrammarDAO;
import DAO.ListeningDAO;
import DAO.ReadingDAO;
import DAO.VocabularyDAO;
import DB.DBConnection;

/**
 * Servlet implementation class HomeAdminController
 */
@WebServlet("/AdminHomeController")
public class AdminHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SocketIOServer socketIOServer = null; 
    public AdminHomeController() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		System.out.println("INIT SERVER");
		Configuration configuration = null;
		try {
			configuration = new Configuration();
		}catch(Exception e) {
			e.printStackTrace();
		}
		configuration.setHostname("localhost");
		configuration.setPort(9095);

		socketIOServer = new SocketIOServer(configuration);

		socketIOServer.addEventListener("chatevent", MessageContact.class, new DataListener<MessageContact>() {
			@Override
			public void onData(SocketIOClient client,MessageContact data, AckRequest ackRequest) throws Exception {
				System.out.println("on event from Admin Controller"+data.getBody());
				socketIOServer.getBroadcastOperations().sendEvent("chatevent", data);
			}
		});

		socketIOServer.start();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBConnection.CreatConnection();
			int grammarTotal = GrammarDAO.CountRow(conn);
			int examinationTotal = ExaminationDAO.CountRow(conn);
			int vocabularyTotal = VocabularyDAO.CountRow(conn);
			int readingTotal = ReadingDAO.CountRow(conn);
			int listeningTotal = ListeningDAO.CountRow(conn);
			
			request.setAttribute("grammarTotal", grammarTotal);
			request.setAttribute("examinationTotal", examinationTotal);
			request.setAttribute("vocabularyTotal", vocabularyTotal);
			request.setAttribute("readingTotal", readingTotal);
			request.setAttribute("listeningTotal", listeningTotal);
			request.setAttribute("currentDay", java.time.LocalDate.now());
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void destroy() {
		socketIOServer.stop();
	}
}
