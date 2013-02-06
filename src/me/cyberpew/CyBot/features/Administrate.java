package me.cyberpew.CyBot.features;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Administrate extends ListenerAdapter {

	public void onMessage(MessageEvent e) {
		String prefix = ".";
		String owners = "Cyberpew";
		
		if(e.getMessage().equalsIgnoreCase(prefix + "kill")){
			if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
				String reason = String.format("Killed by %s in channel '%s'", e.getUser().getNick(), e.getChannel().getName());
				for(Channel chan : e.getBot().getChannels()) e.getBot().sendMessage(chan, reason); 
				e.getBot().quitServer(reason);
				System.exit(1);
			}else{
				e.respond("Sorry, you need op to do this.");
			}
		}
		if(e.getMessage().toLowerCase().startsWith(prefix + "op")){
			String[] arguments = e.getMessage().split(" ");
			if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
				if(arguments.length == 2){
					User u = e.getBot().getUser(arguments[1]);
					e.getBot().op(e.getChannel(), u);
				}else{
					e.respond("Usage: op <username>");
				}
			}else{
				e.respond("Sorry, you need op to do this.");
			}
		}
		if(e.getMessage().toLowerCase().startsWith(prefix + "deop")){
			if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
				String[] arguments = e.getMessage().split(" ");
				if(arguments.length == 2){
					User u = e.getBot().getUser(arguments[1]);
					e.getBot().deOp(e.getChannel(), u);
				}
			}else{
				e.respond("Usage: deop <username>");
			}
		}
		if(e.getMessage().toLowerCase().startsWith(prefix + "voice")){
			if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
				String[] arguments = e.getMessage().split(" ");
				if(arguments.length == 2){
					User u = e.getBot().getUser(arguments[1]);
					e.getBot().voice(e.getChannel(), u);
				}else{
					e.respond("Usage: voice <username>");
				}
			}else{
				e.respond("Sorry, you need op to do this.");
			}
		}
		if(e.getMessage().toLowerCase().startsWith(prefix + "devoice")){
			if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
				String[] arguments = e.getMessage().split(" ");
				if(arguments.length == 2){
					User u = e.getBot().getUser(arguments[1]);
					e.getBot().deVoice(e.getChannel(), u);
				}else{
					e.respond("Usage: devoice <username>");
				}
			}else{
				e.respond("Sorry, you need op to do this.");
			}
		}
		if(e.getMessage().toLowerCase().startsWith(prefix + "ban")){
			if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
				String[] arguments = e.getMessage().split(" ");
				if(arguments.length == 2){
					User u = e.getBot().getUser(arguments[1]);
					e.getBot().ban(e.getChannel(), u.getNick());
					e.getBot().kick(e.getChannel(), u, "ban requested by " +  e.getUser().getNick());
				}else{
					e.respond("Usage: ban <username>");
				}
			}else{
				e.respond("Sorry, you need op to do this.");
			}
		}
		if(e.getMessage().toLowerCase().startsWith(prefix + "info")){
			String[] args = e.getMessage().split(" ");
			if(args.length == 2){
				User u = e.getBot().getUser(args[1]);
				String login = u.getLogin();
				String hostmask = u.getHostmask();
				String nick = u.getNick();
				String realname = u.getRealName();
				String server = u.getServer();
				String format = String.format("====info for %s==== \n login: %s\n hostmask: %s\n nick: %s\n realname: %s\n server: %s\n channels: %s\n ====end info for user %s====", 
						nick, login, hostmask, nick, realname, server, nick);
				System.out.println(format);
			}
		}		
if(e.getMessage().toLowerCase().startsWith(prefix + "quiet")){
	String[] arguments = e.getMessage().split(" ");
	if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
		if(arguments.length == 2){
			User u = e.getBot().getUser(arguments[1]);
			e.getBot().setMode(e.getChannel(), "+q " + u.getNick());
		}else{
			e.respond("Usage: quiet <username>");
		}
	}else{
		e.respond("Sorry, you need op to do this.");
	}
}
if(e.getMessage().toLowerCase().startsWith(prefix + "unquiet")){
	String[] arguments = e.getMessage().split(" ");
	if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
		if(arguments.length == 2){
			User u = e.getBot().getUser(arguments[1]);
			e.getBot().setMode(e.getChannel(), "-q " + u.getNick());
		}else{
			e.respond("Usage: unquiet <username>");
		}
	}else{
		e.respond("Sorry, you need op to do this.");
	}
}
if(e.getMessage().toLowerCase().startsWith(prefix + "kick")){
	String[] arguments = e.getMessage().split(" ");
	if(e.getChannel().getOps().contains(e.getUser()) || owners.contains(e.getUser().getNick())){
		if(arguments.length == 2){
			User u = e.getBot().getUser(arguments[1]);
			e.getBot().kick(e.getChannel(), u, "kick requested by " + e.getUser().getNick());
		}else{
			e.respond("Usage: kick <username>");
		}
	}else{
		e.respond("Sorry, you need op to do this.");
			}
		}
	}
}