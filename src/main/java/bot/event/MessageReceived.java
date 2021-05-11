package bot.event;

import bot.commands.Commands;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

/*
 * Handles all message received events
 */
public class MessageReceived extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived (GuildMessageReceivedEvent event) {
        /*
         * If the user that sent the message is a bot, or our bot does not have messaging permissions, do nothing.
         */
        if (event.getAuthor().isBot() || !event.getChannel().canTalk()) {
            return;
        }

        /*
         * Check if the message entered if a command.
         */
        Commands commands = Commands.isCommand(event);
        if (Objects.isNull(commands))
            return;


        /*
         * If we cannot post embeds, send an error message.
         */
        if(!event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_EMBED_LINKS)) {
            event.getChannel().sendMessage("Please allow me to post embed links within this channel.").queue();
            return;
        }

        /*
         * Pass the event to the proper command handler.
         */
        commands.getAdapter().onGuildMessageReceived(event);
    }
}
