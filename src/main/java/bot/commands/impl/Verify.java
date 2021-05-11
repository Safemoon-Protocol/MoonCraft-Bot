package bot.commands.impl;

import bot.Main;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import okhttp3.*;
import java.awt.*;
import java.util.UUID;

/*
 * Verification command to link users' discord with minecraft account
 */

public class Verify extends ListenerAdapter {


    @SneakyThrows
    @Override
    public void onGuildMessageReceived (GuildMessageReceivedEvent event) {

        /*
         * Generate unique ID for verification
         */
        String uuid = UUID.randomUUID().toString();
        String token = uuid.substring(0, uuid.indexOf("-"));

        /*
         * Grab the users' discord ID to store in the backend database
         */
        String discordId = event.getAuthor().getId();

        /*
         * Build the post request
         */
        HttpUrl httpUrl = new HttpUrl.Builder()
            .scheme("https")
            .host("api.minestock.org")
            .addPathSegments("post/verify")
            .addQueryParameter("auth", Main.config.auth)
            .addQueryParameter("token", token)
            .addQueryParameter("discordId", discordId)
            .build();

        /*
         * Send the post request
         */
        Request request = new Request.Builder().url(httpUrl).post(RequestBody.create(MediaType.get("application/json; charset=utf-8"), "")).build();

        Call call = Main.HTTP_CLIENT.newCall(request);
        Response response = call.execute();
        System.out.println("Response: " + response.code());

        String message;

        /*
         * If the request is accepted, send the user their verification token.
         * Other wise, report an error.
         */
        if (response.code() == 200) {
            message = "Please run the following command in-game: `/verify " + token + "`";
        } else {
            message = "An error has occured submitting your verification, please notify a staff member.";
        }

        /*
         * Send the message to the user
         */
        event.getAuthor().openPrivateChannel().queue((privateChannel ->
                privateChannel.sendMessage(message).queue()
        ));

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("New Verification Request");
        eb.setColor(Color.decode("#00A79D"));
        eb.setDescription("Please check your private messages and run the command I have sent you in-game @" + event.getAuthor().getAsTag());
        event.getChannel().sendMessage(eb.build()).queue();

    }
}
