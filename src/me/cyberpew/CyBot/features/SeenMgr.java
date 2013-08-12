package me.cyberpew.CyBot.features;

import me.cyberpew.CyBot.CyBot;
import me.cyberpew.data.MySQL;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PartEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("rawtypes")
public class SeenMgr extends ListenerAdapter {

    public void onJoin(JoinEvent event) throws SQLException {
        MySQL mysql = new MySQL(CyBot.logger, "[CyBot]", CyBot.mysql_host, CyBot.mysql_port, CyBot.mysql_db, CyBot.mysql_user, CyBot.mysql_pass);

        String user = event.getUser().getNick();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String lastseen = dateFormat.format(date);

        mysql.open();

        ResultSet rs = mysql.query("SELECT * FROM seenusers WHERE user='" + user + "'");

        if (rs.next()) {
            mysql.query("UPDATE seenusers SET lastseen = '" + lastseen + "' WHERE user='" + user + "'");
        } else {
            mysql.query("INSERT INTO seenusers (user, lastseen) VALUES ('" + user + "', '" + lastseen + "') ");
        }
        mysql.close();
    }

    public void onPart(PartEvent event) {
        MySQL mysql = new MySQL(CyBot.logger, "[CyBot]", CyBot.mysql_host, CyBot.mysql_port, CyBot.mysql_db, CyBot.mysql_user, CyBot.mysql_pass);

        String user = event.getUser().getNick();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String lastseen = dateFormat.format(date);

        mysql.open();
        mysql.query("UPDATE seenusers SET lastseen = '" + lastseen + "' WHERE user='" + user + "'");
        mysql.close();
    }

    public void onMessage(MessageEvent event) {
        MySQL mysql = new MySQL(CyBot.logger, "[CyBot]", CyBot.mysql_host, CyBot.mysql_port, CyBot.mysql_db, CyBot.mysql_user, CyBot.mysql_pass);

        mysql.open();
        String user = event.getUser().getNick();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String lastseen = dateFormat.format(date);
        try {
            mysql.query("UPDATE seenusers SET lastseen = '" + lastseen + "' WHERE user='" + user + "'");
        } catch (Exception e) {

        }
    }

}
