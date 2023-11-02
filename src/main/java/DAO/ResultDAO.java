package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import BEAN.Result;;

public class ResultDAO {
	public static boolean ResultExamination(Connection conn, Result result, int examinationId, int memberId) {
		PreparedStatement ptmt = null;
		String sql = "insert into result(correct_answer, incorrect_answer, created_at, examination_id, member_id) values(?, ?, ?, ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, result.getCorrectAnswer());
			ptmt.setInt(2, result.getIncorrectAnswer());
			ptmt.setDate(3, new Date(result.getExamCompletionTime().getTime()));
			ptmt.setInt(4, examinationId);
			ptmt.setInt(5, memberId);
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
