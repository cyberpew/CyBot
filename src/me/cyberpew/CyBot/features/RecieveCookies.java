package me.cyberpew.CyBot.features;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;

@SuppressWarnings("rawtypes")
public class RecieveCookies extends ListenerAdapter {
	
	public void onAction(ActionEvent event){	
		String action = event.getAction().toLowerCase();
		String name = event.getBot().getNick().toLowerCase();
		if(action.contains("gives") && (action.contains(name)) && (action.contains("cookie"))){;
		event.getBot().sendAction(event.getChannel(), "noms a cookie. :)");
		event.getBot().sendMessage(event.getChannel(),event.getUser().getNick() + ": Thanks for the cookie! :D");
		}
	}
}
