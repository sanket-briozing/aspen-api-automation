package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.WhyBilledHelper;
import com.briozing.automation.models.*;
import com.briozing.automation.utils.AppAssert;
import com.briozing.automation.utils.MainUtils;
import com.briozing.automation.utils.TestSteps;
import com.briozing.automation.utils.TestValidationHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class WhyBilledTESTS {
    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());
    private WhyBilledHelper whyBilledHelper;
    private TestValidationHelper validationHelper;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeClass(alwaysRun = true)
    public void setup() {
        whyBilledHelper = new WhyBilledHelper();
        validationHelper = new TestValidationHelper();
    }

    @Test(groups={"foundYes","whyBilled"})
    public void verify_why_billed_found_yes() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_ENTER_WHY_BILLED.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_YES.name(),true);
            validateWhyBilled(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing Why Billed test");
        }
    }

    @Test(groups={"liveChat","whyBilled"})
    public void verify_why_billed_found_no_live_chat() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_ENTER_WHY_BILLED.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_NO.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name(),true);
            validateWhyBilled(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing Why Billed test");
        }
    }

    @Test(groups={"requestACallback","whyBilled"})
    public void verify_why_billed_found_no_request_a_callback() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_ENTER_WHY_BILLED.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_NO.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name(),true);
            validateWhyBilled(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing Why Billed test");
        }
    }

    @Test(groups={"displayContactInformation","whyBilled"})
    public void verify_why_billed_found_no_display_contact_information() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_ENTER_WHY_BILLED.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_NO.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name(),true);
            validateWhyBilled(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing Why Billed test");
        }
    }

    private void validateWhyBilled(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_WELCOME.name()) && testSteps.get(TestSteps.STEP_WELCOME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_WELCOME.name());
            FileInputStream welcomeJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/Welcome.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(welcomeJson, WelcomeRequestVO.class);
            final WelcomeResponseVO response = whyBilledHelper.welcome(welcomeRequestVO,200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_welcome(response);
        }

        if (null != testSteps.get(TestSteps.STEP_ENTER_WHY_BILLED.name()) && testSteps.get(TestSteps.STEP_ENTER_WHY_BILLED.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_ENTER_WHY_BILLED.name());
            FileInputStream whyBilledJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/whyBilled/WhyBilled.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(whyBilledJson, WelcomeRequestVO.class);
            final WelcomeResponseVO response = whyBilledHelper.enterWhyBilled(welcomeRequestVO,200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_enter_why_billed(response);
        }

        if (null != testSteps.get(TestSteps.STEP_LOGIN.name()) && testSteps.get(TestSteps.STEP_LOGIN.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_LOGIN.name());
            FileInputStream loginJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/Login.json"));
            LoginRequestVO loginRequestVO = mapper.readValue(loginJson, LoginRequestVO.class);
            final WhyIAmBeingBilledResponseVO response = whyBilledHelper.login(loginRequestVO,200)
                    .getBody().as(WhyIAmBeingBilledResponseVO.class);
            validationHelper.verify_login_for_why_billed(response);
        }

        if (null != testSteps.get(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_YES.name()) && testSteps.get(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_YES.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_YES.name());
            FileInputStream yesJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/whyBilled/FoundWhatLookingForYes.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(yesJson, WelcomeRequestVO.class);
            final DefaultWhyIAmBeingBilledResponse response = whyBilledHelper.foundWhatLookingForButtonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultWhyIAmBeingBilledResponse.class);
            validationHelper.verify_found_what_looking_for_yes(response);
        }

        if (null != testSteps.get(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_NO.name()) && testSteps.get(TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_NO.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_FOUND_WHAT_YOU_LOOKING_FOR_NO.name());
            FileInputStream noJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/whyBilled/FoundWhatLookingForNo.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(noJson, WelcomeRequestVO.class);
            final DefaultWhyIAmBeingBilledResponse response = whyBilledHelper.foundWhatLookingForButtonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultWhyIAmBeingBilledResponse.class);
            validationHelper.verify_found_what_looking_for_no(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name()) && testSteps.get(TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name());
            FileInputStream liveChatJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/whyBilled/LiveChatWithAnAgentButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(liveChatJson, WelcomeRequestVO.class);
            final DefaultWhyIAmBeingBilledResponse response = whyBilledHelper.contactCustomerServiceButtonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultWhyIAmBeingBilledResponse.class);
            validationHelper.verify_contact_customer_service_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name()) && testSteps.get(TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name());
            FileInputStream liveChatJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/whyBilled/RequestACallbackButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(liveChatJson, WelcomeRequestVO.class);
            final DefaultWhyIAmBeingBilledResponse response = whyBilledHelper.contactCustomerServiceButtonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultWhyIAmBeingBilledResponse.class);
            validationHelper.verify_contact_customer_service_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name()) && testSteps.get(TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name());
            FileInputStream liveChatJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/whyBilled/DisplayContactInformationButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(liveChatJson, WelcomeRequestVO.class);
            final DefaultWhyIAmBeingBilledResponse response = whyBilledHelper.contactCustomerServiceButtonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultWhyIAmBeingBilledResponse.class);
            validationHelper.verify_contact_customer_service_button_click(response);
        }
    }
}