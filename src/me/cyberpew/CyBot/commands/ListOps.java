package me.cyberpew.CyBot.commands;

import java.util.ArrayList;
import java.util.List;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class ListOps extends ListenerAdapter {
	
	@SuppressWarnings("unchecked")
	public void onMessage(MessageEvent event) throws Exception {
		
		if (event.getMessage().startsWith(".listops")) {
			List opsList = new ArrayList();
			
			for ( User u : event.getChannel().getOps()){
				opsList.add(u.getNick());
			}
			String ops = opsList.toString().replaceAll("[\\['s']|['\\]'s']", "");
			event.respond("The current channel operators are " + ops);
		}
	}
}
