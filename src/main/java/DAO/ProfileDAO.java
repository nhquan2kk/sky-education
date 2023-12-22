package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BEAN.Examination;
import BEAN.Member;

public class ProfileDAO {
	public static String GetUserAvatar(Connection conn, Member acc) {
		PreparedStatement ptmt = null;
		String sql = "select avatar from member where username = '"+acc.getUsername()+"'";
		String avatarSrc = "default.jpg";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				avatarSrc = rs.getString("avatar");
			}		
			rs.close();
			ptmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return avatarSrc;
	}
	public static Member GetUserInfo(Connection conn, String username) {
		PreparedStatement ptmt = null;
		String sql = "select avatar, username, full_name, create_at from member where username = '"+username+"'";
		Member userInfo = new Member();
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				userInfo.setAvatar(rs.getString("avatar"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setFullName(rs.getString("full_name"));
				userInfo.setCreateAt(rs.getDate("create_at"));
			}		
			rs.close();
			ptmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	public static boolean UploadUserAvatar(Connection conn, String username, String avatarSrc) {
		PreparedStatement ptmt = null;
		String sql = "update member set avatar = ? where username = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, avatarSrc);
			ptmt.setString(2, username);
			ptmt.executeUpdate();
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
