package starter.Autentikasi;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.equalTo;

public class Refresh {
    String baseUrl = "http://ec2-3-91-177-221.compute-1.amazonaws.com/";
    private String refreshtokens, tokens;

/*    @Step("I set endpoint for refresh token user")
    public String iSetEndpointRefreshToken(String tokens){
        return baseUrl + "api/refresh_token/{tokens}";return baseUrl + "api/refresh_token/{tokens}";
        return baseUrl + "/api/refresh_token?token=tokens}";
    }*/

    @Step("I set endpoint for refresh token user")
    public String iSetEndpointRefreshToken(){
        return baseUrl+"/api/refresh_token/" + tokens;
    }

    @Step("I send refresh token endpoint")
    public void iSendRefreshTokenEndpoint() throws IOException {
        this.tokens = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//tokens.json"), StandardCharsets.UTF_8);
        refreshtokens = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//filejson//refreshtokens.json"), StandardCharsets.UTF_8);
        SerenityRest.given()
                .header("Authorization", "Bearer " + refreshtokens)
                .when()
                .post(iSetEndpointRefreshToken());
    }

    @Step("get token baru for other request")
    public String getTokenBaru(){
        Response response = SerenityRest.lastResponse();
        String token_baru = response.body().path("New Token Generated");
        System.out.println(token_baru);
        try (FileWriter file = new FileWriter("src//test//resources//filejson//tokenbaru.json")) {
            file.write(token_baru);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token_baru;
    }
}

