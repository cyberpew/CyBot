package me.cyberpew.CyBot.commands;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class GTFTS extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		if (event.getMessage().startsWith(".gtfts")) {
			String[] arguments = event.getMessage().split(" ");
			if(arguments.length == 2){
				User u = event.getBot().getUser(arguments[1]);
				event.getBot().sendMessage(event.getChannel(), u.getNick() + " needs to go the fuck to sleep!");
			}
		}
	}
}
