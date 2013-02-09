package me.cyberpew.CyBot;

//Java imports
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Scanner;
import java.util.logging.Logger;

//PircBotX imports
import org.pircbotx.PircBotX;
import org.pircbotx.UtilSSLSocketFactory;

//CyBot imports
import me.cyberpew.Data.*;
import me.cyberpew.CyBot.commands.*;
import me.cyberpew.CyBot.features.*;

public class CyBot {
	
	public static PircBotX bot = new PircBotX();
	public final static Logger logger = Logger.getLogger("CyBot");
	
	static String mysql_host;
    static String mysql_db;
    static String mysql_user;
    static String mysql_pass;
    static String mysql_port;
    public static MySQL mysql;
	
    public static void main(String[] args) throws Exception,
			FileNotFoundException, IOException {
		
		//Load properties
		try {
			
			Config.loadConfig();
			
		} catch (FileNotFoundException ex) {
			//generate file if does not exist 
		}
		
		//Connect Block
		bot.setAutoNickChange(true);
		bot.setVersion("CyBot v2.0, by Cyberpew");
		bot.setLogin(Config.user);
		bot.setName(Config.nick);
		bot.identify(Config.pass);
		bot.setVerbose(false);
		
		//Internal connection start
		if(Config.SSL && !Config.serverpass.isEmpty()) {
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
		
		//#Broken
		//stopCommand();
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
		
		//Load Commands
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
	
//#Broken for now.#//	
//	public static void stopCommand() {
//		@SuppressWarnings("resource")
//		Scanner reader = new Scanner(System.in);
//		String command = reader.nextLine();
//		if (command.equals("stop")) {
//			bot.shutdown();
//			System.exit(1);
//		}
//	}

	//add more stuff later
}