package testngtests;
//
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class FakeStoreAPItests {

    private int ResponseCode;
    private RequestSpecification httpRequest;
    private Response response;
    private ResponseBody body;
    private String rate = "3.9";

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();
        response = httpRequest.get("/products");

    }

    @Test
    public void testGetProductsEndpointReturns200() {
        ResponseCode = response.getStatusCode();
        Assert.assertEquals(200, ResponseCode);
    }

    @Test
    public void testGetProductsFirstRate() {
        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.getJsonObject("rating[0].rate").toString();
        Assert.assertEquals(rate, s);
    }

    @Test
    public void testJSONSchema() {
        ResponseBody body = response.getBody();
        String responseBody = body.asString();
        Assert.assertTrue(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json").matches(responseBody));
    }

    @Test
    public void testJsonSchemaDifferentApproach() {
        String baseUri = "https://fakestoreapi.com/";
        Response response = given().baseUri(baseUri)
                .when().get("/products")
                .then().assertThat().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"))
                .extract().response();
    }

}
