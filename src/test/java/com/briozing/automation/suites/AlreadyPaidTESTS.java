package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.AccountPaymentHistoryHelper;
import com.briozing.automation.helpers.AlreadyPaidHelper;
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

public class AlreadyPaidTESTS {
    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());
    private AlreadyPaidHelper alreadyPaidHelper;
    private TestValidationHelper validationHelper;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeClass(alwaysRun = true)
    public void setup() {
        alreadyPaidHelper = new AlreadyPaidHelper();
        validationHelper = new TestValidationHelper();
    }

    @Test(groups={"liveChatAlreadyPaid","AlreadyPaid"})
    public void verify_unable_to_locate_payments_live_chat_with_an_agent() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_ENTER_ALREADY_PAID.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name(),true);
            validateAlreadyPaid(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing Already Paid test");
        }
    }

    @Test(groups={"requestCallbackAlreadyPaid","AlreadyPaid"})
    public void verify_unable_to_locate_payments_request_a_callback() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_ENTER_ALREADY_PAID.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name(),true);
            validateAlreadyPaid(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing Already Paid test");
        }
    }

    @Test(groups={"displayContactInfoAlreadyPaid","AlreadyPaid"})
    public void verify_unable_to_locate_payments_display_contact_information() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_ENTER_ALREADY_PAID.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name(),true);
            validateAlreadyPaid(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing Already Paid test");
        }
    }

    private void validateAlreadyPaid(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_WELCOME.name()) && testSteps.get(TestSteps.STEP_WELCOME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_WELCOME.name());
            WelcomeRequestVO welcomeRequestVO=new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Welcome Message");
            welcomeRequestVO.setIntentType("WELCOME");
            final WelcomeResponseVO response = alreadyPaidHelper.welcome(welcomeRequestVO, 200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_welcome(response);
        }

        if (null != testSteps.get(TestSteps.STEP_ENTER_ALREADY_PAID.name()) && testSteps.get(TestSteps.STEP_ENTER_ALREADY_PAID.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_ENTER_ALREADY_PAID.name());
            WelcomeRequestVO welcomeRequestVO=new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Already Paid\\n");
            welcomeRequestVO.setIntentType("WELCOME");
            final WelcomeResponseVO response = alreadyPaidHelper.enterText(welcomeRequestVO, 200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_enter_already_paid(response);
        }

        if (null != testSteps.get(TestSteps.STEP_LOGIN.name()) && testSteps.get(TestSteps.STEP_LOGIN.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_LOGIN.name());
            FileInputStream loginJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/Login.json"));
            LoginRequestVO loginRequestVO = mapper.readValue(loginJson, LoginRequestVO.class);
            final DefaultResponse response = alreadyPaidHelper.login(loginRequestVO,200)
                    .getBody().as(DefaultResponse.class);
            validationHelper.verify_login_for_already_paid(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name()) && testSteps.get(TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_LIVE_CHAT_WITH_AN_AGENT.name());
            WelcomeRequestVO welcomeRequestVO=new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Live Chat with an Agent");
            welcomeRequestVO.setIntentType("ALREADY_PAID");
            final DefaultResponse response = alreadyPaidHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultResponse.class);
            validationHelper.verify_contact_customer_service_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name()) && testSteps.get(TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_REQUEST_A_CALLBACK.name());
            WelcomeRequestVO welcomeRequestVO=new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Request a Call Back");
            welcomeRequestVO.setIntentType("ALREADY_PAID");
            final DefaultResponse response = alreadyPaidHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultResponse.class);
            validationHelper.verify_contact_customer_service_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name()) && testSteps.get(TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_DISPLAY_CONTACT_INFORMATION.name());
            WelcomeRequestVO welcomeRequestVO=new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Display Contact Information");
            welcomeRequestVO.setIntentType("ALREADY_PAID");
            final DefaultResponse response = alreadyPaidHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultResponse.class);
            validationHelper.verify_contact_customer_service_button_click(response);
        }
    }
}
