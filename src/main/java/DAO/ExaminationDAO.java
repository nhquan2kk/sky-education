package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import BEAN.Examination;
import BEAN.ExaminationDetail;
public class ExaminationDAO {
	public static boolean CreateExamination(Connection conn, Examination examination, int levelId) {
		PreparedStatement ptmt = null;
		String sql = "insert into examination(name, image, level_id) values(?, ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, examination.getName());
			ptmt.setString(2, examination.getImage());
			ptmt.setInt(3, levelId);
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean UpdateExamination(Connection conn, Examination examination, int levelId) {
		PreparedStatement ptmt = null;
		String sql = "update examination set name = ?, image = ?, level_id = ? where examination_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, examination.getName());
			ptmt.setString(2, examination.getImage());
			ptmt.setInt(3, levelId);
			ptmt.setInt(4, examination.getExaminationId());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static int FindId(Connection conn) {
		PreparedStatement ptmt = null;
		String sql = "select examination_id from examination order by examination_id desc limit 1";
		int examinationId = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			examinationId = rs.getInt(1);
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		return examinationId;
	}
	public static List<Examination> ShowExaminations(Connection conn) {
		List<Examination> dataExaminations = new ArrayList<Examination>();
		String sql = "select * from examination";
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Examination examination = new Examination();
				examination.setExaminationId(rs.getInt("examination_id"));
				examination.setName(rs.getString("name"));
				examination.setImage(rs.getString("image"));
				dataExaminations.add(examination);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return dataExaminations;
	}
	
	public static int CountRow(Connection conn) {
		int count = 0;
		String sql = "select count(*) from examination";
	
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

	public static Examination DetailExamination(Connection conn, int id) {
		Examination examination = new Examination();
		String sql = "select * from examination where examination_id = " + id;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				examination.setExaminationId(rs.getInt("examination_id"));
				examination.setName(rs.getString("name"));
				examination.setImage(rs.getString("image"));
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return examination;
	}

	
	public static List<Examination> ExaminationPagination(int start, int count, Connection conn){
		List<Examination> list = new  ArrayList<Examination>();
		String sql = "select * from examination limit "+ (start - 1) +", "+ count +"";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Examination examination = new Examination();
				int id = rs.getInt("examination_id");
				String name = rs.getString("name");
				
				examination.setImage(rs.getString("image"));

				examination.setExaminationId(id);
				examination.setName(name);
				list.add(examination);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<ExaminationDetail> ExaminationDetailPagination(int start, int count, Connection conn){
		List<ExaminationDetail> list = new  ArrayList<ExaminationDetail>();
		String sql = "select examination.*, level.name as level_name from examination inner join level on examination.level_id = level.level_id limit "+ (start - 1) +", "+ count +"";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				ExaminationDetail examination = new ExaminationDetail();
				int id = rs.getInt("examination_id");
				String name = rs.getString("name");
				examination.setImage(rs.getString("image"));
				examination.setNameLevel(rs.getString("level_name"));
				examination.setExaminationId(id);
				examination.setName(name);
				list.add(examination);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Examination> NewExamination( Connection conn){
		List<Examination> listExamination = new  ArrayList<Examination>();
		String sql = "select * from examination order by examination_id desc limit 3";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Examination examination = new Examination();
				examination.setImage(rs.getString("image"));
				examination.setExaminationId(rs.getInt("examination_id"));
				examination.setName(rs.getString("name"));
				listExamination.add(examination);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listExamination;
	}
	public static boolean DeleteExamination(Connection conn, int examinationId) {
		String sql = "{call deleteExamination("+examinationId+")}";
		try {
			System.out.println("sql "+sql);
			CallableStatement cs = conn.prepareCall(sql);
//	        cs.setInt(1, ;
			cs.executeUpdate();
			cs.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
