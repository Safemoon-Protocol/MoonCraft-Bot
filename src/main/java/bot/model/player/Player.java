package bot.model.player;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Player {
    public final String name;
    public final String uuid;
    public final String rank;
    public final LoginData loginData;
    public final BedwarsData bedwarsData;
    public final OgPrisonData ogPrisonData;
    public final OpPrisonData opPrisonData;
    public final StarfruitData starfruitData;

}
