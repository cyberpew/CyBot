package me.cyberpew.CyBot;

import me.cyberpew.data.MySQL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static Properties config = new Properties();
    static String nick;
    static String user;
    static String server;
    static int port;
    static String pass;
    static String serverpass;
    static boolean SSL;
    static int messagedelay;
    static String[] channels;
    public static String[] admins;
    static String mysql_host;
    static String mysql_db;
    static String mysql_user;
    static String mysql_pass;
    static String mysql_port;
    public static MySQL mysql;

    public static void loadConfig() throws FileNotFoundException,
            IOException {
        config.load(new FileInputStream("CyBot.properties"));
        nick = config.getProperty("nick");
        user = config.getProperty("user");
        server = config.getProperty("server");
        port = Integer.parseInt(config.getProperty("port"));
        SSL = Boolean.parseBoolean(config.getProperty("SSL"));
        pass = config.getProperty("pass");
        serverpass = config.getProperty("serverpass");
        channels = config.getProperty("channels").split(",");
        messagedelay = Integer.parseInt(config.getProperty("messagedelay"));
        admins = config.getProperty("admins").split(",");

        mysql_host = config.getProperty("mysql_host");
        mysql_db = config.getProperty("mysql_db");
        mysql_user = config.getProperty("mysql_user");
        mysql_pass = config.getProperty("mysql_pass");
        mysql_port = config.getProperty("mysql_port");

    }
}
