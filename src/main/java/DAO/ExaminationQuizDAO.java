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

import BEAN.ExaminationQuiz;

public class ExaminationQuizDAO {
	public static List<ExaminationQuiz> DetailExamination(Connection conn, int id) {
		List<ExaminationQuiz> dataExaminationQuiz = new ArrayList<ExaminationQuiz>();
		String sql = "select * from examination_quiz where examination_id = " + id;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				ExaminationQuiz eq = new ExaminationQuiz();
				eq.setNum(rs.getInt("ordinal_num"));
				eq.setImage(rs.getString("image_question"));
				eq.setAudioMP3(rs.getString("audio_mp3"));
				eq.setParagraph(rs.getString("paragraph"));
				eq.setQuestion(rs.getString("question"));
				eq.setOption1(rs.getString("option_1"));
				eq.setOption2(rs.getString("option_2"));
				eq.setOption3(rs.getString("option_3"));
				eq.setOption4(rs.getString("option_4"));
				eq.setCorectAnswer(rs.getString("correct_answer"));
				dataExaminationQuiz.add(eq);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataExaminationQuiz;
	}

	public static int CountRow(Connection conn, int examinationId) {
		int count = 0;
		String sql = "select count(*) from examination_quiz where examination_id = " + examinationId;

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

	public static ExaminationQuiz CreateInstance(Row row, int examinationId) {
		int num = (int) row.getCell(0).getNumericCellValue();
		
		String image = row.getCell(1) != null ?  row.getCell(1).getStringCellValue() : "";
		String audioMP3 = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "";
		String paragraph = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "";
		String question = row.getCell(4) != null ? row.getCell(4).getStringCellValue() : "";
		
		String option1 = row.getCell(5).getStringCellValue();
		String option2 = row.getCell(6).getStringCellValue();
		String option3 = row.getCell(7).getStringCellValue();
		String option4 = row.getCell(8).getStringCellValue();
		String correctanswer = row.getCell(9).getStringCellValue();

		ExaminationQuiz eq = new ExaminationQuiz();
		eq.setNum(num);
		eq.setImage(image);
		eq.setAudioMP3(audioMP3);
		eq.setParagraph(paragraph);
		eq.setQuestion(question);
		eq.setOption1(option1);
		eq.setOption2(option2);
		eq.setOption3(option3);
		eq.setOption4(option4);
		eq.setCorectAnswer(correctanswer);
		eq.setExaminationId(examinationId);
		return eq;
	}

	public static void ImportQuizExcel(Connection conn, int examinationId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			System.out.println("length : "+sheet.getLastRowNum()+"examinationId : "+examinationId);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);
				CreateExaminationQuiz(conn, CreateInstance(row, examinationId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void UpdateQuizExcel(Connection conn, int examinationId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);
				UpdateExaminationQuiz(conn, CreateInstance(row, examinationId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean CreateExaminationQuiz(Connection conn, ExaminationQuiz examinationQuiz) {
		PreparedStatement ptmt = null;
		String sql = "insert into examination_quiz(ordinal_num, image_question,  audio_mp3, paragraph, question, option_1, option_2, option_3, option_4, correct_answer, examination_id)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, examinationQuiz.getNum());
			ptmt.setString(2, examinationQuiz.getImage());
			ptmt.setString(3, examinationQuiz.getAudioMP3());
			ptmt.setString(4, examinationQuiz.getParagraph());
			ptmt.setString(5, examinationQuiz.getQuestion());
			ptmt.setString(6, examinationQuiz.getOption1());
			ptmt.setString(7, examinationQuiz.getOption2());
			ptmt.setString(8, examinationQuiz.getOption3());
			ptmt.setString(9, examinationQuiz.getOption4());
			ptmt.setString(10, examinationQuiz.getCorectAnswer());
			ptmt.setInt(11, examinationQuiz.getExaminationId());
			System.out.println("sql quiz : " + sql);
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean UpdateExaminationQuiz(Connection conn, ExaminationQuiz examinationQuiz) {
		PreparedStatement ptmt = null;
		String sql = "update examination_quiz set ordinal_num = ?, image_question = ?,  audio_mp3 = ?, paragraph = ?, question = ?, option_1 = ?, option_2 = ?, option_3 = ?, option_4 = ?, correct_answer = ? where examination_id = ? and ordinal_num = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, examinationQuiz.getNum());
			ptmt.setString(2, examinationQuiz.getImage());
			ptmt.setString(3, examinationQuiz.getAudioMP3());
			ptmt.setString(4, examinationQuiz.getParagraph());
			ptmt.setString(5, examinationQuiz.getQuestion());
			ptmt.setString(6, examinationQuiz.getOption1());
			ptmt.setString(7, examinationQuiz.getOption2());
			ptmt.setString(8, examinationQuiz.getOption3());
			ptmt.setString(9, examinationQuiz.getOption4());
			ptmt.setString(10, examinationQuiz.getCorectAnswer());
			ptmt.setInt(11, examinationQuiz.getExaminationId());
			ptmt.setInt(12, examinationQuiz.getNum());
			System.out.println("sql quiz : " + sql);
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String ResultQuiz(Connection conn, int examinationId, int ordinal_num) {
		String correctAnswer = "";
		String sql = "select correct_answer from examination_quiz where examination_id = " + examinationId
				+ " and ordinal_num = " + ordinal_num;
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
