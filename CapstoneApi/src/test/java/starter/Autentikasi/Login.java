package starter.Autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.io.FileWriter;
import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class Login {
    String base_url = "http://ec2-3-91-177-221.compute-1.amazonaws.com/";

    public String endpointLogin(){
        return base_url +"api/login";
    }

    public void requestPostLogin(String email, String password){
        JSONObject requestData = new JSONObject();
        requestData.put("email",email);
        requestData.put("password", password);

        SerenityRest.given().header("Content-Type", "application/json")
                .body(requestData.toJSONString());
        SerenityRest.when().post(endpointLogin());
    }

    public void verifyStatusCode(int status_code){
        Response response = SerenityRest.lastResponse();
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, status_code);
    }

    public void validateErrorMessageLogin(String hasil) {
        if(hasil.equals("password null")){
            then().body("error.password", equalTo("cannot be blank"));
        }else if(hasil.equals("email null")){
            then().body("error.email", equalTo("cannot be blank"));
        }else if(hasil.equals("invalid length password")){
            then().body("error.password", equalTo("the length must be no less than 8"));
        }
    }

    @Step("get token for other request")
    public String getTokens(){
        Response response = SerenityRest.lastResponse();
        String tokens = response.body().path("jwt.access_token");
        System.out.println(tokens);
        try (FileWriter file = new FileWriter("src//test//resources//filejson//tokens.json")) {
            file.write(tokens);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokens;
    }

    @Step("get refresh token for other request")
    public String getRefreshTokens(){
        Response response = SerenityRest.lastResponse();
        String refreshtokens= response.body().path("jwt.refresh_token");
        System.out.println(refreshtokens);
        try (FileWriter file = new FileWriter("src//test//resources//filejson//refreshtokens.json")) {
            file.write(refreshtokens);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return refreshtokens;
    }
}
