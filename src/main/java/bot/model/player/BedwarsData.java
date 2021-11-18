package bot.model.player;

import lombok.Data;

@Data
public class BedwarsData{
    public int kills;
    public int deaths;
    public int wins;
    public int losses;
    public int finalKills;
    public int bedsBroken;
    public int ironLooted;
    public int goldLooted;
    public int diamondsLooted;
    public int emeraldsLooted;
    public int bedbugsThrown;
    public int bridgeEggsThrown;
    public int towersUsed;
    public int dreamDefendersUsed;
}