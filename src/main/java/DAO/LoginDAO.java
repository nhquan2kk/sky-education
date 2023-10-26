package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BEAN.Member;

public class LoginDAO {
	public static boolean AuthenticationMember(Connection conn, Member acc) {
		PreparedStatement ptmt = null;
		boolean isAuth = false;
		int isHave = 0;
		String sql = "select count(*) from member where username = ? and password = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, acc.getUsername());
			ptmt.setString(2, acc.getPassword());
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			isHave = rs.getInt(1);
			isAuth = isHave == 1 ? true : false;
			rs.close();
			ptmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return isAuth;
	}
	public static int AuthorticationMember(Connection conn, Member acc) {
		PreparedStatement ptmt = null;
		String sql = "select role_id from member where username = '"+acc.getUsername()+"' and password = '"+acc.getPassword()+"' ";
		int roleId = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				roleId = rs.getInt("role_id");
			}		
			rs.close();
			ptmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return roleId;
	}
	public static int GetRoleId(Connection conn, Member acc) {
		PreparedStatement ptmt = null;
		String sql = "select role_id from member where username='"+acc.getUsername()+"' and password ='"+acc.getPassword()+"'";
		int roleId = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				roleId = rs.getInt("role_id");
			}			
			rs.close();
			ptmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return roleId;
	}
	public static int GetMemberId(Connection conn, Member acc) {
		PreparedStatement ptmt = null;
		String sql = "select member_id from member where username='"+acc.getUsername()+"' and password ='"+acc.getPassword()+"'";
		int memberId = 0;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				memberId = rs.getInt("member_id");
			}			
			rs.close();
			ptmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("memberId"+memberId);
		return memberId;
	}
}
