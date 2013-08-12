package me.cyberpew.CyBot.commands;

import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class ListHalfOps extends ListenerAdapter {

    @SuppressWarnings("unchecked")
    public void onMessage(MessageEvent event) throws Exception {

        if (event.getMessage().startsWith(".listhops")) {
            List hopsList = new ArrayList();

            for (User u : event.getChannel().getHalfOps()) {
                hopsList.add(u.getNick());
            }
            String hops = hopsList.toString().replaceAll("[\\['s']|['\\]'s']", "");
            event.respond("The current channel half operators are " + hops);
        }
    }

}
