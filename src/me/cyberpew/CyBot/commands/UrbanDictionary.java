//MESSY CODE ALERT

package me.cyberpew.CyBot.commands;

import me.cyberpew.Data.JSONParser;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;


@SuppressWarnings("rawtypes")
public class UrbanDictionary extends ListenerAdapter{
	
	private final int MAX_LENGTH = 400;

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

		    JSONObject result = JSONParser.getJSON(URBAN + URLEncoder.encode(outsay.toString(), "UTF-8"));
			String matchtype = result.getString("result_type");
			if(!"exact".equals(matchtype)) {
				event.respond("No results found.");
				return;
			}
			JSONObject best = null;
			int bestScore = Integer.MIN_VALUE;
			JSONArray definitions = result.getJSONArray("list");
			JSONObject temp;
			for(int ii = 0; ii < definitions.length(); ii++) {
				temp = definitions.getJSONObject(ii);
				int thisScore = temp.getInt("thumbs_up");
				if(thisScore > bestScore) {
					best = temp;
					bestScore = thisScore;
				}
			}
			if(best == null) {
				event.respond("No results for term found.");
				return;
			}
			definition = best.getString("definition").replaceAll("\\r\\n|\\r|\\n", " ");
			url = best.getString("permalink");

			if(definition.length() > MAX_LENGTH) {
				definition = definition.substring(0, (MAX_LENGTH-2)) + "..";
			}


			event.respond(best.getString("word") + ": " + definition);
			}
		}
	}
}
   

