package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BEAN.Comment;


public class CommentDAO {
	public static boolean InsertData(Connection conn, Comment cmt, int grammarId, int memberId) {
		PreparedStatement ptmt = null;
		String sql = "insert into comment(content, grammar_id, member_id) values (?, ?, ?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, cmt.getContent());
			ptmt.setInt(2, cmt.getGrammarId());
			ptmt.setInt(3, cmt.getMemberId());
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} 
	public static List<Comment> DisplayComment(Connection conn, int grammarId){
		List<Comment> list = new ArrayList<Comment>();
		String sql = "select cmt.content, mb.username from comment as cmt inner join member as mb on cmt.member_id = mb.member_id \r\n"
				+ "inner join grammar as g on cmt.grammar_id = g.grammar_id where cmt.grammar_id = "+grammarId;
		PreparedStatement ptmt = null;
		System.out.println("sql excute comment : "+sql);
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				Comment cmt = new Comment();
				String content = rs.getString("content");
				String username = rs.getString("username");
				cmt.setContent(content);
				cmt.setUsername(username);
				list.add(cmt);
			}
			rs.close();
			ptmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static int commentsCount(Connection conn, int grammarId) {
		int count = 0;
		String sql = "select count(*) from comment where grammar_id = ?";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, grammarId);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
			ptmt.close();
		}catch(SQLException error) {
			error.printStackTrace();
		}
		return count;
	}
}
