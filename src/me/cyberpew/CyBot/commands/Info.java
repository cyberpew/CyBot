package me.cyberpew.CyBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Info extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		if (event.getMessage().equals(".info")) {
			event.respond("I am Cyberpew's personal assistant! Type .help for more information.");
		}
	}

}
