package bot;

import bot.event.MessageReceived;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import okhttp3.OkHttpClient;
import javax.security.auth.login.LoginException;
import java.io.*;

/*
 * MoonCraft Bot's main class.
 */

public class Main {

    public static final Gson GSON = new Gson();
    public static Config config;

    public static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static void main(String[] args) throws LoginException {

        // Load the configuration file and it's properties.
        loadConfig();

        // Build the JDA object to connect to Discord's API
        JDABuilder builder = JDABuilder.createDefault(config.token);

        // Set the bot's display activity
        builder.setActivity(Activity.playing(config.presence));

        // Register the listener for receiving commands
        builder.addEventListeners(new MessageReceived());

        // Finally, build and start the bot.
        builder.build();
    }

    /*
     * Loads config from resources/config.json
     */
    public static void loadConfig() {
        JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/bot/config.json"))));
        config = GSON.fromJson(reader, Config.class);
    }

}