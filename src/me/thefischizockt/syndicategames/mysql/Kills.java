package me.thefischizockt.syndicategames.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.thefischizockt.syndicategames.SyndicateGames;

public class Kills {

	public static void setKills(String uuid, int kills) {
		try{
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			if(Kills.isInPointsTable(uuid)) {
				st.executeUpdate("UPDATE SGKills SET Kills='"+kills+"' WHERE UUID='"+uuid+"'");
			}else {
				st.executeUpdate("INSERT INTO SGKills(UUID, Kills) VALUES ('"+uuid+"','"+kills+"')");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getKills(String uuid) {
		try{
			int points = 0;
			
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM SGKills WHERE UUID='"+uuid+"'");
			
			while(rs.next()) {
				points = rs.getInt(2);
			}
			return points;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void addKill(String uuid) {
		setKills(uuid, getKills(uuid)+1);
	}
	
	public static boolean isInPointsTable(String uuid) {
		try{
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT * FROM SGKills WHERE UUID='"+uuid+"'");
			
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
