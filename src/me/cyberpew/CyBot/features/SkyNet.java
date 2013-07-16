package me.cyberpew.CyBot.features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class SkyNet extends ListenerAdapter {

    public static boolean skynetEnabled;

    public void onMessage(MessageEvent event) throws Exception {
        String message = event.getMessage();

        if (skynetEnabled) {
            event.respond(process(message));
        }

    }

    private String process(String intake) throws NoSuchAlgorithmException, MalformedURLException, IOException {
        Map<String, String> postData = new HashMap<String, String>();
        postData.put("stimulus", intake);
        postData.put("start", "y");
        postData.put("sub", "Say");
        postData.put("icognoid", "wsf");
        postData.put("fno", "0");
        postData.put("islearning", "1");
        postData.put("cleanslate", "false");
        postData.put("sub", "Say");
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : postData.entrySet()) {
            builder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            builder.append('=');
            builder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            builder.append('&');
        }
        builder.setLength(builder.length() - 1);
        String post = builder.toString();

        // Create hash based on md5 of subString 9,29
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(post.substring(9, 29).getBytes("UTF-8"));
        builder.append('&');
        builder.append(URLEncoder.encode("icognocheck", "UTF-8"));
        builder.append('=');
        builder.append(URLEncoder.encode(String.format("%1$032X", new Object[] { new BigInteger(1, md5.digest()) }), "UTF-8"));

        URLConnection urlConnection = new URL("http://cleverbot.com/webservicemin").openConnection();
        urlConnection.setDoOutput(true);
        OutputStreamWriter output = new OutputStreamWriter(urlConnection.getOutputStream());
        output.write(builder.toString());
        output.flush();

        BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringWriter stringWriter = new StringWriter();
        char[] buffer = new char[1024];
        int charsRead = 0;
        while ((charsRead = input.read(buffer)) != -1) {
            stringWriter.write(buffer, 0, charsRead);
        }
        input.close();
        output.close();
        String data = stringWriter.toString();

        String[] split = data.split("\r");
        return split.length > 16 ? split[16] : "";
    }

}
