package me.cyberpew.CyBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import me.cyberpew.CyBot.features.Google;
import me.cyberpew.CyBot.features.Core;
import me.cyberpew.CyBot.features.MinecraftStatus;
import me.cyberpew.CyBot.features.Administrate;
import me.cyberpew.CyBot.features.Misc;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.InviteEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.events.ServerResponseEvent;


public class CyBot extends ListenerAdapter {
	String prefix = ".";
	String owners = "Cyberpew";
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		PircBotX bot = new PircBotX(); // make a new bot
		System.out.println("CyBot 2.0 initialized.");
		System.out.println("Author: Cyberpew");
		System.out.println("Enter the name of the bot:");
		bot.setName(in.nextLine());
		bot.setAutoNickChange(true);
		//loadfeatures
		bot.getListenerManager().addListener(new CyBot());
		bot.getListenerManager().addListener(new Google());
		bot.getListenerManager().addListener(new Core());
		bot.getListenerManager().addListener(new MinecraftStatus());
		bot.getListenerManager().addListener(new Administrate());
		bot.getListenerManager().addListener(new Misc());
		try {
			bot.setVersion("2.0");
			bot.setLogin("CyBot");
			System.out.println("enable debug output? (true/false)");
			boolean debug = in.nextBoolean();
			if(debug){
				bot.setVerbose(true);
			}
			if(!debug){
				bot.setVerbose(false);
			}
			System.out.println("Enter the irc server you wish to connect to:");
			String srv = in.next();
			System.out.println("Connecting to "+srv + "!");
			System.out.println("this can take a few seconds...");
			if(srv != null){
				bot.connect(srv);
				System.out.println("Succesfully connected to " + bot.getServer());
			}
			System.out.println("identify with nickserv? (true/false)");
			boolean auth = in.nextBoolean();
			if(auth){
				System.out.println("Input the password for nickserv:");
				String pass = in.next();
				bot.sendRawLine("ns id " + pass);
			}
			System.out.println("Enter the channel you wish to connect to:");
			String chan = in.next();
			bot.joinChannel(chan);
			System.out.println("Connected to " + chan + "!");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	

	public void onInvite(InviteEvent e) { 
		String line = String.format("[Invite] %s invited %S to channel %S", e.getUser(), e.getBot().getNick(), e.getChannel());
		System.out.println(line);
		if(owners.contains(e.getUser())){
			e.getBot().joinChannel(e.getChannel());	
		}else{
			e.getBot().sendMessage(e.getUser(), "Sorry, you need to be on the list of owners to invite this bot!");
		}
	}	
	public void onAction(ActionEvent e){
		String a = String.format("[Action] [%s] %s %s", e.getChannel().getName(), e.getUser().getNick(), e.getAction());
		System.out.println(a);
		String action = e.getAction().toLowerCase();
		String name = e.getBot().getNick().toLowerCase();
		if(action.contains("gives") && (action.contains(name)) && (action.contains("cookies"))){;
		e.getBot().sendAction(e.getChannel(), "noms the cookies");
		e.getBot().sendMessage(e.getChannel(),e.getUser().getNick() + ": Thanks for the cookies! :D");
		}
	}
	public void onNotice(PrivateMessageEvent e){
		System.out.println("Private message from " + e.getUser().getNick());
		if(owners.contains(e.getUser().getNick())){
			System.out.println(e.getUser() + "is the bots owner");
			StringBuilder sb = new StringBuilder();
			String[] arguments = e.getMessage().split(" ");
			for (int i = 1; i < arguments.length; i++){
				sb.append(arguments[i]).append(" ");
			}
			String allArgs = sb.toString().trim();
			e.getBot().sendRawLine(allArgs);
			System.out.println("Executed raw command " + allArgs);
		}else{
			e.respond("nope.avi");
		}
	}
}