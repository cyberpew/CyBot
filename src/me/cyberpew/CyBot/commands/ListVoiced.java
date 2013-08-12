package me.cyberpew.CyBot.commands;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ListVoiced extends ListenerAdapter {

    @SuppressWarnings("unchecked")
    public void onMessage(MessageEvent event) throws Exception {

        if (event.getMessage().startsWith(".listvoiced")) {
            List voicedList = new ArrayList();

            for (User u : event.getChannel().getVoices()) {
                voicedList.add(u.getNick());
            }
            String voiced = voicedList.toString().replaceAll("[\\['s']|['\\]'s']", "");
            event.respond("The current voiced users are " + voiced);
        }
    }

}
