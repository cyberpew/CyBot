// Copyright 2013 Andrew Calvert

package me.cyberpew.CyBot;
//CyBot imports
import me.cyberpew.CyBot.commands.*;
import me.cyberpew.CyBot.console.Console;
import me.cyberpew.CyBot.features.*;
import me.cyberpew.data.MySQL;

import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class CyBot {

    public static PircBotX bot = new PircBotX();
    public final static Logger logger = Logger.getLogger("CyBot");

    public static String mysql_host;
    public static String mysql_db;
    public static String mysql_user;
    public static String mysql_pass;
    public static String mysql_port;
    public static MySQL mysql;

    public static void main(String[] args) throws Exception, FileNotFoundException, IOException {
        //Load properties
        try {
            Config.loadConfig();
        } catch (FileNotFoundException ex) {
            Config.config.setProperty("nick", "CyBot");
            Config.config.setProperty("user", "CyBot");
            Config.config.setProperty("server", "irc.xir.me");
            Config.config.setProperty("port", "6667");
            Config.config.setProperty("SSL", "false");
            Config.config.setProperty("pass", "");
            Config.config.setProperty("serverpass", "");
            Config.config.setProperty("channels", "#cyberspace");
            Config.config.setProperty("messagedelay", "1000");
            Config.config.setProperty("admins", "Cyberpew");

            Config.config.setProperty("mysql_host", "");
            Config.config.setProperty("mysql_db", "");
            Config.config.setProperty("mysql_user", "");
            Config.config.setProperty("mysql_pass", "");
            Config.config.setProperty("mysql_port", "3306");

            Config.config.store(new FileOutputStream("CyBot.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Connect Block
        bot.setAutoNickChange(true);
        bot.setVersion("CyBot v2.0, by Cyberpew");
        bot.setLogin(Config.user);
        bot.setName(Config.nick);
        bot.identify(Config.pass);
        bot.setVerbose(false);

        //Internal connection start
        if (Config.SSL && !Config.serverpass.isEmpty()) {
            bot.connect(Config.server, Config.port, Config.serverpass, new UtilSSLSocketFactory().trustAllCertificates());
        } else if (Config.SSL && Config.serverpass.isEmpty()) {
            bot.connect(Config.server, Config.port, new UtilSSLSocketFactory().trustAllCertificates());
        } else {
            bot.connect(Config.server, Config.port);
        }

        bot.setMessageDelay(Config.messagedelay);
        bot.setAutoReconnect(true);
        bot.setAutoReconnectChannels(true);

        joinChannels();


        setupDatabase();


        loadListeners();


        stopCommand();
    }

    public static void joinChannels() {

        for (int i = 0; i < Config.channels.length; i++) {
            bot.joinChannel(Config.channels[i]);
        }
    }

    public static void loadListeners() throws Exception {

        //#load features & commands in 'bot.getListenerManager().addListener(new FEATUREORCOMMAND_NAME());' format.
        //
        //Load Features
        bot.getListenerManager().addListener(new Welcome());
        bot.getListenerManager().addListener(new SeenMgr());
        bot.getListenerManager().addListener(new RecieveCookies());
        bot.getListenerManager().addListener(new SkyNet());
        bot.getListenerManager().addListener(new NetCommands());
        bot.getListenerManager().addListener(new InviteBot());

        //Load Commands
        bot.getListenerManager().addListener(new Help());
        bot.getListenerManager().addListener(new GTFTS());
        bot.getListenerManager().addListener(new Seen());
        bot.getListenerManager().addListener(new Google());
        bot.getListenerManager().addListener(new Join());
        bot.getListenerManager().addListener(new Part());
        bot.getListenerManager().addListener(new ListOps());
        bot.getListenerManager().addListener(new ListHalfOps());
        bot.getListenerManager().addListener(new ListVoiced());
        bot.getListenerManager().addListener(new MinecraftStatus());
        bot.getListenerManager().addListener(new MinecraftPaid());
        bot.getListenerManager().addListener(new Stack());
        bot.getListenerManager().addListener(new Kill());
        bot.getListenerManager().addListener(new Info());
        bot.getListenerManager().addListener(new Nick());
        bot.getListenerManager().addListener(new SkyNetToggle());
        bot.getListenerManager().addListener(new Fapget());
        bot.getListenerManager().addListener(new Rehash());
        bot.getListenerManager().addListener(new UrbanDictionary());

        //Load handlers
        bot.getListenerManager().addListener(new Console());
    }

    private static void setupDatabase() {
        mysql_host = Config.mysql_host;
        mysql_db = Config.mysql_db;
        mysql_user = Config.mysql_user;
        mysql_pass = Config.mysql_pass;
        mysql_port = Config.mysql_port;

        mysql = new MySQL(logger, "[CyBot]", mysql_host, mysql_port, mysql_db, mysql_user, mysql_pass);

        System.out.println("Trying to connect to database...");
        mysql.open();

        if (mysql.checkConnection()) {
            System.out.println("Successfully connected to database!");

            if (!mysql.checkTable("seenusers")) {
                System.out.println("Creating table 'seenusers' in database " + mysql_db);
                mysql.createTable("CREATE TABLE seenusers (id int NOT NULL AUTO_INCREMENT, user VARCHAR(32) NOT NULL, lastseen VARCHAR(32) NOT NULL, PRIMARY KEY (id) ) ENGINE=MyISAM;");
            }
            if (!mysql.checkTable("joinedusers")) {
                System.out.println("Creating table 'joinedusers' in database " + mysql_db);
                mysql.createTable("CREATE TABLE joinedusers (id int NOT NULL AUTO_INCREMENT, users VARCHAR(32) NOT NULL, PRIMARY KEY (id) ) ENGINE=MyISAM;");
            }
        } else {
            System.out.println("Error connecting to database.");
        }
        mysql.close();
    }

    @SuppressWarnings("resource")
    public static void stopCommand() {
        Scanner reader = new Scanner(System.in);
        String command = reader.nextLine();
        if (command.equals("stop")) {
            System.exit(0);
        }
    }

    //add more stuff later
}