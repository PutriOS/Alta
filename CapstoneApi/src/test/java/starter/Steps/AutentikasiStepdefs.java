package starter.Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.Autentikasi.Login;
import starter.Autentikasi.Logout;
import starter.Autentikasi.Refresh;

import java.io.IOException;

public class AutentikasiStepdefs {
    @Steps
    Login login;

    @Steps
    Logout logout;

    @Steps
    Refresh refresh;

    //Login
    @Given("I set an endpoint for login")
    public void iSetAnEndpointForLogin() {
        login.endpointLogin();
    }

    @When("I request POST for login with {string} and {string}")
    public void iRequestPOSTForLoginWithAnd(String email, String password) {
        login.requestPostLogin(email,password);
    }

    @Then("I validate the status code {int}")
    public void iValidateTheStatusCodeStatus_code(int status_code) {
        login.verifyStatusCode(status_code);
    }

    @And("get tokens for other request")
    public void getTokensForOtherRequest() {
        login.getTokens();
    }

    @And("get refresh tokens for other request")
    public void getRefreshTokensForOtherRequest() {
        login.getRefreshTokens();
    }

    //Logout
    @Given("I set logout endpoint")
    public void iSetLogoutEndpoint() {
        logout.iSetEndpointLogoutUser();
    }

    @When("I request POST for logout")
    public void iRequestPOSTForLogout() throws IOException {
        logout.iSendLogoutEndpoint();
    }

    @Then("I received HTTP response {int}")
    public void iReceivedHTTPResponse(int statusCode) {
        logout.validateResponseCode(statusCode);
    }

    @And("I validate response message")
    public void iValidateResponseMessage() {
        logout.validateResponseMessage();
    }

    @Given("I set refresh token endpoint")
    public void iSetRefreshTokenEndpoint() {
        refresh.iSetEndpointRefreshToken();
    }

    @When("I request POST for refresh token")
    public void iRequestPOSTForRefreshToken() throws IOException {
        refresh.iSendRefreshTokenEndpoint();
    }

    @And("get token baru for other request")
    public void getTokenbaruForOtherRequest() {
        refresh.getTokenBaru();
    }
}
