package me.cyberpew.CyBot.features;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PartEvent;

import me.cyberpew.CyBot.CyBot;

@SuppressWarnings("rawtypes")
public class SeenMgr extends ListenerAdapter {
	
	public void onJoin(JoinEvent event) throws SQLException {
		String user = event.getUser().getNick();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String lastseen = dateFormat.format(date);
		CyBot.mysql.open();
		
		ResultSet rs = CyBot.mysql.query("SELECT * FROM seenusers WHERE user='" + user + "'");
		
		if(rs.next()) {
			CyBot.mysql.query("UPDATE seenusers SET lastseen = '"+lastseen+"' WHERE user='"+ user +"'");
		} else {
			CyBot.mysql.query("INSERT INTO seenusers (user, lastseen) VALUES ('" + user + "', '" + lastseen + "') "); 
		}
		CyBot.mysql.close();
	}
	
	public void onPart(PartEvent event) {
		String user = event.getUser().getNick();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String lastseen = dateFormat.format(date);
		
		CyBot.mysql.open();
		CyBot.mysql.query("UPDATE seenusers SET lastseen = '"+lastseen+"' WHERE user='"+ user +"'");
		CyBot.mysql.close();
	}
	
	public void onMessage(MessageEvent event) {
		CyBot.mysql.open();
		String user = event.getUser().getNick();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String lastseen = dateFormat.format(date);
		try {
			CyBot.mysql.query("UPDATE seenusers SET lastseen = '"+lastseen+"' WHERE user='"+ user +"'");
		} catch (Exception e){
			
		}
	}

}
