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

import BEAN.VocabularyWord;

public class VocabularyWordDAO {
	public static List<VocabularyWord> DetailVocabulary(Connection conn, int vocabularyId) {
		List<VocabularyWord> vocabularyWordList = new ArrayList<VocabularyWord>();
		String sql = "select * from vocabulary_word where vocabulary_id = " + vocabularyId;
		System.out.println("sql query : "+sql);
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				VocabularyWord vocabularyWord = new VocabularyWord();
				vocabularyWord.setOrdinalNum(rs.getInt("ordinal_num"));
				vocabularyWord.setName(rs.getString("name"));
				vocabularyWord.setAudioMp3(rs.getString("audio_mp3"));
				vocabularyWord.setImage(rs.getString("image"));
				vocabularyWord.setMean(rs.getString("mean"));

				vocabularyWordList.add(vocabularyWord);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vocabularyWordList;
	}
	public static VocabularyWord CreateInstance(Row row, int vocabularyId) {
		int ordinalNum = (int) row.getCell(0).getNumericCellValue();
		String name = row.getCell(1).getStringCellValue();
		String image = row.getCell(2) != null ?  row.getCell(2).getStringCellValue() : "";
		String audioMP3 = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "";
		String mean = row.getCell(4).getStringCellValue();

		VocabularyWord vocabularyWord = new VocabularyWord();
		vocabularyWord.setOrdinalNum(ordinalNum);
		vocabularyWord.setVocabularyId(vocabularyId);
		vocabularyWord.setName(name);
		vocabularyWord.setImage(image);
		vocabularyWord.setAudioMp3(audioMP3);
		vocabularyWord.setMean(mean);
		
		return vocabularyWord;
	}

	public static void ImportQuizExcel(Connection conn, int vocabularyId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			System.out.println("length : "+sheet.getLastRowNum());
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);			
				CreateVocabularyWord(conn, CreateInstance(row, vocabularyId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void UpdateQuizExcel(Connection conn, int vocabularyId, String address)
			throws ServletException, IOException {
		InputStream inp;

		try {
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			Sheet sheet = wb.getSheetAt(0);
			System.out.println("length update: "+sheet.getLastRowNum());
			for (int i = 1; i <= sheet.getLastRowNum(); ++i) {
				Row row = sheet.getRow(i);			
				UpdateVocabularyWord(conn, CreateInstance(row, vocabularyId));
			}
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean CreateVocabularyWord(Connection conn, VocabularyWord vocabularyWord) {
		PreparedStatement ptmt = null;
		String sql = "insert into vocabulary_word(name,  image,  audio_mp3,mean, vocabulary_id, ordinal_num)"
				+ " values(?, ?, ?, ?, ? , ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, vocabularyWord.getName());
			ptmt.setString(2, vocabularyWord.getImage());
			ptmt.setString(3, vocabularyWord.getAudioMp3());
			ptmt.setString(4, vocabularyWord.getMean());
			ptmt.setInt(5, vocabularyWord.getVocabularyId());
			ptmt.setInt(6, vocabularyWord.getOrdinalNum());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean UpdateVocabularyWord(Connection conn, VocabularyWord vocabularyWord) {
		PreparedStatement ptmt = null;
		String sql = "update vocabulary_word set name = ?, image = ?, audio_mp3 = ?, mean = ? where "
				+ "vocabulary_id = ? and ordinal_num = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, vocabularyWord.getName());
			ptmt.setString(2, vocabularyWord.getImage());
			ptmt.setString(3, vocabularyWord.getAudioMp3());
			ptmt.setString(4, vocabularyWord.getMean());
			ptmt.setInt(5, vocabularyWord.getVocabularyId());
			ptmt.setInt(6, vocabularyWord.getOrdinalNum());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
