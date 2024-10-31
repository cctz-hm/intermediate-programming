/*
 * Irene Feng 3/23/2023
 */

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class MoviePosterWriter implements RestApiWriterInterface {

    String movieString;
    String movieYear;

    MoviePosterWriter() {
    }

    public void setMovieString(String movieString) {
        this.movieString = movieString;
    }

    @Override
    public URI getApiEndpoint() {
        return URI.create("https://movie-database-alternative.p.rapidapi.com/?s=" + movieString + "&r=json&page=1");
    }

    @Override
    public void write(String filename) throws IOException, InterruptedException {
        String response = getResponseFromRequest();
        JSONObject json = new JSONObject(response);
        try {
            JSONArray searchResults = json.getJSONArray("Search");

            if (searchResults.length() > 0) {
                JSONObject firstResult = searchResults.getJSONObject(0);
                String posterUrl = firstResult.getString("Poster");
                // download poster from poster url
                try (BufferedInputStream in = new BufferedInputStream(new URI(posterUrl).toURL().openStream());
                        FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
                    byte dataBuffer[] = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                    }
                } catch (IOException | URISyntaxException e) {
                    // handle exception
                }

                movieYear = firstResult.getString("Year");


            }

        } catch (JSONException e) {
            System.out.println("Sorry, there was a JSONException in the response.");
            System.out.println("We look for the poster in {'Search': [{'Poster'... }]}, json object. We got " + json);
        }
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return new HashMap<String, String>() {
            {
                put("X-RapidAPI-Key", "ae71572c1amsh4a3a00092a6e868p17b04ajsn7318499fbbcd");
                put("X-RapidAPI-Host", "movie-database-alternative.p.rapidapi.com");
            }
        };
    }

}
