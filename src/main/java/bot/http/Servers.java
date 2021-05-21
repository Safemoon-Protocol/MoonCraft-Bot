package bot.http;

import bot.Main;
import bot.model.Server;
import bot.model.ServerList;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
 * Handles requests to obtain server info
 */
public class Servers {

    private static final String baseUrl = "https://mcapi.safemoon.net";
    public static ServerList SERVER_LIST;

    @SneakyThrows(IOException.class)
    public static List<Server> getServers() {

        Request request = new Request.Builder()
                .url(baseUrl + "/servers")
                .build();
        Response response = Main.HTTP_CLIENT.newCall(request).execute();

        ResponseBody body = response.body();
        if (body != null) {
            SERVER_LIST = Main.GSON.fromJson(body.string(), ServerList.class);
            return SERVER_LIST.servers.stream().collect(Collectors.toList());
        } else
            return new ArrayList<>();
    }
}