package starter.Autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;

public class Logout {
    String baseUrl = "http://ec2-3-91-177-221.compute-1.amazonaws.com/";
/*    public static String tokens = "";*/

    @Step("I set endpoint for logout user")
    public String iSetEndpointLogoutUser(){
        return baseUrl+"api/logout";
    }

    @Step("I send logout endpoint")
    public void iSendLogoutEndpoint() throws IOException {
        String tokens = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given()
                .header("Authorization", "Bearer " + tokens)
                .when().post(iSetEndpointLogoutUser());
    }

    @Step("i received HTTP response {int}")
    public void validateResponseCode(int statusCode){
        Response response = SerenityRest.lastResponse();
        int actual = response.statusCode();
        Assert.assertEquals(statusCode, actual);
    }

    @Step("i validate response message")
    public void validateResponseMessage(){
        SerenityRest.then().body("message", equalTo("User Logged Out"));
    }
}
