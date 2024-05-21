package poa.util;

import lombok.SneakyThrows;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FetchSkin1206 {
    private static final Map<UUID, HttpResponse<String>> skinMap = new HashMap<>();

    @SneakyThrows
    public static String fetchSkinURL(UUID uuid) {
        if (!skinMap.containsKey(uuid)) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            skinMap.put(uuid, response);

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


            Runnable task = () -> skinMap.remove(uuid);


            scheduler.schedule(task, 5, TimeUnit.MINUTES);

            scheduler.schedule(scheduler::shutdown, 6, TimeUnit.MINUTES);

        }
        HttpResponse<String> response = skinMap.get(uuid);
        if (response.statusCode() == 200) {
            JSONObject jsonObject = new JSONObject(response.body());
            return jsonObject.getJSONArray("properties").getJSONObject(0).getString("value");
        }
        return null;
    }

    @SneakyThrows
    public static String fetchSkinSignature(UUID uuid) {
        HttpResponse<String> response = skinMap.get(uuid);
        if (response.statusCode() == 200) {
            JSONObject jsonObject = new JSONObject(response.body());
            String tr = jsonObject.getJSONArray("properties").getJSONObject(0).getString("signature");
            return tr;
        }
        return null;
    }
}
