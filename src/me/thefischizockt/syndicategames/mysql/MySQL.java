package me.thefischizockt.syndicategames.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

public class MySQL {

	public static Connection con;
	
	private final String host;
	private final int port;
	private final String database;
	private final String username;
	private final String password;
	
	public MySQL(String host, int port, String database, String username, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	
	public Connection connect() {
		try{
			con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?autoReconnect=true", username, password);
			return con;
		}catch(SQLException e) {
			MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "Konnte keine Verbindung zum MySQL-Server aufbauen");
			return null;
		}
	}
	
	public void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "Fehler beim Schliessen der Verbindung: " + e.getMessage());
		}
	}
	
	public boolean hasConnection() {
		try {
			return con.isValid(1) || con != null;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public Connection getConnection() {
		if(!hasConnection()){
			return connect();
		}else {
			return con;
		}
	}
	
}
