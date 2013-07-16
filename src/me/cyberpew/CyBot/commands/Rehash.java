package me.cyberpew.CyBot.commands;

import java.io.FileNotFoundException;

import me.cyberpew.CyBot.Config;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Rehash extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		
		if (event.getMessage().equals(".rehash")) {
			
			String admin = "";
			for (int i = 0; i < Config.admins.length; i++) {
				admin += Config.admins[i];
			}
			
			if (admin.contains(event.getUser().getNick())) {
				try {
					
					Config.loadConfig();
					
				} catch (FileNotFoundException ex) {
					
				}
				
				event.respond("Rehashing configuration.");
				
			}else{
				event.respond("You're not my master. :O");
			}
			
		}
		
	}

}
