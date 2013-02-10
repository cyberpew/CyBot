package me.cyberpew.CyBot.console;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class Console extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) {
		String msg = String.format("[Message] [%s] %s %s", event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
		System.out.println(msg);
	}
	
	public void onAction(ActionEvent event) {
		String action = String.format("[Action] [%s] %s %s", event.getChannel().getName(), event.getUser().getNick(), event.getAction());
		System.out.println(action);
	}
	
	public void onPrivateMessage(PrivateMessageEvent event) {
		String privmsg = String.format("[PM] [%s] %s", event.getUser().getNick(), event.getMessage());
		System.out.println(privmsg);
	}
	
	public void onKick(KickEvent event) {
		String kick = String.format("[Kick] [%s] %s was kicked by %s for %s", event.getChannel().getName(), event.getRecipient().getNick(), event.getSource().getNick(), event.getReason());
		System.out.println(kick);
	}
}
