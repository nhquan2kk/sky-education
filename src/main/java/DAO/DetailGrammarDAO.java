package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Grammar;

public class DetailGrammarDAO {
	
	public static Grammar GrammarDetail(int grammarId, Connection conn){
		Grammar grammar = new Grammar();
		String sql = "select * from grammar where grammar_id = "+grammarId;
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				grammar.setImage(rs.getString("image"));
				grammar.setName(name);
				grammar.setContent(rs.getString("content"));
				grammar.setLevelId(rs.getInt("level_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grammar;
	}
}
