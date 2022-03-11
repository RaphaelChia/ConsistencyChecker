package RequestWrapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CandleHTTPResponse;
import entities.CryptoComHTTPResponse;
import entities.TradeHTTPResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PrebuiltRequests {
    public static ObjectMapper mapper = new ObjectMapper();

    private static HttpResponse<String> doRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static CryptoComHTTPResponse submitRequest(String url) throws IOException, InterruptedException {
        return mapper.readValue(doRequest(url).body(), new TypeReference<>(){});
    }




}
