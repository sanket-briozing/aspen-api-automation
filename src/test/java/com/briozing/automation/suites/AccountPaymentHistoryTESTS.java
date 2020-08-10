package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.AccountPaymentHistoryHelper;
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

public class AccountPaymentHistoryTESTS {
    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());
    private AccountPaymentHistoryHelper accountPaymentHistoryHelper;
    private TestValidationHelper validationHelper;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeClass(alwaysRun = true)
    public void setup() {
        accountPaymentHistoryHelper = new AccountPaymentHistoryHelper();
        validationHelper = new TestValidationHelper();
    }

    @Test(groups={"additionalPaymentsNo","accountPaymentHistory"})
    public void verify_additional_payments_no() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_NO.name(),true);
            validateAccountPaymentHistory(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing account payment history test");
        }
    }

    @Test(groups={"customDateRange","accountPaymentHistory"})
    public void verify_additional_payments_yes_custom_date_range() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_YES.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_CUSTOM_DATE_RANGE.name(),true);
            testSteps.put(TestSteps.STEP_SUBMIT_CUSTOM_DATE_RANGE.name(),true);
            validateAccountPaymentHistory(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing account payment history test");
        }
    }

    @Test(groups={"3MonthsFoundYes","accountPaymentHistory"})
    public void verify_additional_payments_3_months_found_yes() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_YES.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_3_MONTHS.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_YES.name(),true);
            validateAccountPaymentHistory(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing account payment history test");
        }
    }

    @Test(groups={"3MonthsFoundNo","accountPaymentHistory"})
    public void verify_additional_payments_3_months_found_no() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_YES.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_3_MONTHS.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_NO.name(),true);
            validateAccountPaymentHistory(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing account payment history test");
        }
    }

    @Test(groups={"3MonthsChangeDateRange","accountPaymentHistory"})
    public void verify_additional_payments_3_months_change_date_range() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_YES.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_3_MONTHS.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_CHANGE_DATE_RANGE.name(),true);
            validateAccountPaymentHistory(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing account payment history test");
        }
    }

    private void validateAccountPaymentHistory(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_WELCOME.name()) && testSteps.get(TestSteps.STEP_WELCOME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_WELCOME.name());
            FileInputStream welcomeJson = new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/Welcome.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(welcomeJson, WelcomeRequestVO.class);
