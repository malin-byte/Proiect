package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTest {

    private final String BASE_URL = "https://reqres.in/api";
    private final String API_KEY = "reqres-free-v1";
    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    public void testGetUsers() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users?page=1"))
                .header("x-api-key", API_KEY)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Status code nu este 200!");
        Assert.assertTrue(response.body().contains("data"), "Raspunsul nu contine 'data'!");
        System.out.println("GET users - OK");
    }

    @Test
    public void testCreateUser() throws Exception {
        String body = "{\"name\": \"Maria\", \"job\": \"QA Tester\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .header("Content-Type", "application/json")
                .header("x-api-key", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 201, "Status code nu este 201!");
        Assert.assertTrue(response.body().contains("Maria"), "Userul nu a fost creat!");
        System.out.println("POST create user - OK");
    }

    @Test
    public void testDeleteUser() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/users/2"))
                .header("x-api-key", API_KEY)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 204, "Status code nu este 204!");
        System.out.println("DELETE user - OK");
    }
}