package me.cyberpew.CyBot.features;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Misc extends ListenerAdapter {

	public void onMessage(MessageEvent e) {
		String prefix = ".";
		String owners = "Cyberpew";
		
if(e.getMessage().startsWith(prefix + "stack")){
	String[] arguments = e.getMessage().split(" ");
	if(arguments.length == 2){
		User u = e.getBot().getUser(arguments[1]);
		e.getBot().sendMessage(e.getChannel(), u.getNick() +": stack: http://i.imgur.com/jacoj.jpg");
	}else{
		e.getBot().sendMessage(e.getChannel(), "stack: http://i.imgur.com/jacoj.jpg");
			}
		}
	}
}