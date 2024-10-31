/*
 * Irene Feng 3/23/2023
 * Interfaces and learning about APIs
 */

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;

interface RestApiWriterInterface {

    default String getResponseFromRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        Builder builder = HttpRequest.newBuilder().uri(getApiEndpoint());
        builder = setHeaders(builder);
        HttpRequest request = builder.build();
        return client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)).body();
    }

    default Builder setHeaders(Builder builder) {
        for (String key : getHeaders().keySet()) {
            builder = builder.header(key, getHeaders().get(key));
        }
        return builder;
    }

    default HashMap<String, String> getHeaders() {
        return new HashMap<String, String>();
    }

    URI getApiEndpoint();

    void write(String filename) throws IOException, InterruptedException;
}