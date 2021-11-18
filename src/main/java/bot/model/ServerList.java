package bot.model;

import lombok.Data;

import java.util.List;

/*
 * Model for ServerList
 */
@Data
public class ServerList {

    public int totalOnline;
    public List<Server> servers;
}
