package me.thefischizockt.syndicategames;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import me.thefischizockt.syndicategames.command.CommandManager;
import me.thefischizockt.syndicategames.command.executor.HubExecutor;
import me.thefischizockt.syndicategames.command.executor.LeaveExecutor;
import me.thefischizockt.syndicategames.command.executor.LobbyExecutor;
import me.thefischizockt.syndicategames.listener.AsyncPlayerChatListener;
import me.thefischizockt.syndicategames.listener.BlockBreakListener;
import me.thefischizockt.syndicategames.listener.BlockPlaceListener;
import me.thefischizockt.syndicategames.listener.ChestListener;
import me.thefischizockt.syndicategames.listener.EnderFlashListener;
import me.thefischizockt.syndicategames.listener.EnderchestListener;
import me.thefischizockt.syndicategames.listener.EntityDamageByEntityListener;
import me.thefischizockt.syndicategames.listener.EntitySpawnListener;
import me.thefischizockt.syndicategames.listener.ExpBottleListener;
import me.thefischizockt.syndicategames.listener.FoodLevelChangeListener;
import me.thefischizockt.syndicategames.listener.PickupItemListener;
import me.thefischizockt.syndicategames.listener.PlayerDeathListener;
import me.thefischizockt.syndicategames.listener.PlayerJoinListener;
import me.thefischizockt.syndicategames.listener.PlayerLoginListener;
import me.thefischizockt.syndicategames.listener.PlayerMoveListener;
import me.thefischizockt.syndicategames.listener.PlayerQuitListener;
import me.thefischizockt.syndicategames.listener.PlayerRespawnListener;
import me.thefischizockt.syndicategames.listener.ServerListPingListener;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.mysql.Deaths;
import me.thefischizockt.syndicategames.mysql.Kills;
import me.thefischizockt.syndicategames.mysql.MySQL;
import me.thefischizockt.syndicategames.mysql.Points;
import me.thefischizockt.syndicategames.util.Countdown;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SyndicateGames extends JavaPlugin{
	
	public enum GameState {
		WAITING,
		INGAME,
		ENDING;
	}
	
	private static SyndicateGames instance;
	
	private GameState state = GameState.WAITING;
	
	private boolean freeze = false;
	
	private int onlinePlayers = 0;
	
	private static MySQL sql;
	
	private HashMap<Location, Inventory> chests = new HashMap<Location, Inventory>();
	
	public void onEnable() {
		instance = this;
		getConfig().addDefault("Finished-Setup", false);
		getConfig().addDefault("Fallback-Server", "lobby");
		getConfig().addDefault("MySQL.Hostname", "localhost");
		getConfig().addDefault("MySQL.Port", 3306);
		getConfig().addDefault("MySQL.Database", "syndicategames");
		getConfig().addDefault("MySQL.Username", "root");
		getConfig().addDefault("MySQL.Password", "password");
		getConfig().options().copyDefaults(true);
		saveConfig();
		registerCommand();
		registerEvents();
		sql = new MySQL(getConfig().getString("MySQL.Hostname"), getConfig().getInt("MySQL.Port"), getConfig().getString("MySQL.Database"), getConfig().getString("MySQL.Username"), getConfig().getString("MySQL.Password"));
		sql.connect();
		try{
			Statement st = sql.getConnection().createStatement();
			st.executeUpdate("CREATE TABLE IF NOT EXISTS SGPoints(UUID varchar(100), Points int(100))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS SGKills(UUID varchar(100), Kills int(100))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS SGDeaths(UUID varchar(100), Deaths int(100))");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void onDisable() {
		sql.disconnect();
	}

	public static SyndicateGames getInstance() {
		return instance;
	}
	
	public void registerCommand() {
		CommandManager cm = new CommandManager();
		cm.setup();
		getCommand("syndicategames").setExecutor(cm);
		getCommand("leave").setExecutor(new LeaveExecutor());
		getCommand("lobby").setExecutor(new LobbyExecutor());
		getCommand("hub").setExecutor(new HubExecutor());
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new FoodLevelChangeListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerLoginListener(), this);
		pm.registerEvents(new PlayerMoveListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new PlayerRespawnListener(), this);
		pm.registerEvents(new ChestListener(), this);
		pm.registerEvents(new EnderchestListener(), this);
		pm.registerEvents(new EnderFlashListener(), this);
		pm.registerEvents(new ExpBottleListener(), this);
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new BlockPlaceListener(), this);
		pm.registerEvents(new EntityDamageByEntityListener(), this);
		pm.registerEvents(new PickupItemListener(), this);
		pm.registerEvents(new EntitySpawnListener(), this);
		pm.registerEvents(new ServerListPingListener(), this);
		pm.registerEvents(new AsyncPlayerChatListener(), this);
	}
	
	public boolean isFreezed() {
		return freeze;
	}
	
	public void setFreezed(boolean freezed) {
		this.freeze = freezed;
	}
	
	public HashMap<Location, Inventory> getChests() {
		return chests;
	}
	
	public void sendWinner(Player winner, Player looser, boolean scared) {
		MessageManager.getInstance().broadcast(MessageType.GOOD, ChatColor.MAGIC + "SYNDICATEGAMES");
		if(scared) {
			MessageManager.getInstance().broadcast(MessageType.INFO, winner.getDisplayName() + " hat", looser.getDisplayName() + " weggegruselt!");
		}else {
			MessageManager.getInstance().broadcast(MessageType.INFO, winner.getDisplayName() + " hat", looser.getDisplayName() + " besiegt!");
		}
		MessageManager.getInstance().broadcast(MessageType.GOOD, ChatColor.MAGIC + "SYNDICATEGAMES");
		Points.addPoints(winner.getUniqueId().toString(), 20);
		if(Points.getPoints(looser.getUniqueId().toString()) >= 20) {
			Points.removePoints(looser.getUniqueId().toString(), 20);
		}
		Deaths.addDeath(looser.getUniqueId().toString());
		Kills.addKill(winner.getUniqueId().toString());
		restart();
	}
	
	public void restart() {
		state = GameState.ENDING;
		new Countdown(20, "Der Server restartet in %t Sekunden", "Der Server restartet jetzt", "restart",
				10,
				5,
				4,
				3,
				2,
				1).runTaskTimer(this, 0, 20);
	}
	
	public void start() {
		ScoreboardManager m = Bukkit.getScoreboardManager();
		Scoreboard board1 = m.getNewScoreboard();
		Objective o1 = board1.registerNewObjective("Opponent1", "dummy");
		o1.setDisplayName(ChatColor.GRAY + "Opponent");
		o1.setDisplaySlot(DisplaySlot.SIDEBAR);
		o1.getScore("Kills").setScore(Kills.getKills(Bukkit.getOnlinePlayers()[1].getUniqueId().toString()));
		o1.getScore("Deaths").setScore(Deaths.getDeaths(Bukkit.getOnlinePlayers()[1].getUniqueId().toString()));
		o1.getScore("Points").setScore(Points.getPoints(Bukkit.getOnlinePlayers()[1].getUniqueId().toString()));
		Bukkit.getOnlinePlayers()[0].setScoreboard(board1);
		Scoreboard board2 = m.getNewScoreboard();
		Objective o2 = board1.registerNewObjective("Opponent2", "dummy");
		o2.setDisplayName(ChatColor.GRAY + "Opponent");
		o2.setDisplaySlot(DisplaySlot.SIDEBAR);
		o2.getScore("Kills").setScore(Kills.getKills(Bukkit.getOnlinePlayers()[0].getUniqueId().toString()));
		o2.getScore("Deaths").setScore(Deaths.getDeaths(Bukkit.getOnlinePlayers()[0].getUniqueId().toString()));
		o2.getScore("Points").setScore(Points.getPoints(Bukkit.getOnlinePlayers()[0].getUniqueId().toString()));
		Bukkit.getOnlinePlayers()[1].setScoreboard(board2);
		state = GameState.INGAME;
		freeze = true;
		new Countdown(10, "Die SyndicateGames starten in %t Sekunden", "Die SyndicateGames haben gestartet", "start",
				10,
				9,
				8,
				7,
				6,
				5,
				4,
				3,
				2,
				1).runTaskTimer(this, 0, 20);
	}
	
	public boolean finishedSetup() {
		return getConfig().getBoolean("Finished-Setup");
	}
	
	public int getOnlinePlayers() {
		return onlinePlayers;
	}
	
	public void setOnlinePlayers(int op) {
		onlinePlayers = op;
		if(onlinePlayers == 2) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					Location spawn1 = LocationUtil.getLocation(getConfig().getConfigurationSection("Spawn.First"), true);
					Location spawn2 = LocationUtil.getLocation(getConfig().getConfigurationSection("Spawn.Second"), true);
					Bukkit.getOnlinePlayers()[0].teleport(spawn1);
					Bukkit.getOnlinePlayers()[1].teleport(spawn2);
					start();
				}
			}, 10L);
		}
	}
	
	public static MySQL getMySQL() {
		return sql;
	}
	
	public GameState getState() {
		return state;
	}
}
