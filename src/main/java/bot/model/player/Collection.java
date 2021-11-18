package bot.model.player;

import lombok.Data;

import java.util.List;

@Data
public class Collection{
    public String name;
    public List<Item> items;
}