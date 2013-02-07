package me.cyberpew.CyBot.features;

import java.sql.ResultSet;

import me.cyberpew.CyBot.CyBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

public class Welcome extends ListenerAdapter {
	
	public void onJoin(JoinEvent event) throws Exception {
		CyBot.mysql.open();
		String user = event.getUser().getNick();
		String channel = event.getChannel().getName();
		
		ResultSet rs = CyBot.mysql.query("SELECT * FROM joinedusers WHERE users='" + user + "'");
		
		if (channel.equals("#cyberspace")) {
			
			if (rs.next()) {
				//nope
			} else {
				
				CyBot.mysql.query("INSERT INTO joinedusers (users) VALUES ('" + user + "')");
				CyBot.mysql.close();
				event.respond("Hello there, " + user);
			}
		}
	}
}
