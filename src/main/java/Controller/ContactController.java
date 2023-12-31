package Controller;

import BEAN.MessageContact;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import org.slf4j.Logger;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;
import org.slf4j.Logger;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.DataListener;/**
 * Servlet implementation class ContactController
 */
@WebServlet("/ContactController")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SocketIOServer socketIOServer = null; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactController() {
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
		configuration.setPort(9092);

		socketIOServer = new SocketIOServer(configuration);

		socketIOServer.addEventListener("chatevent", MessageContact.class, new DataListener<MessageContact>() {
			@Override
			public void onData(SocketIOClient client,MessageContact data, AckRequest ackRequest) throws Exception {
				System.out.println("on event from Contact Controller");
				socketIOServer.getBroadcastOperations().sendEvent("chatevent", data);
			}
		});

		socketIOServer.start();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Main/Contact.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String username = "requesttest.skyeducation@gmail.com";
		final String password = "ldrz prnl thbh gjex";
		final String emailTo = "quantalaquan@gmail.com";
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.starttls.required", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		System.out.print("DO IT");
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
			msg.setSubject(subject);
			msg.setContent(content,  "text/html; charset=ISO-8859-1");
			Transport.send(msg);
			request.setAttribute("msgSuccess", "Send email successfully!");
			RequestDispatcher rd = request.getRequestDispatcher("View/Main/Contact.jsp");
			rd.forward(request, response);
//			response.sendRedirect("ContactController");
			System.out.print("DONE");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		socketIOServer.stop();
	}
}
