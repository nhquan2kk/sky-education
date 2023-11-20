package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Reading;


public class ReadingDAO {
	public static List<Reading> ShowReadingList(Connection conn) {
		List<Reading> dataReadings = new ArrayList<Reading>();
		String sql = "select * from reading";
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Reading reading = new Reading();
				reading.setReadingId(rs.getInt("reading_id"));
				reading.setName(rs.getString("name"));
				reading.setImage(rs.getString("image"));
				reading.setContent(rs.getString("content"));
				dataReadings.add(reading);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return dataReadings;
	}
	public static int FindId(Connection conn) {
		PreparedStatement ptmt = null;
		String sql = "select reading_id from reading order by reading_id desc limit 1";
		int readingId = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			readingId = rs.getInt(1);
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		return readingId;
	}
	public static boolean CreateReading(Connection conn, Reading reading) {
		PreparedStatement ptmt = null;
		String sql = "insert into reading(name, image, content) values(?, ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, reading.getName());
			ptmt.setString(2, reading.getImage());
			ptmt.setString(3, reading.getContent());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Reading DetailReading(Connection conn, int readingId) {
		Reading reading = new Reading();
		String sql = "select * from reading where reading_id = " + readingId;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				reading.setReadingId(readingId);
				reading.setName(rs.getString("name"));
				reading.setImage(rs.getString("image"));
				reading.setContent(rs.getString("content"));
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reading;
	}
	
	
	
	public static int CountRow(Connection conn) {
		int count = 0;
		String sql = "select count(*) from reading";
	
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
	
	public static List<Reading> ReadingPagination(int start, int count, Connection conn){
		List<Reading> dataReading = new  ArrayList<Reading>();
		String sql = "select * from reading limit "+ (start - 1) +", "+ count +"";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Reading reading = new Reading();
				reading.setReadingId(rs.getInt("reading_id"));
				reading.setImage(rs.getString("image"));
				reading.setContent(rs.getString("content"));
				reading.setName(rs.getString("name"));
				dataReading.add(reading);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataReading;
	}
	public static boolean DeleteReading(Connection conn, int readingId) {
		String sql = "{call deleteReading(?)}";
		try {
			System.out.println("sql "+sql);
			CallableStatement cs = conn.prepareCall(sql);
	        cs.setInt(1, readingId) ;
			cs.executeUpdate();
			cs.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean UpdateReading(Connection conn, Reading reading) {
		PreparedStatement ptmt = null;
		String sql = "update reading set name = ?, image = ?, content = ? where reading_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, reading.getName());
			ptmt.setString(2, reading.getImage());
			ptmt.setString(3, reading.getContent());
			ptmt.setInt(4, reading.getReadingId());
			ptmt.executeUpdate();
			ptmt.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
