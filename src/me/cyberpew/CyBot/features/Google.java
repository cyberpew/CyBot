package me.cyberpew.CyBot.features;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.ListenerAdapter;

import me.cyberpew.CyBot.CyBot;

import com.googleapis.ajax.common.PagedList;
import com.googleapis.ajax.schema.WebResult;
import com.googleapis.ajax.services.GoogleSearchQueryFactory;
import com.googleapis.ajax.services.WebSearchQuery;

public class Google extends ListenerAdapter {

	public void onMessage(MessageEvent e) {
		String prefix = ".";
		String owners = "Cyberpew";
		
		if(e.getMessage().toLowerCase().startsWith(prefix+ "google")){
			StringBuilder sb = new StringBuilder();
			String[] arguments = e.getMessage().split(" ");
			for (int i = 1; i < arguments.length; i++){
				sb.append(arguments[i]).append(" ");
			}
			String allArgs = sb.toString().trim();	
			GoogleSearchQueryFactory factory = GoogleSearchQueryFactory.newInstance("AIzaSyBusf7ylIW4pMmS60IBTyUPsvuPmzo9cfM");
			WebSearchQuery query = factory.newWebSearchQuery();
			PagedList<WebResult> response = query.withQuery(allArgs).list();
			String title = response.get(0).getTitle().replace("<b>", "").replace("</b>", "").replace("&#39;", "'");
			String link = response.get(0).getUrl();
			String c = response.get(0).getContent().replace("<b>", "").replace("</b>", "").replace("&#39;", "'");
			String r = String.format("%s - %s (%s)", title, c, link);
			e.respond(r);
		}
	}
}
