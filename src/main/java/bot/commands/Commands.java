package bot.commands;

import bot.Main;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import bot.commands.impl.*;

import javax.annotation.Nullable;

/*
 * Registers available commands for use in Discord.
 */
@RequiredArgsConstructor @Getter
public enum Commands {

    HELP("help", "Command for help", new Help()),
    VERIFY("verify", "Allows users to link minecraft accounts", new Verify()),
    ONLINE("online", "Checks player count of online servers", new Online());

    public static final Commands[] VALUES = values();

    private final String name;
    private final String description;
    private final ListenerAdapter adapter;

    /*
     * Verifies that incoming messages contain commands.
     */
    @Nullable
    public static Commands isCommand(GuildMessageReceivedEvent event) {
        String text = event.getMessage().getContentRaw().toLowerCase();
        for (Commands command : VALUES) {
            if (text.startsWith(Main.config.prefix + command.getName())) {
                return command;
            }
        }
        return null;
    }

}
