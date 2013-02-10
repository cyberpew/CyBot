//MESSY CODE ALERT

package me.cyberpew.CyBot.commands;


import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.google.gson.Gson;


@SuppressWarnings("rawtypes")
public class UrbanDictionary extends ListenerAdapter{
	
	private final int MAX_LENGTH = 400;

	private Gson gson = new Gson();

    @SuppressWarnings("resource")
	public void onMessage(MessageEvent event) throws Exception {
	if (event.getMessage().split(" ").length > 1) {
	    String message = event.getMessage();

	    String[] ArrSay = message.split(" ");
	    String outsay = "";

	    for (int i = 1; i < ArrSay.length; i++) {
		    if (ArrSay[i].contains("'")) {
			String temp = "";
			String temp2 = ArrSay[i];
			for (int j = 0; j < temp2.length(); j++) {
			    String temp3 = "" + temp2.charAt(j);
			    if (!temp3.equals("'")) {
				temp += temp3;
			    }
			}
			outsay += temp;
		    } else {
			outsay += ArrSay[i];
		    }
		    if (i != ArrSay.length - 1) {
			outsay += " ";
		    }
		}
	    
	    String definition = "";
		@SuppressWarnings("unused")
		String url = "";

	    if (event.getMessage().startsWith(".ud ") && event.getMessage().contains(outsay)) {

		    String URBAN = "http://api.urbandictionary.com/v0/define?term=";
		    final URLConnection c = new URL(URBAN + URLEncoder.encode(outsay.toString(), "UTF-8")).openConnection();
		    final Scanner s = new Scanner(c.getInputStream());
		    final StringBuffer buffer = new StringBuffer();
		    while(s.hasNextLine()) {
		            buffer.append(s.nextLine());
		    }
		    Urban result = gson.fromJson(buffer.toString(), Urban.class);
			String matchtype = result.result_type;
			if(!"exact".equals(matchtype)) {
				event.respond("No results found.");
				return;
			}
			Urban.Item best = null;
			int bestScore = Integer.MIN_VALUE;
			Urban.Item temp;
			for(int ii = 0; ii < result.list.length; ii++) {
				temp = result.list[ii];
				int thisScore = temp.thumbs_up;
				if(thisScore > bestScore) {
					best = temp;
					bestScore = thisScore;
				}
			}
			if(best == null) {
				event.respond("No results for term found.");
				return;
			}
			definition = best.definition.replaceAll("\\r\\n|\\r|\\n", " ");
			url = best.permalink;

			if(definition.length() > MAX_LENGTH) {
				definition = definition.substring(0, (MAX_LENGTH-2)) + "..";
			}


			event.respond(best.word + ": " + definition);
			}
		}
	}
    
    private class Urban {
        public String result_type;
        public Item[] list; 
        public class Item {
            public int thumbs_up;
            public String definition;
            public String permalink;
            public String word;
        }
    }
}
   

