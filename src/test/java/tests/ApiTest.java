package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTest {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    public void testGetPosts() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Status code nu este 200!");
        Assert.assertTrue(response.body().contains("userId"), "Raspunsul nu contine 'userId'!");
        System.out.println("GET posts - OK");
    }

    @Test
    public void testGetSinglePost() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts/1"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Status code nu este 200!");
        Assert.assertTrue(response.body().contains("\"id\": 1"), "Post-ul cu id 1 nu a fost gasit!");
        System.out.println("GET single post - OK");
    }

    @Test
    public void testCreatePost() throws Exception {
        String body = "{\"title\": \"Test Post\", \"body\": \"Continut test\", \"userId\": 1}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 201, "Status code nu este 201!");
        Assert.assertTrue(response.body().contains("Test Post"), "Post-ul nu a fost creat!");
        System.out.println("POST create post - OK");
    }
}