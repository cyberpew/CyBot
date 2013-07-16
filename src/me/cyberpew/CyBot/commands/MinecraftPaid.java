package me.cyberpew.CyBot.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class MinecraftPaid extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		if (event.getMessage().split(" ").length > 1) {
			
			if (event.getMessage().equals(".mcpaid")) {
				
				event.respond("Please specify a user.");
			}
			
			String userarg = event.getMessage().split(" ")[1];
			if (event.getMessage().startsWith(".mcpaid ") && event.getMessage().contains(userarg)) {
					URL url = new URL("http://minecraft.net/haspaid.jsp?user=" + userarg);
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
					String str;
					while ((str = in.readLine()) != null) {
						if(str.equalsIgnoreCase("true")){
							event.respond("The user " + userarg + Colors.GREEN + " has" + Colors.NORMAL + " paid for Minecraft!");
						}
						if(str.equalsIgnoreCase("false")){
							event.respond("The user " + userarg + " has " + Colors.RED + "not " + Colors.NORMAL + "paid for Minecraft!");
					}
				}
			}
		}
	}
}
