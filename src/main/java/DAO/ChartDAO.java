package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BEAN.ExaminationLevelChart;
import BEAN.MemberChart;

public class ChartDAO {
	public static List<MemberChart> ShowMemberChart(Connection conn){
		List<MemberChart> list = new ArrayList<MemberChart>();
		String sql = "select count(*) as count, create_at from member group by create_at";
		System.out.println("sql :"+sql);
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				MemberChart memberChart = new MemberChart();
				memberChart.setCountMember(rs.getInt("count"));
				memberChart.setCreateAt(rs.getDate("create_at"));
				list.add(memberChart);
			}
			rs.close();
			ptmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<ExaminationLevelChart> ShowExaminationOfLevels(Connection conn){
		List<ExaminationLevelChart> list = new ArrayList<ExaminationLevelChart>();
		String sql = "select count(*) as count, level.name from examination inner join level on examination.level_id = level.level_id group by level.name;";
		System.out.println("sql :"+sql);
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				ExaminationLevelChart exLvChart = new ExaminationLevelChart();
				exLvChart.setTotalExLv(rs.getInt("count"));
				exLvChart.setNameLevel(rs.getString("name"));
				list.add(exLvChart);
			}
			rs.close();
			ptmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
