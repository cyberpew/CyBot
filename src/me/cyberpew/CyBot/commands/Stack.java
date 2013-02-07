package me.cyberpew.CyBot.commands;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Stack extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		if (event.getMessage().startsWith(".stack")) {
			String[] arguments = event.getMessage().split(" ");
			if(arguments.length == 2){
				User u = event.getBot().getUser(arguments[1]);
				event.getBot().sendMessage(event.getChannel(), u.getNick() +": STACKTRACE OR GTFO! - http://i.imgur.com/jacoj.jpg");
			}else{
				event.getBot().sendMessage(event.getChannel(), "STACKTRACE OR GTFO! - http://i.imgur.com/jacoj.jpg");
			}
		}
	}
}
