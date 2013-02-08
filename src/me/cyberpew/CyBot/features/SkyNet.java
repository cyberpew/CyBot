package me.cyberpew.CyBot.features;

//import me.cyberpew.CyBot.CyBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

@SuppressWarnings("rawtypes")
public class SkyNet extends ListenerAdapter {
	
	private ChatterBotFactory factory;
	private ChatterBot cleverbot;
	private ChatterBotSession botsession;
	
	public static boolean skynetEnabled = false;
	
	public SkyNet() throws Exception {
		factory = new ChatterBotFactory();
		cleverbot = factory.create(ChatterBotType.CLEVERBOT);
		botsession = cleverbot.createSession();
	}
	
	public void onMessage(MessageEvent event) throws Exception {
		String message = event.getMessage();
		String[] ArrSay = message.split(" ");
		String outsay = "";
		
		for (int i = 1; i < ArrSay.length; i++) {
			outsay += ArrSay[i];
		}
		
		//if (event.getMessage().startsWith(CyBot.bot.getNick() + ": ")) {
			if (skynetEnabled == true) {
				
				String s = botsession.think(outsay);
				event.respond(s);
			}
		
	}
}
