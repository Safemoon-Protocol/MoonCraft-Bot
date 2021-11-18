package bot;

import bot.model.Server;
import bot.model.ServerList;
import bot.model.player.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/*
 * Handles requests to obtain server info
 */
public class Http {

    private static final String baseUrl = "https://mcapi.safemoon.net";
    public static ServerList SERVER_LIST;

    @SneakyThrows(IOException.class)
    public List<Server> getServers() {

        Request request = new Request.Builder()
                .url(baseUrl + "/servers")
                .build();
        Response response = Main.HTTP_CLIENT.newCall(request).execute();

        ResponseBody body = response.body();
        if (body != null) {
            SERVER_LIST = Main.GSON.fromJson(body.string(), ServerList.class);
            return new ArrayList<>(SERVER_LIST.servers);
        } else
            return new ArrayList<>();
    }

    @SneakyThrows(IOException.class)
    public Player getPlayer(String name) {
        Request request = new Request.Builder()
                .url(baseUrl + "/player/" + name)
                .build();
        Response response = Main.HTTP_CLIENT.newCall(request).execute();

        if(response.code() == 200) {
            Gson gb = new GsonBuilder().setPrettyPrinting().create();
            return gb.fromJson(response.body().string(), Player.class);
        }

        return null;
    }
}