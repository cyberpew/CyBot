package me.cyberpew.CyBot.commands;

import me.cyberpew.CyBot.Config;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Kill extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		if (event.getMessage().startsWith(".kill")) {
			String admin = "";
			for (int i = 0; i < Config.admins.length; i++) {
				admin += Config.admins[i];
			}
			
			if (admin.contains(event.getUser().getNick())) {
				String reason = String.format("Killed by %s in channel '%s'", event.getUser().getNick(), event.getChannel().getName());
				for(Channel chan : event.getBot().getChannels()) event.getBot().sendMessage(chan, reason);
				event.getBot().quitServer(reason);
				System.exit(1);
			}else{
				event.respond("You're not my master. :O");
			}
		}
	}
}
