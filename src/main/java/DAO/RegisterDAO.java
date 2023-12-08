package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import BEAN.Member;

public class RegisterDAO {
	public static boolean InsertAccount(Connection conn, Member acc){
		PreparedStatement ptmt = null;
		String sql = "insert into member(full_name, username, password, role_id) values (?,?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql);
			
			String fullName = acc.getFullName();
			String username = acc.getUsername();
			String password = acc.getPassword();
			int roleId = 1;
			
			ptmt.setString(1, fullName);
			ptmt.setString(2, username);
			ptmt.setString(3, password);
			ptmt.setInt(4, roleId);
			
			int kt = ptmt.executeUpdate();
			System.out.println("RESULT : "+kt);
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
