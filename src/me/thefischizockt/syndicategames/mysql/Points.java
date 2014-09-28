package me.thefischizockt.syndicategames.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.thefischizockt.syndicategames.SyndicateGames;

public class Points {

	public static void setPoints(String uuid, int points) {
		try{
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			if(Points.isInPointsTable(uuid)) {
				st.executeUpdate("UPDATE SGPoints SET Points='"+points+"' WHERE UUID='"+uuid+"'");
			}else {
				st.executeUpdate("INSERT INTO SGPoints(UUID, Points) VALUES ('"+uuid+"','"+points+"')");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getPoints(String uuid) {
		try{
			int points = 0;
			
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM SGPoints WHERE UUID='"+uuid+"'");
			
			while(rs.next()) {
				points = rs.getInt(2);
			}
			return points;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void addPoints(String uuid, int points) {
		int oldPoints = getPoints(uuid);
		int newPoints = oldPoints+points;
		setPoints(uuid, newPoints);
	}
	
	public static void removePoints(String uuid, int points) {
		int oldPoints = getPoints(uuid);
		int newPoints = oldPoints-points;
		setPoints(uuid, newPoints);
	}
	
	public static boolean isInPointsTable(String uuid) {
		try{
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT * FROM SGPoints WHERE UUID='"+uuid+"'");
			
			while(rs.next()) {
				if(rs != null) {
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
