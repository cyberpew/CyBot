package me.cyberpew.CyBot.features;

import me.cyberpew.CyBot.CyBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.InviteEvent;

@SuppressWarnings("rawtypes")
public class InviteBot extends ListenerAdapter {
	
	public void onInvite(InviteEvent event, String user, String channel) throws Exception {
		CyBot.bot.sendMessage(event.getUser(), "Attempting to join " + channel);
		CyBot.bot.sendRawLineNow("join" + " " + channel);
	}
}
