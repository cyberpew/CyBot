package me.cyberpew.CyBot.features;

import java.sql.ResultSet;

import me.cyberpew.CyBot.CyBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

@SuppressWarnings("rawtypes")
public class Welcome extends ListenerAdapter {
	
	public void onJoin(JoinEvent event) throws Exception {
		CyBot.mysql.open();
		String user = event.getUser().getNick();
		
		ResultSet rs = CyBot.mysql.query("SELECT * FROM joinedusers WHERE users='" + user + "'");
			
			if (rs.next()) {
				//nope
			} else {
				
				CyBot.mysql.query("INSERT INTO joinedusers (users) VALUES ('" + user + "')");
				CyBot.mysql.close();
			}
	}

}
