package bot.commands.impl;

import bot.Http;
import bot.model.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;
import java.util.Map;

public class Profile extends ListenerAdapter {

    Http http = new Http();

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split(" ");
        boolean hasArgs = command.length != 1;
        EmbedBuilder eb = new EmbedBuilder();

        if(!hasArgs) {
            eb.setColor(Color.RED);
            eb.setTitle("Please use the proper command format");
            eb.setDescription("`!!profile <player>`");
            event.getChannel().sendMessageEmbeds(eb.build()).queue();
        } else {
            String playerName = command[1].substring(0, 1).toUpperCase() + command[1].substring(1);

            eb.setTitle(playerName + "'s Profile");
            eb.setColor(Color.decode("#00A79D"));

            Player player = http.getPlayer(playerName);

            if (player == null) {
                eb.setColor(Color.RED);
                eb.setDescription("We searched far and wide, but this player could not be found within MoonCraft's orbit!");
                event.getChannel().sendMessageEmbeds(eb.build()).queue();
                return;
            }

            int sfLevel = 0;
            for (Map.Entry<String, Integer> entry : player.starfruitData.stats.entrySet()) {
                sfLevel += entry.getValue();
            }

            eb.setThumbnail("https://crafatar.com/renders/head/" + player.uuid + "?default=MHF_Steve&overlay");
            eb.addField("Login Info",
                    "Total Logins: " + player.loginData.totalJoins + "\n" +
                            "First Join: " + player.loginData.firstJoin + "\n" +
                            "Last Join: " + player.loginData.lastJoin + "\n",
                    false);
            eb.addField("Alkatraz",
                    "Blocks Broken: " + player.ogPrisonData.blocksBroken + "\n" +
                            "Current Mine: " + player.ogPrisonData.currentMine + "\n" +
                            "Current Presige: " + player.ogPrisonData.prestige + "\n",
                    true);
            eb.addField("Azkaban",
                    "Blocks Broken: " + player.opPrisonData.blocksBroken + "\n" +
                            "Current Mine: " + player.opPrisonData.currentMine + "\n" +
                            "Current Presige: " + player.opPrisonData.prestige + "\n",
                    true);
            eb.addField("", "", false);
            eb.addField("Bedwars",
                    "Wins: " + player.bedwarsData.wins + "\n" +
                            "Losses: " + player.bedwarsData.losses + "\n",
                    true);
            eb.addField("Starfruit",
                    "Total Level: " + sfLevel + "\n" +
                            "Collections: 0" + /*player.starfruitData.collections +*/ "\n",
                    true);


            event.getChannel().sendMessageEmbeds(eb.build()).setActionRow(
                    Button.link("https://profiles.mooncraft.gg/profile/" + playerName, "View Full Profile")
            ).queue();
        }

    }

}
