package me.thefischizockt.syndicategames.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.thefischizockt.syndicategames.SyndicateGames;

public class Deaths {

	public static void setDeaths(String uuid, int deaths) {
		try{
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			if(Deaths.isInPointsTable(uuid)) {
				st.executeUpdate("UPDATE SGDeaths SET Deaths='"+deaths+"' WHERE UUID='"+uuid+"'");
			}else {
				st.executeUpdate("INSERT INTO SGDeaths(UUID, Deaths) VALUES ('"+uuid+"','"+deaths+"')");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getDeaths(String uuid) {
		try{
			int points = 0;
			
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM SGDeaths WHERE UUID='"+uuid+"'");
			
			while(rs.next()) {
				points = rs.getInt(2);
			}
			return points;
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void addDeath(String uuid) {
		setDeaths(uuid, getDeaths(uuid)+1);
	}
	
	public static boolean isInPointsTable(String uuid) {
		try{
			Statement st = SyndicateGames.getMySQL().getConnection().createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT * FROM SGDeaths WHERE UUID='"+uuid+"'");
			
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
