package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Level;

public class LevelDAO {
	public static List<Level> ShowLevelList(Connection conn) {
		List<Level> dataLevels = new ArrayList<Level>();
		String sql = "select * from level";
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				Level level = new Level();
				level.setIdLevel(rs.getInt("level_id"));
				level.setName(rs.getString("name"));
				level.setDescription(rs.getString("description"));
				dataLevels.add(level);
			}
			rs.close();
			ptmt.close();
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return dataLevels;
	}
}
