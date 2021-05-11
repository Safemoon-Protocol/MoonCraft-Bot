package bot.commands.impl;

import bot.http.Servers;
import bot.model.Server;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.*;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Online Command
 * Lists all online users
 * Contains optional command arguments to retrieve player count from a specific server
 */
public class Online extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split(" ");
        boolean hasArgs = command.length != 1;
        List<Server> servers = Servers.getServers();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("MoonCraft Online Players");
        eb.setColor(Color.decode("#00A79D"));

        /*
         * If an argument is given in the command,
         * check if the server exists.
         * If the server exists, return the player count.
         * If not, return a list of available server arguments
         */
        if (hasArgs) {
            String serverName = command[1];
            Optional<Server> serverOpt = servers.stream().filter(s -> s.name.equalsIgnoreCase(serverName)).findFirst();
            serverOpt.ifPresent(server -> {
                eb.addField(server.name.substring(0, 1).toUpperCase() + server.name.substring(1), String.valueOf(server.onlineCount), true);
            });
            if (serverOpt.isEmpty()) {
                String serverNames = servers.stream().map(server -> server.name).collect(Collectors.joining(", "));
                eb.setDescription("A server with the name `" + serverName + "` was not found. Please choose from the following: " + serverNames);
                eb.setColor(Color.RED);
            }
        }
        /*
         * Regardless of arguments, always return the total online player count.
         */
        eb.addField("Total Players", String.valueOf(Servers.SERVER_LIST.totalCount), true);
        event.getChannel().sendMessage(eb.build()).queue();
    }
}
