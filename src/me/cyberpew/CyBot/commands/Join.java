package me.cyberpew.CyBot.commands;

import me.cyberpew.CyBot.Config;
import me.cyberpew.CyBot.CyBot;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Join extends ListenerAdapter {

    public void onMessage(MessageEvent event) throws Exception {
        if (event.getMessage().split(" ").length > 1) {

            if (event.getMessage().equals(".join")) {
                event.respond("Please specify a channel to join.");
            }

            String channelarg = event.getMessage().split(" ")[1];
            if (event.getMessage().startsWith(".join ") && event.getMessage().contains(channelarg)) {

                String admin = "";
                for (int i = 0; i < Config.admins.length; i++) {
                    admin += Config.admins[i];
                }

                if (admin.contains(event.getUser().getNick())) {
                    CyBot.bot.sendRawLineNow("join" + " " + channelarg);

                    event.respond("Attempted to join channel " + channelarg);
                }
            }
        }
    }

}
