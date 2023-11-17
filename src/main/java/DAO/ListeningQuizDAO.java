package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import BEAN.ListeningQuiz;

public class ListeningQuizDAO {
	public static List<ListeningQuiz> DetailListeningQuiz(Connection conn, int listenId) {
		List<ListeningQuiz> listeningQuizList = new ArrayList<ListeningQuiz>();
		String sql = "select * from listening_quiz where listen_id = " + listenId;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				ListeningQuiz listeningQuiz = new ListeningQuiz();
				listeningQuiz.setOrdinalNum(rs.getInt("ordinal_num"));
				listeningQuiz.setQuestion(rs.getString("question"));
				listeningQuiz.setOption1(rs.getString("option_1"));
				listeningQuiz.setOption2(rs.getString("option_2"));
				listeningQuiz.setOption3(rs.getString("option_3"));
				listeningQuiz.setOption4(rs.getString("option_4"));
				listeningQuiz.setCorectAnswer(rs.getString("correct_answer"));
				listeningQuizList.add(listeningQuiz);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeningQuizList;
	}

	public static ListeningQuiz CreateInstance(Row row, int listenId) {
		int ordinalNum = (int) row.getCell(0).getNumericCellValue();
		String question = row.getCell(1).getStringCellValue();
		String option1 = row.getCell(2).getStringCellValue();
		String option2 = row.getCell(3).getStringCellValue();
		String option3 = row.getCell(4).getStringCellValue();
		String option4 = row.getCell(5).getStringCellValue();
		String correctAnswer = row.getCell(6).getStringCellValue();

		ListeningQuiz rq = new ListeningQuiz();
		rq.setOrdinalNum(ordinalNum);
		rq.setQuestion(question);
		rq.setOption1(option1);
		rq.setOption2(option2);
		rq.setOption3(option3);
		rq.setOption4(option4);
		rq.setCorectAnswer(correctAnswer);
		rq.setListenId(listenId);
		return rq;
	}

	public static void ImportQuizExcel(Connection conn, int listenId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);

				CreateListeningQuiz(conn, CreateInstance(row, listenId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateQuizExcel(Connection conn, int listenId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);

				UpdateListeningQuiz(conn, CreateInstance(row, listenId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean CreateListeningQuiz(Connection conn, ListeningQuiz listeningQuiz) {
		PreparedStatement ptmt = null;
		String sql = "insert into listening_quiz(ordinal_num, question,  option_1, option_2,option_3,option_4, correct_answer, listen_id)"
				+ " values(?, ?, ?, ?, ?, ? , ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, listeningQuiz.getOrdinalNum());
			ptmt.setString(2, listeningQuiz.getQuestion());
			ptmt.setString(3, listeningQuiz.getOption1());
			ptmt.setString(4, listeningQuiz.getOption2());
			ptmt.setString(5, listeningQuiz.getOption3());
			ptmt.setString(6, listeningQuiz.getOption4());
			ptmt.setString(7, listeningQuiz.getCorectAnswer());
			ptmt.setInt(8, listeningQuiz.getListenId());

			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean UpdateListeningQuiz(Connection conn, ListeningQuiz listeningQuiz) {
		PreparedStatement ptmt = null;
		String sql = "update listening_quiz set ordinal_num = ?,question = ?, option_1 = ?, option_2 = ?, option_3 = ?, option_4 = ?, correct_answer = ? where listen_id = ? and ordinal_num = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, listeningQuiz.getOrdinalNum());
			ptmt.setString(2, listeningQuiz.getQuestion());
			ptmt.setString(3, listeningQuiz.getOption1());
			ptmt.setString(4, listeningQuiz.getOption2());
			ptmt.setString(5, listeningQuiz.getOption3());
			ptmt.setString(6, listeningQuiz.getOption4());
			ptmt.setString(7, listeningQuiz.getCorectAnswer());
			ptmt.setInt(8, listeningQuiz.getListenId());
			ptmt.setInt(9, listeningQuiz.getOrdinalNum());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int CountRow(Connection conn, int listenId) {
		int count = 0;
		String sql = "select count(*) from listening_quiz where listen_id = " + listenId;

		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static String ResultQuiz(Connection conn, int listenId, int ordinal_num) {
		String correctAnswer = "";
		String sql = "select correct_answer from listening_quiz where listen_id = " + listenId + " and ordinal_num = "
				+ ordinal_num;
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				correctAnswer = rs.getString(1);
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return correctAnswer;
	}
}
