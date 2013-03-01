package me.cyberpew.CyBot.features;

import me.cyberpew.CyBot.CyBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.InviteEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class InviteBot extends ListenerAdapter {
	//Plan A
	public void onInvite(InviteEvent event, String user, String channel) throws Exception {
		
		CyBot.bot.joinChannel(channel);
		CyBot.bot.sendMessage(event.getUser(), "Attempting to join " + channel);
	}
	//Plan B
	public void onPrivateMessage(PrivateMessageEvent event) throws Exception {
		
		if (event.getMessage().split(" ").length > 1) {
			
			if (event.getMessage().equals("join") || event.getMessage().equals("request")){
				event.respond("Please specify a channel to join.");
			}
			
			String channelarg = event.getMessage().split(" ")[1];
			if (event.getMessage().startsWith("join ") || event.getMessage().startsWith("request ") && event.getMessage().contains(channelarg)) {
				
				CyBot.bot.sendRawLineNow("join" + " " + channelarg);
				event.respond("Attempted to join channel " + channelarg);
			}
		}
	}
}
