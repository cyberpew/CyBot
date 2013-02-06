package me.cyberpew.CyBot.features;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class MinecraftStatus extends ListenerAdapter {

	public void onMessage(MessageEvent e) {
		String prefix = ".";
		String owners = "Cyberpew";

if(e.getMessage().toLowerCase().startsWith(prefix + "paid")){
	String[] args = e.getMessage().split(" ");
	try{
		String user = args[1];
		URL url = new URL("http://minecraft.net/haspaid.jsp?user=" + user);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		String str;
		while ((str = in.readLine()) != null) {
			if(str.equalsIgnoreCase("true")){
				e.respond("The user " + user + Colors.GREEN + " has" + Colors.NORMAL + " paid for minecraft!");
			}
			if(str.equalsIgnoreCase("false")){
				e.respond("The user " + user + " has " + Colors.RED + "not " + Colors.NORMAL + "paid for minecraft!");
			}
		}
		in.close();
	}catch(java.io.IOException e1){
		e1.printStackTrace();
	}
}
if(e.getMessage().toLowerCase().equalsIgnoreCase(prefix + "mcstatus")){
	try{
		URL url = new URL ("http://status.mojang.com/check");
		BufferedReader re = new BufferedReader(new InputStreamReader(url.openStream()));
		String st;
		while((st = re.readLine()) != null){
			String a = st.replace("red", Colors.RED + "Offline" + Colors.NORMAL).replace("green", Colors.GREEN + "Online" + Colors.NORMAL).replace("[", "").replace("]", "");
			String b = a.replace("{", "").replace("}", "").replace(":", " is currently ").replace("\"", "").replaceAll(",", ", ");
			e.respond(b);
		}
	}catch (Exception ex){
		ex.printStackTrace();
			}
		}
	}
}
