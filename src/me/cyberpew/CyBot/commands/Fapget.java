package me.cyberpew.CyBot.commands;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Fapget extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		String user = Colors.MAGENTA + event.getUser().getNick();
		String [] FapgetList = {
				"Boxxy",
				"Abasiophilia",
				"Erotic asphixiation",
				"Transvestic fetishism",
				"Entrails",
				"Nasophilia",
				"Salirophilia",
				"Mucus",
				"Dirty talk",
				"Mechanophilia",
				"Vomit",
				"Sleeping or unconscious people",
				"BDSM",
				"Foot fetish/Feet",
				"Amputee",
				"Navel",
				"Scat",
				"Buttocks",
				"Menstruation",
				"Trees",
				"Urination",
				"Vore",
				"Ponies",
				"Cleavage",
				"Ass",
				"Buttplugs",
				"Lesbians",
				"Gay porn",
				"Oral sex",
				"Anal sex",
				"Internal cumshots",
				"Swimwear",
				"Celebrities",
				"Interracial",
				"Asians",
				"Bondage",
				"Group sex",
				"Bukkake",
				"Orgy",
				"Yaoi",
				"Yuri",
				"Tentacle porn",
				"Traps",
				"Futa",
				"Anthromorphism",
				"Pregnant woman",
				"80s porn",
				"Vintage pornography",
				"Fursuiting",
				"Inflation",
				"Genital mutilation",
				"Cock vorarephilia - what the fuck this actually exists and " + user + Colors.GREEN + " has to fap to it!",
				"the first person found on Chatroulette or Omegle",
				"Cybering with Cleverbot",
				"My Little Pony Porn",
				"Nothing. " + user + Colors.GREEN + " has to go to bed!",
				"Redheads",
				"Santa Claus",
				"Goatse",
				"Hai2u",
				"Meatspin",
				"whatever you choose",
				"LemonParty"};
		
		int x = FapgetList.length;
		int RandomX = (int) (Math.random()* x);
		String Fapget = FapgetList[RandomX];
		
		PircBotX bot = event.getBot();
		
		if (event.getMessage().equals(".fapget")) {
			bot.sendMessage(event.getChannel(), Colors.BOLD + Colors.BLACK + Colors.REVERSE + Colors.RED + "[" + Colors.YELLOW + "FAPGET!" + Colors.RED + "] " + user + Colors.BROWN +" has to fap to "+ Colors.GREEN + Fapget);
		}
	}
}
