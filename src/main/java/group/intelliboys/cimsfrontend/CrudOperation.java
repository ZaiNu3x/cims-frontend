package group.intelliboys.cimsfrontend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import group.intelliboys.cimsfrontend.models.customer.Credit;
import group.intelliboys.cimsfrontend.models.customer.Customer;
import javafx.collections.ObservableList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class CrudOperation {
    public static boolean insertObjectFromDb(String uri, String authToken, Object object) throws JsonProcessingException {
        HttpClient httpClient = HttpClient.newHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(object);

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(uri))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body().contains("true")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public static List<Customer> fetchObjects(String uri, String authToken) {
        HttpClient httpClient = HttpClient.newHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(uri))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static List<Credit> fetchCredits(String uri, String authToken) {
        HttpClient httpClient = HttpClient.newHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(uri))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean deleteObject(String uri, String authToken, int objectId) {
        HttpClient httpClient = HttpClient.newHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(uri + objectId))
                    .DELETE()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            boolean result = objectMapper.readValue(response.body(), Boolean.class);

            return result;

        } catch (Exception e) {
            System.out.println(e);

            return false;
        }
    }

    public static Object updateObject(String uri, String authToken, Object object) throws JsonProcessingException {
        HttpClient httpClient = HttpClient.newHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(object);

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(uri))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            Object o = objectMapper.readValue(response.body(), new TypeReference<>() {
            });
            System.out.println(o);

            return o;

        } catch (Exception e) {
            return false;
        }
    }

    public static List<Customer> searchObjects(String uri, String authToken, String keyword) {
        HttpClient httpClient = HttpClient.newHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(uri + "/" + keyword))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + authToken)
                    .timeout(Duration.ofSeconds(30))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
