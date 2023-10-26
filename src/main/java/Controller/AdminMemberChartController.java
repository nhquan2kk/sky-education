package Controller;

import java.io.IOException; 
import java.nio.IntBuffer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import BEAN.ExaminationLevelChart;
import BEAN.Level;
import BEAN.MemberChart;
import DAO.ChartDAO;
import DAO.GrammarDAO;
import DAO.LevelDAO;
import DB.DBConnection;

class ChartData {
	public List<MemberChart> getNumberMembers() {
		return numberMembers;
	}

	public void setNumberMembers(List<MemberChart> numberMembers) {
		this.numberMembers = numberMembers;
	}

	public List<Integer> getEleTotal() {
		return eleTotal;
	}

	public void setEleTotal(List<Integer> eleTotal) {
		this.eleTotal = eleTotal;
	}

	public List<ExaminationLevelChart> getExLvChart() {
		return exLvChart;
	}

	public void setExLvChart(List<ExaminationLevelChart> exLvChart) {
		this.exLvChart = exLvChart;
	}

	private List<MemberChart> numberMembers;
	private List<Integer> eleTotal;
	private List<ExaminationLevelChart> exLvChart;

}

@WebServlet("/AdminMemberChartController")
public class AdminMemberChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMemberChartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection conn = DBConnection.CreatConnection();
			List<MemberChart> dataList = ChartDAO.ShowMemberChart(conn);
			int grammarTotal = GrammarDAO.CountRow(conn);
//			int examinationTotal = ExaminationDAO.CountRow(conn);
//			int vocabularyTotal = VocabularyDAO.CountRow(conn);
//			int readingTotal = ReadingDAO.CountRow(conn);
//			int listeningTotal = ListeningDAO.CountRow(conn);
			List<Integer> eleTotal = new ArrayList<Integer>();
			eleTotal.add(grammarTotal);
//			eleTotal.add(examinationTotal);
//			eleTotal.add(vocabularyTotal);
//			eleTotal.add(readingTotal);
//			eleTotal.add(listeningTotal);
			
			List<ExaminationLevelChart> exLvChartData = ChartDAO.ShowExaminationOfLevels(conn);
			

			ChartData chartData = new ChartData();
			chartData.setNumberMembers(dataList);
			chartData.setEleTotal(eleTotal);
			chartData.setExLvChart(exLvChartData);
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(chartData);
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
			conn.close();
		} catch (SQLException error) {
			error.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
