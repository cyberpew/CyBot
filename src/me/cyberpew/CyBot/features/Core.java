package me.cyberpew.CyBot.features;
import me.cyberpew.CyBot.CyBot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Core extends ListenerAdapter {
	String prefix = ".";
	String owners = "Cyberpew";
	
public void onMessage(MessageEvent e){
	
	
	String msg = String.format("[Message] [%s] %s %s" , e.getChannel().getName(), e.getUser().getNick(), e.getMessage());
	System.out.println(msg);
	if(e.getMessage().equals(prefix + "time")){
		//if the message is ".time" then do the following
		String time = String.format("[%tD - %tT]", new java.util.Date(), new java.util.Date());	
		//make a new date object with string.format
		e.respond("The current system time is " + time);
		//respond to the event telling them the time.
	}
	
	if(e.getMessage().equalsIgnoreCase(prefix +"listops")){
		//if the message is ".ops"
		List myList = new ArrayList();
		//make a new list
		StringBuilder sb = new StringBuilder();
		//make a new string builder
		for ( User s : e.getChannel().getOps()){
			//for every op in the channel
			myList.add(s.getNick());
			//add their nickname to that list
		}
		String f1 = myList.toString().replaceAll("[\\['s']|['\\]'s']", "");
		//replace all [ and ] 's in that with nothing.
		e.respond("The current operators are " + f1);
		//tell the user who the ops in the channer are 
	}
	if(e.getMessage().equalsIgnoreCase(prefix + "part")){
		//if the message is ".part"
		if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
			//if the user who entered it was either op or me
			e.getBot().partChannel(e.getChannel());	
			//part the current channel
		}else{
			//if they are not op or dont have permission tell them so.
			e.respond("Sorry, you need op to do this.");
		}
	}
	if(e.getMessage().toLowerCase().startsWith(prefix + "join")){
		String[] arguments = e.getMessage().split(" ");
		if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
		if(arguments.length == 2){
			e.getBot().changeNick(arguments[1]);
		}
		e.getBot().joinChannel(arguments[1]);
		}else{ 
			e.respond("Usage: .join <channel>");
			//if there was more or less than 2 args
			//tell them they did it wrong.
		}
	}

	if(e.getMessage().toLowerCase().startsWith(prefix + "nick")){
		//if the message is ".nick"
		String[] arguments = e.getMessage().split(" ");
		//make a new array out of the message, and seperate them all.
		if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
			//if the user is op
			if(arguments.length == 2){
				//if the arguments count is 2
				e.getBot().changeNick(arguments[1]);
				//change the nickname to the second one
			}else{ 
				e.respond("Usage: .nick <new nickname>");
				//if there was more or less than 2 args
				//tell them they did it wrong.
			}
		}else{
			//if they arent op tell them so
			e.respond("Sorry, you need op to do this.");
		}
	}
	if(e.getMessage().toLowerCase().startsWith(prefix + "prefix")){
		String[] arguments = e.getMessage().split(" ");
		if(arguments.length == 2){
			prefix = arguments[1];
			e.respond("Prefix for " + e.getBot().getNick() + " set to " + prefix);
		}else{
			e.respond("Usage: prefix <prefix>");
		}
	}
	if(e.getMessage().toLowerCase().startsWith(prefix + "say")){
		//if the message is ".say"
		if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
			StringBuilder sb = new StringBuilder();
			String[] arguments = e.getMessage().split(" ");
			//make a new array of all args
			for (int i = 1; i < arguments.length; i++){
				sb.append(arguments[i]).append(" ");
			}
			//make them all into a single string
			String allArgs = sb.toString().trim();
			e.getBot().sendMessage(e.getChannel(), allArgs);
			//say the message.
		}else{
			e.respond("Sorry, you need to op to do this.");
		}
	}
	if(e.getMessage().toLowerCase().startsWith(prefix + "me")){
		if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
			StringBuilder sb = new StringBuilder();//from this to 
			String[] arguments = e.getMessage().split(" ");
			for (int i = 1; i < arguments.length; i++){
				sb.append(arguments[i]).append(" ");
			}
			String allArgs = sb.toString().trim();
			e.getBot().sendAction(e.getChannel(), allArgs);
		}else{
			e.respond("Sorry, you need op to do this.");
		}
	}

	if(e.getMessage().toLowerCase().startsWith(prefix + "help")){
		String[] args = e.getMessage().split(" ");
		if(args.length == 1){
		e.respond("---help---");
		e.getBot().sendMessage(e.getChannel(), "User commands: paid, mcstatus, listops, google, time");
		e.getBot().sendMessage(e.getChannel(), "Admin commands: op, deop, voice, devoice, quiet, unquiet, ban");
		e.getBot().sendMessage(e.getChannel(), "Owner commands: kill, part, say, me, nick");
		}
		if(args.length == 2){
			if(args[1].equalsIgnoreCase("mcstatus")){
				e.respond("mcstatus: checks the status of the internal minecraft servers.");
			}
			if(args[1].equalsIgnoreCase("mcstatus")){
				e.respond("paid: checks if <username> has paid for minecraft.");
			}
			if(args[1].equalsIgnoreCase("listops")){
				e.respond("listops: lists all current channel operators.");
			}
			if(args[1].equalsIgnoreCase("google")){
				e.respond("google: looks up <query> in google");
			}
			if(args[1].equalsIgnoreCase("time")){
				e.respond("time: states the current system time.");
			}
			if(args[1].equalsIgnoreCase("op")){
				e.respond("op: gives <username> operator status (note: this does not edit flags and is temporary.)");
			}
			if(args[1].equalsIgnoreCase("deop")){
				e.respond("deop: removes operator stausfrom <username> (note: this does not remove the op flag if they have it.");
			}
			if(args[1].equalsIgnoreCase("voice")){
				e.respond("voice: grants voice to <user> (note: this does not edit flags and is temporary.)");
			}
			if(args[1].equalsIgnoreCase("devoice")){
				e.respond("devoice: removes voice from <user> (note: this does not edit flags and is temporary.)");
			}
			if(args[1].equalsIgnoreCase("quiet")){
				e.respond("quiet: set the +q flag on <username> and keeps them from talking.");
			}
			if(args[1].equalsIgnoreCase("ban")){
				e.respond("ban: bans <username> from the channel.");
				}
			}
		}	
	}
}