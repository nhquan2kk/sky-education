package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Listening;

public class ListeningDAO {
	public static List<Listening> ShowListeningList(Connection conn) {
		List<Listening> dataListenings = new ArrayList<Listening>();
		String sql = "select * from listening";
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Listening listening = new Listening();
				listening.setListenId(rs.getInt("listen_id"));
				listening.setName(rs.getString("name"));
				listening.setImage(rs.getString("image"));
				listening.setAudioMp3(rs.getString("audio_mp3"));;
				dataListenings.add(listening);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return dataListenings;
	}
	public static int FindId(Connection conn) {
		PreparedStatement ptmt = null;
		String sql = "select listen_id from listening order by listen_id desc limit 1";
		int ListeningId = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			ListeningId = rs.getInt(1);
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		return ListeningId;
	}
	public static boolean CreateListening(Connection conn, Listening listening) {
		PreparedStatement ptmt = null;
		String sql = "insert into listening(name, image, audio_mp3) values(?, ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, listening.getName());
			ptmt.setString(2, listening.getImage());
			ptmt.setString(3, listening.getAudioMp3());
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static Listening DetailListening(Connection conn, int listeningId) {
		Listening listening = new Listening();
		String sql = "select * from listening where listen_id = " + listeningId;
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				listening.setListenId(listeningId);
				listening.setName(rs.getString("name"));
				listening.setImage(rs.getString("image"));
				listening.setAudioMp3(rs.getString("audio_mp3"));
			}
			rs.close();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listening;
	}
	
	
	
	public static int CountRow(Connection conn) {
		int count = 0;
		String sql = "select count(*) from listening";
	
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
	
	public static List<Listening> ListeningPagination(int start, int count, Connection conn){
		List<Listening> dataListening = new  ArrayList<Listening>();
		String sql = "select * from listening limit "+ (start - 1) +", "+ count +"";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Listening listening = new Listening();
				listening.setListenId(rs.getInt("listen_id"));
				listening.setImage(rs.getString("image"));
				listening.setAudioMp3(rs.getString("audio_mp3"));
				listening.setName(rs.getString("name"));
				dataListening.add(listening);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataListening;
	}
	public static boolean DeleteListening(Connection conn, int listenId) {
		String sql = "{call deleteListening(?)}";
		try {
			CallableStatement cs = conn.prepareCall(sql);
	        cs.setInt(1, listenId) ;
			cs.executeUpdate();
			cs.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean UpdateListening(Connection conn, Listening listening) {
		PreparedStatement ptmt = null;
		String sql = "update listening set name = ?, image = ?, audio_mp3 = ? where listen_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, listening.getName());
			ptmt.setString(2, listening.getImage());
			ptmt.setString(3, listening.getAudioMp3());
			ptmt.setInt(4, listening.getListenId());
			ptmt.executeUpdate();
			ptmt.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
