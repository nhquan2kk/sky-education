package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BEAN.Grammar;
import BEAN.GrammarDetail;

public class GrammarDAO {
	public static List<Grammar> DisplayGrammar(HttpServletRequest request, Connection conn) {
		List<Grammar> list = new ArrayList<Grammar>();
		String sql = "select * from grammar";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Grammar grammar = new Grammar();
					int grammarId = rs.getInt("grammar_id");
					String name = rs.getString("name");
					String image = rs.getString("image");
					String content = rs.getString("content");
					grammar.setGrammarId(grammarId);
					grammar.setName(name);
					grammar.setImage(image);
					grammar.setContent(content);
					list.add(grammar);
				}
			} else {
				request.setAttribute("msgSuccess", "Empty Data");
			}
		} catch (SQLException e) {
			request.setAttribute("msgError", e.getMessage());
		}

		return list;
	}

	public static List<Grammar> DisplayGrammar(int start, int count, Connection conn) {
		List<Grammar> list = new ArrayList<Grammar>();
		String sql = "select * from grammar limit " + (start - 1) + ", " + count + "";
		try {
			System.out.println("sql select page : " + sql);
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Grammar grammar = new Grammar();
				int id = rs.getInt("grammar_id");
				String name = rs.getString("name");
				String image = rs.getString("image");
				grammar.setGrammarId(id);
				grammar.setName(name);
				grammar.setImage(image);
				list.add(grammar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<GrammarDetail> DisplayGrammarDetailList(int start, int count, Connection conn) {
		List<GrammarDetail> grammarDetailList = new ArrayList<GrammarDetail>();
		String sql = "select grammar.grammar_id, grammar.name, grammar.image, level.name as level_name,  (select count(*) from comment where comment.grammar_id = grammar.grammar_id) as count from grammar"
				+ " inner join level on grammar.level_id = level.level_id limit " + (start - 1) + ", " + count + "";
		try {
			System.out.println("sql select page : " + sql);
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				GrammarDetail grammarDetail = new GrammarDetail();
				int id = rs.getInt("grammar_id");
				String name = rs.getString("name");
				String image = rs.getString("image");
				String nameLevel = rs.getString("level_name");
				int countComments = rs.getInt("count");
				
				grammarDetail.setGrammarId(id);
				grammarDetail.setName(name);
				grammarDetail.setImage(image);
				grammarDetail.setNameLevel(nameLevel);
				grammarDetail.setCountComment(countComments);
				grammarDetailList.add(grammarDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grammarDetailList;
	}

	public static int CountRow(Connection conn) {
		int count = 0;
		String sql = "select count(*) from grammar";

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

	public static boolean AddGrammar(HttpServletRequest request, Connection conn, Grammar grammar, int levelId) {
		PreparedStatement ptmt = null;
		String sql = "insert into grammar(name, image, content, level_id) values(?,?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, grammar.getName());
			ptmt.setString(2, grammar.getImage());
			ptmt.setString(3, grammar.getContent());
			ptmt.setInt(4, levelId);
			int kt = ptmt.executeUpdate();
			if (kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean UpdateGrammar(HttpServletRequest request, Connection conn, Grammar grammar, int grammarId, int levelId) {
		PreparedStatement ptmt = null;
		String sql = "update grammar set name = ?, image = ?, content = ?, level_id = ? where grammar_id = ?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, grammar.getName());
			ptmt.setString(2, grammar.getImage());
			ptmt.setString(3, grammar.getContent());
			ptmt.setInt(4, levelId);
			ptmt.setInt(5, grammarId);
			int kt = ptmt.executeUpdate();
			if (kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static List<Grammar> DisplayResult(String _name, Connection conn, HttpServletRequest request) {
		List<Grammar> list = new ArrayList<Grammar>();
		String sql = "select * from grammar where name like '%" + _name + "%'";
		try {

			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				request.setAttribute("result", "Empty data");
			} else {

				while (rs.next()) {
					Grammar grammar = new Grammar();
					grammar.setGrammarId(rs.getInt("grammar_id"));
					grammar.setName(rs.getString("name"));
					grammar.setImage(rs.getString("image"));
					grammar.setContent(rs.getString("content"));
					list.add(grammar);
				}
				request.setAttribute("dataGrammar", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<GrammarDetail> RelatedGrammar(Connection conn, int grammarId) {
		List<GrammarDetail> grammarList = new ArrayList<GrammarDetail>();
		String sql = "select grammar.grammar_id, grammar.name, grammar.image, level.name as level_name,  (select count(*) from comment where comment.grammar_id = grammar.grammar_id) "
				+ "as count from grammar"
				+ " inner join level on grammar.level_id = level.level_id where grammar.grammar_id != " + grammarId + " order by rand() limit 3";
		try {
			System.out.println("sql : " + sql);
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				GrammarDetail grammar = new GrammarDetail();
				grammar.setGrammarId(rs.getInt("grammar_id"));
				grammar.setName(rs.getString("name"));
				grammar.setImage(rs.getString("image"));
				grammar.setNameLevel(rs.getString("level_name"));
				grammar.setCountComment(rs.getInt("count"));
				grammarList.add(grammar);
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}
		return grammarList;
	}

	public static boolean DeleteGrammar(Connection conn, int grammarId) {
		String sql = "delete from grammar where grammar_id = " + grammarId;
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
			System.out.println("sql delete : " + sql);
			ptmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static List<Grammar> NewGrammar(Connection conn) {
		List<Grammar> grammarList = new ArrayList<Grammar>();
		String sql = "select * from grammar  order by grammar_id desc limit 3";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Grammar grammar = new Grammar();
				grammar.setGrammarId(rs.getInt("grammar_id"));
				grammar.setName(rs.getString("name"));
				grammar.setImage(rs.getString("image"));
				grammar.setContent(rs.getString("content"));
				grammarList.add(grammar);
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}
		return grammarList;
	}
}
