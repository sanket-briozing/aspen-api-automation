package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import com.briozing.automation.models.LoginRequestVO;
import com.briozing.automation.models.SubmitCustomDateRangeRequestVO;
import com.briozing.automation.models.WelcomeRequestVO;
import com.briozing.automation.utils.TestConstants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class AccountPaymentHistoryHelper {
    private RequestSpecification requestSpecification;

    public AccountPaymentHistoryHelper() {
        requestSpecification = with()
                .log().all()
                .baseUri(Configuration.apiServer);
        System.out.println("RequestSpecification object Created");
    }

    public Response welcome(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.prettyPrint();
        response.then().assertThat().statusCode(status);
        TestConstants.sessionId=response.jsonPath().get("sessionId");
        return response;
    }

    public Response accountPaymentHistoryButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response login(LoginRequestVO loginRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(loginRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response additionalPaymentsNoButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response buttonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response submitCustomDateRange(SubmitCustomDateRangeRequestVO submitCustomDateRangeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(submitCustomDateRangeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }
}
