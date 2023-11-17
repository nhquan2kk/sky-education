package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Vocabulary;

public class VocabularyDAO {
	public static List<Vocabulary> ShowVocabularyList(Connection conn) {
		List<Vocabulary> dataVocabularys = new ArrayList<Vocabulary>();
		String sql = "select * from vocabulary_topic";
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Vocabulary vocabulary = new Vocabulary();
				vocabulary.setVocabularyId(rs.getInt("vocabulary_id"));
				vocabulary.setName(rs.getString("name"));
				vocabulary.setImage(rs.getString("image"));
				dataVocabularys.add(vocabulary);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return dataVocabularys;
	}
	public static int FindId(Connection conn) {
		PreparedStatement ptmt = null;
		String sql = "select vocabulary_id from vocabulary_topic order by vocabulary_id desc limit 1";
		int vocabularyId = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			vocabularyId = rs.getInt(1);
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		return vocabularyId;
	}
	public static boolean CreateVocabulary(Connection conn, Vocabulary vocabulary) {
		PreparedStatement ptmt = null;
		String sql = "insert into vocabulary_topic(name, image) values(?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, vocabulary.getName());
			ptmt.setString(2, vocabulary.getImage());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Vocabulary DetailVocabulary(Connection conn, int vocabularyId) {
		Vocabulary vocabulary = new Vocabulary();
		String sql = "select * from vocabulary_topic where vocabulary_id = " + vocabularyId;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				vocabulary.setVocabularyId(rs.getInt("vocabulary_id"));
				vocabulary.setName(rs.getString("name"));
				vocabulary.setImage(rs.getString("image"));
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vocabulary;
	}

	public static int CountRow(Connection conn) {
		int count = 0;
		String sql = "select count(*) from vocabulary_topic";

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

	public static List<Vocabulary> VocabularyPagination(int start, int count, Connection conn) {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		String sql = "select * from vocabulary_topic limit " + (start - 1) + ", " + count + "";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Vocabulary Vocabulary = new Vocabulary();
				int id = rs.getInt("vocabulary_id");
				String name = rs.getString("name");

				Vocabulary.setImage(rs.getString("image"));

				Vocabulary.setVocabularyId(id);
				Vocabulary.setName(name);
				list.add(Vocabulary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static boolean UpdateVocabulary(Connection conn, Vocabulary vocabulary) {
		PreparedStatement ptmt = null;
		String sql = "update vocabulary_topic set name = ?, image = ? where vocabulary_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, vocabulary.getName());
			ptmt.setString(2, vocabulary.getImage());
			ptmt.setInt(3, vocabulary.getVocabularyId());
			ptmt.executeUpdate();
			ptmt.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean DeleteVocabulary(Connection conn, int vocabularyId) {
		String sql = "{call deleteVocabulary(?)}";
		try {
			System.out.println("sql "+sql);
			CallableStatement cs = conn.prepareCall(sql);
	        cs.setInt(1, vocabularyId) ;
			cs.executeUpdate();
			cs.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
