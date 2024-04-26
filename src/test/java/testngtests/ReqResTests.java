package testngtests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class ReqResTests {

    private static final String BASE_URI = "https://reqres.in/api";
    private static final int USER_ID = 1;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test(priority = 1)
    public void testGetUser() {
        Response response = given()
                .when()
                .get("/users/" + USER_ID);

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.getBody().asString());
        Assert.assertTrue(response.getBody().asString().contains("data"));
        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"));
    }

    @Test(priority = 2)
    public void testCreateUser() {
        String requestBody = "{\"name\": \"John\", \"job\": \"QA Engineer\"}";

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", "John");
        requestParams.put("job", "QA Engineer");

        Response response = given()
                .header("Content-Type", "application/json")
//                .body(requestBody)
                .body(requestParams.toJSONString())
                .when()
                .post("/users");

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.getBody().asString().contains("id"));
        Assert.assertTrue(response.getBody().asString().contains("createdAt"));
        System.out.println(response.getBody().asString());

    }

    @Test(priority = 3)
    public void testUpdateUser() {
        String requestBody = "{\"name\": \"John Doe\", \"job\": \"Senior QA Engineer\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/users/" + USER_ID);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("updatedAt"));
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        Response response = given()
                .when()
                .delete("/users/" + USER_ID);

        Assert.assertEquals(response.getStatusCode(), 204);
        System.out.println(response.getBody().asString());
    }

}
