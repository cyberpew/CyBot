package me.cyberpew.CyBot.commands;

import me.cyberpew.CyBot.Config;
import me.cyberpew.CyBot.CyBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Nick extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) {
		if (event.getMessage().split(" ").length > 1) {
			String nickname = event.getMessage().split(" ")[1];
			if (event.getMessage().startsWith(".nick ") && event.getMessage().contains(nickname)) {
				String admin = "";
				for (int i = 0; i < Config.admins.length; i++) {
					admin += Config.admins[i];
				}
				
				if (admin.contains(event.getUser().getNick())) {
					CyBot.bot.changeNick(nickname);
				}else{
					event.respond("You're not my master. :O");
				}
			}
		}
	}
}
