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

import BEAN.ReadingQuiz;

public class ReadingQuizDAO {
	public static List<ReadingQuiz> DetailReadingQuiz(Connection conn, int readingId) {
		List<ReadingQuiz> readingQuizList = new ArrayList<ReadingQuiz>();
		String sql = "select * from reading_quiz where reading_id = " + readingId;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				ReadingQuiz readingQuiz = new ReadingQuiz();
				readingQuiz.setOrdinalNum(rs.getInt("ordinal_num"));
				readingQuiz.setQuestion(rs.getString("question"));
				readingQuiz.setOption1(rs.getString("option_1"));
				readingQuiz.setOption2(rs.getString("option_2"));
				readingQuiz.setOption3(rs.getString("option_3"));
				readingQuiz.setOption4(rs.getString("option_4"));
				readingQuiz.setCorectAnswer(rs.getString("correct_answer"));
				readingQuizList.add(readingQuiz);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return readingQuizList;
	}

	public static ReadingQuiz CreateInstance(Row row, int readingId) {
		int ordinalNum = (int) row.getCell(0).getNumericCellValue();
		String question = row.getCell(1).getStringCellValue();
		String option1 = row.getCell(2).getStringCellValue();
		String option2 = row.getCell(3).getStringCellValue();
		String option3 = row.getCell(4).getStringCellValue();
		String option4 = row.getCell(5).getStringCellValue();
		String correctAnswer = row.getCell(6).getStringCellValue();

		ReadingQuiz rq = new ReadingQuiz();
		rq.setOrdinalNum(ordinalNum);
		rq.setQuestion(question);
		rq.setOption1(option1);
		rq.setOption2(option2);
		rq.setOption3(option3);
		rq.setOption4(option4);
		rq.setCorectAnswer(correctAnswer);
		rq.setReadingId(readingId);
		return rq;
	}

	public static void ImportQuizExcel(Connection conn, int readingId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);

				CreateReadingQuiz(conn, CreateInstance(row, readingId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateQuizExcel(Connection conn, int readingId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);

				UpdateReadingQuiz(conn, CreateInstance(row, readingId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean CreateReadingQuiz(Connection conn, ReadingQuiz readingQuiz) {
		PreparedStatement ptmt = null;
		String sql = "insert into reading_quiz(ordinal_num, question,  option_1,  option_2,option_3,option_4, correct_answer, reading_id)"
				+ " values(?, ?, ?, ?, ?, ? , ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, readingQuiz.getOrdinalNum());
			ptmt.setString(2, readingQuiz.getQuestion());
			ptmt.setString(3, readingQuiz.getOption1());
			ptmt.setString(4, readingQuiz.getOption2());
			ptmt.setString(5, readingQuiz.getOption3());
			ptmt.setString(6, readingQuiz.getOption4());
			ptmt.setString(7, readingQuiz.getCorectAnswer());
			ptmt.setInt(8, readingQuiz.getReadingId());

			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean UpdateReadingQuiz(Connection conn, ReadingQuiz readingQuiz) {
		PreparedStatement ptmt = null;
		String sql = "update reading_quiz set ordinal_num = ?,question = ?, option_1 = ?, option_2 = ?, option_3 = ?, option_4 = ?, correct_answer = ? where reading_id = ? and ordinal_num = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, readingQuiz.getOrdinalNum());
			ptmt.setString(2, readingQuiz.getQuestion());
			ptmt.setString(3, readingQuiz.getOption1());
			ptmt.setString(4, readingQuiz.getOption2());
			ptmt.setString(5, readingQuiz.getOption3());
			ptmt.setString(6, readingQuiz.getOption4());
			ptmt.setString(7, readingQuiz.getCorectAnswer());
			ptmt.setInt(8, readingQuiz.getReadingId());
			ptmt.setInt(9, readingQuiz.getOrdinalNum());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int CountRow(Connection conn, int readingId) {
		int count = 0;
		String sql = "select count(*) from reading_quiz where reading_id = " + readingId;

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

	public static String ResultQuiz(Connection conn, int readingId, int ordinal_num) {
		String correctAnswer = "";
		String sql = "select correct_answer from reading_quiz where reading_id = " + readingId + " and ordinal_num = "
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