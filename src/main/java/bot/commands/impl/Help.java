package bot.commands.impl;

import bot.Main;
import bot.commands.Commands;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
 * Help command
 * Lists all available commands registered through the Commands enum
 */

public class Help extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("MoonCraft Help");
        for (Commands command : Commands.VALUES) {
            eb.addField(Main.config.prefix + command.getName().toLowerCase(), command.getDescription(), false);
        }
        event.getChannel().sendMessage(eb.build()).queue();
    }

}
