package cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static org.junit.Assert.*;

public class Products {
    private int ResponseCode;
    private RequestSpecification httpRequest;
    private Response response;
    private JSONObject requestParams;

    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
         httpRequest = RestAssured.given();
        response = httpRequest.get("/products");
    }
    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        ResponseCode = response.getStatusCode();
        assertEquals(200, ResponseCode);
    }


    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String rate) {
//    body = response.getBody();
//    String responseBody = body.asString();
    JsonPath jsonPath = response.jsonPath();
    String s = jsonPath.getJsonObject("rating[0].rate").toString();

        assertEquals(rate, s);
    }


    @Given("I hit the url of post product api endpoint")
    public void i_hit_the_url_of_post_product_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();


    }
    @When("I pass the request body of product title {}")
    public void i_pass_the_request_body_of_product_title(String title) {
        requestParams.put("title", title);
        requestParams.put("price", 3.3);
        requestParams.put("description", "anything");
        requestParams.put("image", "https://thisisanexample.cc");
        requestParams.put("category", "electronic");

        httpRequest.body(requestParams.toJSONString());
         response = httpRequest.post("/products");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }


    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAsId(String id) {
        JsonPath jsonpath = response.jsonPath();
        String s = jsonpath.getJsonObject("id").toString();
        assertEquals(id,s);
    }
}
