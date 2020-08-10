package com.briozing.automation.helpers;

import com.briozing.automation.common.Configuration;
import com.briozing.automation.models.*;
import com.briozing.automation.utils.TestConstants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class AddInsuranceHelper {

    private RequestSpecification requestSpecification;

    public AddInsuranceHelper() {
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

    public Response addInsuranceButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
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

    public Response autoInsuranceButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response autoInsuranceFormSubmit(AutoInsuranceFormRequestVO autoInsuranceFormRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(autoInsuranceFormRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response hmoButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response insuranceCardUploadNoButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response hmoFormSubmit(HMOFormRequestVO hmoFormRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(hmoFormRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response ppoButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    public Response insuranceCardUploadYesButtonClick(WelcomeRequestVO welcomeRequestVO, int status) {
        final Response response = given(requestSpecification)
                .header("Content-Type", "application/json")
                .header("sessionId",TestConstants.sessionId)
                .body(welcomeRequestVO)
                .post("/aspen-conversation-service/intent/processText");
        response.then().assertThat().statusCode(status);
        return response;
    }

    private Map<String, Object> getFormParamsMap() {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("queryText", "Insurance Form Submitted");
        formParams.put("intentType", "ADD_INSURANCE_FORM_SUBMIT");
        Map<String,Object> submitInsuranceInformation = new HashMap();
        submitInsuranceInformation.put("requestAction","ADD");
        submitInsuranceInformation.put("planType","PPO");
        submitInsuranceInformation.put("planLevel","2");
        submitInsuranceInformation.put("relationToPatient","Brother");
        Map<String,String> relatedAccount = new HashMap();
        relatedAccount.put("clientRefNumber","Test001");
        List<Map<String,String>> relatedAccounts = new ArrayList<>();
        relatedAccounts.add(relatedAccount);
        submitInsuranceInformation.put("relatedAccounts",relatedAccounts);
        formParams.put("submitInsuranceInformation", submitInsuranceInformation);
        return formParams;
    }

    public Response ppoFormSubmit( String filePath, int status) {
        final Response response = given(requestSpecification)
                .header("sessionId",TestConstants.sessionId)
                .multiPart("files", new File(filePath),"application/pdf")
                .multiPart("requestBody",getFormParamsMap())
                .accept("*/*")
                .post("/aspen-conversation-service/intent/processText/form-data");
        response.then().assertThat().statusCode(status);
        return response;
    }
}
