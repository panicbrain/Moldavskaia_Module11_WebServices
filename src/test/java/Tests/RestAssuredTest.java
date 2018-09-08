package Tests;

import Model.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssuredTest {
    @BeforeTest
    public void beforeTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        Response response = RestAssured.when().get("/users").andReturn();
        System.out.println("Status code is " + response.getStatusLine());
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test
    public void checkResponseHeader() {
        Response response = RestAssured.when().get("/users").andReturn();
        String responseHeaderContentType = response.getContentType();
        System.out.println("Content Type is " + responseHeaderContentType);
        Assert.assertNotNull(responseHeaderContentType);
        Assert.assertEquals(responseHeaderContentType, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBody() {
        Response response = RestAssured.when().get("/users").andReturn();
        ResponseBody<?> responseBody = response.getBody();
        User[] users = responseBody.as(User[].class);
        System.out.println("The number of users is " + users.length);
        Assert.assertEquals(users.length, 10);
    }
}
