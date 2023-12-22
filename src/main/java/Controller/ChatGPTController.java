package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ChatGPTController
 */
@WebServlet("/ChatGPTController")
public class ChatGPTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatGPTController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("View/Main/ChatGPT.jsp").forward(request, response);
	}

	public static String extractContentFromResponse(String response) {
		int startMarker = response.indexOf("content") + 11; // Marker for where the content starts.
		int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
		return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter("msg");
		System.out.println("msg: "+message);
		String url = "https://api.openai.com/v1/chat/completions";
		String apiKey = "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"; // API key goes here
		String model = "gpt-3.5-turbo"; // current model of chatgpt api

		try {
			// Create the HTTP POST request
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Bearer " + apiKey);
			con.setRequestProperty("Content-Type", "application/json");

			// Build the request body
			String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message
					+ "\"}]}";
			con.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write(body);
			writer.flush();
			writer.close();

			// Get the response
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				res.append(inputLine);
			}
			in.close();

			// returns the extracted contents of the response.
			System.out.println("RES: "+res);
			String content = extractContentFromResponse(res.toString());
			Gson gson = new Gson();
			String jsonString = gson.toJson(content);
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
//			request.setAttribute("content", content);
//			request.getRequestDispatcher("result.jsp").forward(request, response);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
