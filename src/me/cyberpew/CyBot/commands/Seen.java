package me.cyberpew.CyBot.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.cyberpew.CyBot.CyBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Seen extends ListenerAdapter {
	public void onMessage(MessageEvent event) throws SQLException {
		if (event.getMessage().split(" ").length > 1) {
			String user = event.getMessage().split(" ")[1];
			
			if (event.getMessage().startsWith(".seen ") && event.getMessage().contains(user)) {
				CyBot.mysql.open();
				ResultSet rs = CyBot.mysql.query("SELECT * FROM seenusers WHERE user='" + user + "'");
				
				if (rs.next()) {
					String lastseen = new String(rs.getString("lastseen"));
					event.respond("I last saw this user on " + lastseen);
					
				} else {
					event.respond("I've never seen this user!");
				}
				CyBot.mysql.close();
			}
		}
	}

}
