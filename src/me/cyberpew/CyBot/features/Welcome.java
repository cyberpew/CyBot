package me.cyberpew.CyBot.features;

import me.cyberpew.CyBot.CyBot;
import me.cyberpew.data.MySQL;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;

import java.sql.ResultSet;

@SuppressWarnings("rawtypes")
public class Welcome extends ListenerAdapter {

    public void onJoin(JoinEvent event) throws Exception {
        MySQL mysql = new MySQL(CyBot.logger, "[CyBot]", CyBot.mysql_host, CyBot.mysql_port, CyBot.mysql_db, CyBot.mysql_user, CyBot.mysql_pass);

        mysql.open();
        String user = event.getUser().getNick();

        ResultSet rs = mysql.query("SELECT * FROM joinedusers WHERE users='" + user + "'");

        if (rs.next()) {
            //nope
        } else {

            mysql.query("INSERT INTO joinedusers (users) VALUES ('" + user + "')");
            mysql.close();
        }
    }

}
