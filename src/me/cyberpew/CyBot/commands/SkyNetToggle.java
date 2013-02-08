package me.cyberpew.CyBot.commands;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import me.cyberpew.CyBot.Config;
import me.cyberpew.CyBot.features.SkyNet;

@SuppressWarnings("rawtypes")
public class SkyNetToggle extends ListenerAdapter {
	
	 public void onMessage(MessageEvent event) throws Exception {
		 if (event.getMessage().equals(".skyneton")) {
			 
			 String admin = "";
			 for (int i = 0; i < Config.admins.length; i++) {
				 admin += Config.admins[i];
			 }
			 
			 if (admin.contains(event.getUser().getNick())) {
				 
				 SkyNet.skynetEnabled = true;
				 
				 event.respond("SkyNet enabled.");
			 }else{
				 event.respond("You're not my master. :O");
			 }
		 }
		 
		 if (event.getMessage().equals(".skynetoff")) {
			 
			 String admin = "";
			 for (int i = 0; i < Config.admins.length; i++) {
				 admin += Config.admins[i];
			 }
			 
			 if (admin.contains(event.getUser().getNick())) {
				 
				 SkyNet.skynetEnabled = false;
				 
				 event.respond("SkyNet disabled.");
			 }else{
				 event.respond("You're not my master. :O");
			 }
		 }
	 }
}