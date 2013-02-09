package me.cyberpew.CyBot.features;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class NetCommands extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		String[] args = event.getMessage().split(" ");
		
		if (args[0].equals(".ip") || args[0].equals(".ipresolve")) {
			try {
				InetAddress[] ips = InetAddress.getAllByName(args[1]);
				StringBuilder iplist = new StringBuilder();
				for(InetAddress ip : ips) {
					iplist.append(ip.getHostAddress());
					iplist.append(" ");
				}
				event.respond(new StringBuilder().append(args[1]).append(" resolves to ").append(ips.length).append(" ip(s): ").append(iplist.toString()).toString());
			}catch(UnknownHostException e) {
				event.respond(new StringBuilder().append("Unknown host '").append(args[1]).append("'.").toString());
			}
		}
		if (args[0].equals(".rdns") || args[0].equals(".hostresolve")) {
			try {
				InetAddress addr = InetAddress.getByName(args[1]);
				event.respond(new StringBuilder().append(addr.getHostAddress()).append(" resolves to ").append(addr.getHostName()).toString());
			}catch(UnknownHostException e) {
				event.respond(new StringBuilder().append("Unknown host '").append(args[1]).append("'.").toString());
			}
		}
		
		if(args[0].equals(".port")) {
			Socket sock = null;
					try {
						sock = new Socket(args[1], Integer.parseInt(args[2]));
						event.respond(new StringBuilder().append(args[1]).append(":").append(args[2]).append(" is open.").toString());
						
					}catch(Exception e) {
						event.respond(new StringBuilder().append("Failed to connect to ").append(args[1]).append(":").append(args[2]).append(".").toString());
					}finally{
						if(sock != null)
								try {
										sock.close();
								}catch(IOException e) {}
					}
		}
		//add more network functions
	}
}