//            WelcomeRequestVO welcomeRequestVO=new WelcomeRequestVO();
//            welcomeRequestVO.setQueryText("Welcome Message");
//            welcomeRequestVO.setIntentType("WELCOME");
            final WelcomeResponseVO response = accountPaymentHistoryHelper.welcome(welcomeRequestVO, 200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_welcome(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name()) && testSteps.get(TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_ACCOUNT_PAYMENT_HISTORY.name());
            FileInputStream accountPaymentHistoryButtonJson = new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/AccountPaymentHistory/AccountPaymentHistoryButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(accountPaymentHistoryButtonJson, WelcomeRequestVO.class);
            final WelcomeResponseVO response = accountPaymentHistoryHelper.accountPaymentHistoryButtonClick(welcomeRequestVO, 200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_account_payment_history_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_LOGIN.name()) && testSteps.get(TestSteps.STEP_LOGIN.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_LOGIN.name());
            FileInputStream loginJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/Login.json"));
            LoginRequestVO loginRequestVO = mapper.readValue(loginJson, LoginRequestVO.class);
            final AccountPaymentsResponseVO response = accountPaymentHistoryHelper.login(loginRequestVO,200)
                    .getBody().as(AccountPaymentsResponseVO.class);
            validationHelper.verify_login_for_account_payment_history(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_NO.name()) && testSteps.get(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_NO.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_NO.name());
            FileInputStream noJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/AccountPaymentHistory/AdditionalPaymentsNoButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(noJson, WelcomeRequestVO.class);
            final DefaultAccountPaymentsResponse response = accountPaymentHistoryHelper.additionalPaymentsNoButtonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultAccountPaymentsResponse.class);
            validationHelper.verify_additional_payments_no_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_YES.name()) && testSteps.get(TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_YES.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_ADDITIONAL_PAYMENTS_YES.name());
            WelcomeRequestVO welcomeRequestVO = new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Yes");
            welcomeRequestVO.setIntentType("ACCOUNT_PAYMENTS");
            final DefaultAccountPaymentsResponse response = accountPaymentHistoryHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultAccountPaymentsResponse.class);
            validationHelper.verify_additional_payments_yes_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_CUSTOM_DATE_RANGE.name()) && testSteps.get(TestSteps.STEP_CLICK_CUSTOM_DATE_RANGE.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_CUSTOM_DATE_RANGE.name());
            WelcomeRequestVO welcomeRequestVO = new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("custom date range");
            welcomeRequestVO.setIntentType("DEFAULT_ACCOUNT_PAYMENTS");
            final DefaultAccountPaymentsResponse response = accountPaymentHistoryHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultAccountPaymentsResponse.class);
            validationHelper.verify_custom_date_range_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_SUBMIT_CUSTOM_DATE_RANGE.name()) && testSteps.get(TestSteps.STEP_SUBMIT_CUSTOM_DATE_RANGE.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_SUBMIT_CUSTOM_DATE_RANGE.name());
            FileInputStream submitJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/AccountPaymentHistory/SubmitCustomDateRange.json"));
            SubmitCustomDateRangeRequestVO submitCustomDateRangeRequestVO = mapper.readValue(submitJson, SubmitCustomDateRangeRequestVO.class);
            final AccountPaymentsResponseVO response = accountPaymentHistoryHelper.submitCustomDateRange(submitCustomDateRangeRequestVO,200)
                    .getBody().as(AccountPaymentsResponseVO.class);
            validationHelper.verify_custom_date_range_submit(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_3_MONTHS.name()) && testSteps.get(TestSteps.STEP_CLICK_3_MONTHS.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_3_MONTHS.name());
            WelcomeRequestVO welcomeRequestVO = new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("3 months");
            welcomeRequestVO.setIntentType("DEFAULT_ACCOUNT_PAYMENTS");
            final AccountPaymentsResponseVO response = accountPaymentHistoryHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(AccountPaymentsResponseVO.class);
            validationHelper.verify_3_months_button_click(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_YES.name()) && testSteps.get(TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_YES.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_YES.name());
            WelcomeRequestVO welcomeRequestVO = new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Yes");
            welcomeRequestVO.setIntentType("ACCOUNT_PAYMENTS_USER_PROVIDES_DURATION_FOR_ADDITIONAL_PAYMENT");
            final DefaultAccountPaymentsResponse response = accountPaymentHistoryHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultAccountPaymentsResponse.class);
            validationHelper.verify_found_what_looking_for_yes(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_NO.name()) && testSteps.get(TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_NO.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_FOUND_WHAT_YOU_LOOKING_FOR_NO.name());
            WelcomeRequestVO welcomeRequestVO = new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("No");
            welcomeRequestVO.setIntentType("ACCOUNT_PAYMENTS_USER_PROVIDES_DURATION_FOR_ADDITIONAL_PAYMENT");
            final DefaultAccountPaymentsResponse response = accountPaymentHistoryHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultAccountPaymentsResponse.class);
            validationHelper.verify_found_what_looking_for_no(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_CHANGE_DATE_RANGE.name()) && testSteps.get(TestSteps.STEP_CLICK_CHANGE_DATE_RANGE.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_CHANGE_DATE_RANGE.name());
            WelcomeRequestVO welcomeRequestVO = new WelcomeRequestVO();
            welcomeRequestVO.setQueryText("Change Date Range");
            welcomeRequestVO.setIntentType("ACCOUNT_PAYMENTS_USER_PROVIDES_DURATION_FOR_ADDITIONAL_PAYMENT");
            final DefaultAccountPaymentsResponse response = accountPaymentHistoryHelper.buttonClick(welcomeRequestVO,200)
                    .getBody().as(DefaultAccountPaymentsResponse.class);
            validationHelper.verify_change_date_range_button_click(response);
        }
    }
}
