package Tests;

import Model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpringRestTemplateTest {
    @Test
    public void checkStatusCode() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        System.out.println("Status Code is " + response.getStatusCode());
        int statusCode = response.getStatusCodeValue();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkResponseHeader() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        HttpHeaders headers = response.getHeaders();
        String responseHeader = headers.getContentType().toString();
        System.out.println("Response header content type is " + responseHeader);
        Assert.assertEquals(responseHeader, "application/json;charset=utf-8");
    }

    @Test
    public void checkResponseBody() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        User[] users = response.getBody();
        System.out.println("The number of users is " + users.length);
        Assert.assertEquals(users.length, 10);
    }
}
