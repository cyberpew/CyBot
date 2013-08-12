package me.cyberpew.CyBot.commands;

import me.cyberpew.CyBot.CyBot;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Help extends ListenerAdapter {

    public void onMessage(MessageEvent event) {
        if (event.getMessage().equals(".help")) {
            event.respond("Type .help cybot - for CyBot related commands.");
        } else if (event.getMessage().equals(".help cybot")) {
            event.respond("Check your private messages, I've sent you some help files.");
            CyBot.bot.sendMessage(event.getUser(), "---------------- CyBot Helpfiles ----------------");
            CyBot.bot.sendMessage(event.getUser(), "--------------- General Commands ---------------");
            CyBot.bot.sendMessage(event.getUser(), ".info - Shows information about me.");
            CyBot.bot.sendMessage(event.getUser(), ".seen <user> - Checks when a user was last seen.");
            CyBot.bot.sendMessage(event.getUser(), ".g <query> - Uses Google search.");
            CyBot.bot.sendMessage(event.getUser(), ".ud <query> - Looks up a Urban Dictionary term.");
            CyBot.bot.sendMessage(event.getUser(), ".fapget - Gets you something to fap to.");
            CyBot.bot.sendMessage(event.getUser(), ".mcpaid <username> - Checks a Minecraft username to see if they have paid.");
            CyBot.bot.sendMessage(event.getUser(), ".mcstatus - Checks the current status of Minecraft services.");
            CyBot.bot.sendMessage(event.getUser(), ".listops - Lists the current channel operators.");
            CyBot.bot.sendMessage(event.getUser(), ".listhops - Lists the current channel half-operators.");
            CyBot.bot.sendMessage(event.getUser(), ".listvoiced - Lists the voiced users in the current channel.");
            CyBot.bot.sendMessage(event.getUser(), ".stack or .stack <user> - STACKTRACE OR GTFO.");
            CyBot.bot.sendMessage(event.getUser(), ".gtfts <user> - Tells a user to go to sleep.");
            CyBot.bot.sendMessage(event.getUser(), "---------------- Want CyBot in your channel? ----------------");
            CyBot.bot.sendMessage(event.getUser(), "Type '.help cybot join' for information.");
            CyBot.bot.sendMessage(event.getUser(), "---------------- Bot Admin only commands ----------------");
            CyBot.bot.sendMessage(event.getUser(), "For bot admin commands type '.help cybot botadmin'");
        } else if (event.getMessage().equals(".help cybot botadmin")) {
            CyBot.bot.sendMessage(event.getUser(), "---------------- CyBot Helpfiles ----------------");
            CyBot.bot.sendMessage(event.getUser(), "-------------- CyBot Admin Commands -------------");
            CyBot.bot.sendMessage(event.getUser(), "Coming soon...");
        } else if (event.getMessage().equals(".help cybot join")) {
            CyBot.bot.sendMessage(event.getUser(), "---------------- CyBot Helpfiles ----------------");
            CyBot.bot.sendMessage(event.getUser(), "------ Getting CyBot to join your channel? ------");
            CyBot.bot.sendMessage(event.getUser(), "To get CyBot to join your channel, simply type /invite CyBot #yourchannel");
            CyBot.bot.sendMessage(event.getUser(), "Ex. /invite CyBot #Cybot");
            CyBot.bot.sendMessage(event.getUser(), "Want him removed? Do /msg Cyberpew remove CyBot #yourchannel");
            CyBot.bot.sendMessage(event.getUser(), "---------------- Want CyBot to auto-join your channel? ----------------");
            CyBot.bot.sendMessage(event.getUser(), "Type '.help cybot autojoin' for information.");
        } else if (event.getMessage().equals(".help cybot autojoin")) {
            CyBot.bot.sendMessage(event.getUser(), "---------------- CyBot Helpfiles ----------------");
            CyBot.bot.sendMessage(event.getUser(), "--------------- CyBot Auto-joining ---------------");
            CyBot.bot.sendMessage(event.getUser(), "If you'd like me to auto-join your channel whenever I am restarted, please type /msg Cyberpew AUTOJOIN CyBot to #yourchannel");
        }
    }
}
