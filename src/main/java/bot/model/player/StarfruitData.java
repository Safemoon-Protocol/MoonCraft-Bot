package bot.model.player;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class StarfruitData{
    public Map<String, Integer> stats;
    public List<Collection> collections;

    public int getTotalLevel() {
        return 0;
    }
}