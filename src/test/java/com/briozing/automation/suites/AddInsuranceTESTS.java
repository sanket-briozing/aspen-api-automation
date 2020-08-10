package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.helpers.AddInsuranceHelper;
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

public class AddInsuranceTESTS {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());
    private AddInsuranceHelper addInsuranceHelper;
    private TestValidationHelper validationHelper;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeClass(alwaysRun = true)
    public void setup() {
        addInsuranceHelper = new AddInsuranceHelper();
        validationHelper = new TestValidationHelper();
    }

    @Test(groups={"autoInsurance","addInsurance"})
    public void verify_auto_Insurance() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ADD_INSURANCE_BUTTON.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_AUTO_INSURANCE.name(),true);
            testSteps.put(TestSteps.STEP_SUBMIT_AUTO_INSURANCE_FORM.name(),true);
            validateAddInsurance(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing auto insurance test");
        }
    }

    @Test(groups={"hmoWithNoUpload","addInsurance"})
    public void verify_hmo_with_no_upload() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ADD_INSURANCE_BUTTON.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_HMO.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_NO_BUTTON.name(),true);
            testSteps.put(TestSteps.STEP_SUBMIT_HMO_FORM.name(),true);
            validateAddInsurance(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing HMO with no upload test");
        }
    }

    @Test(groups={"ppoWithUpload","addInsurance"})
    public void verify_ppo_with_upload() {
        try {
            logger.info("-------------Test Started ------------");
            final Map<String, Boolean> testSteps = new LinkedHashMap<>();
            testSteps.put(TestSteps.STEP_WELCOME.name(), true);
            testSteps.put(TestSteps.STEP_CLICK_ADD_INSURANCE_BUTTON.name(),true);
            testSteps.put(TestSteps.STEP_LOGIN.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_PPO.name(),true);
            testSteps.put(TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_YES_BUTTON.name(),true);
            testSteps.put(TestSteps.STEP_SUBMIT_PPO_FORM.name(),true);
            validateAddInsurance(testSteps);
            logger.info("--------------Test Ended -------------");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex);
            AppAssert.assertTrue(false, "Failure executing PPO with upload test");
        }
    }

    private void validateAddInsurance(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.STEP_WELCOME.name()) && testSteps.get(TestSteps.STEP_WELCOME.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_WELCOME.name());
            FileInputStream welcomeJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/Welcome.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(welcomeJson, WelcomeRequestVO.class);
            final WelcomeResponseVO response = addInsuranceHelper.welcome(welcomeRequestVO,200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_welcome(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_ADD_INSURANCE_BUTTON.name()) && testSteps.get(TestSteps.STEP_CLICK_ADD_INSURANCE_BUTTON.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_ADD_INSURANCE_BUTTON.name());
            FileInputStream addInsuranceButtonJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/AddInsuranceButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(addInsuranceButtonJson, WelcomeRequestVO.class);
            final WelcomeResponseVO response = addInsuranceHelper.addInsuranceButtonClick(welcomeRequestVO,200)
                    .getBody().as(WelcomeResponseVO.class);
            validationHelper.verify_add_insurance_button(response);
        }

        if (null != testSteps.get(TestSteps.STEP_LOGIN.name()) && testSteps.get(TestSteps.STEP_LOGIN.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_LOGIN.name());
            FileInputStream loginJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/Login.json"));
            LoginRequestVO loginRequestVO = mapper.readValue(loginJson, LoginRequestVO.class);
            final DefaultAddInsuranceResponse response = addInsuranceHelper.login(loginRequestVO,200)
                    .getBody().as(DefaultAddInsuranceResponse.class);
            validationHelper.verify_login_for_add_insurance(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_AUTO_INSURANCE.name()) && testSteps.get(TestSteps.STEP_CLICK_AUTO_INSURANCE.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_AUTO_INSURANCE.name());
            FileInputStream autoInsuranceButtonJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/AutoInsuranceButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(autoInsuranceButtonJson, WelcomeRequestVO.class);
            final AddInsuranceFormResponseVO response = addInsuranceHelper.autoInsuranceButtonClick(welcomeRequestVO,200)
                    .getBody().as(AddInsuranceFormResponseVO.class);
            validationHelper.verify_auto_insurance_button(response);
        }

        if (null != testSteps.get(TestSteps.STEP_SUBMIT_AUTO_INSURANCE_FORM.name()) && testSteps.get(TestSteps.STEP_SUBMIT_AUTO_INSURANCE_FORM.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_SUBMIT_AUTO_INSURANCE_FORM.name());
            FileInputStream autoInsuranceFormJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/AutoInsuranceFormSubmit.json"));
            AutoInsuranceFormRequestVO autoInsuranceFormRequestVO = mapper.readValue(autoInsuranceFormJson, AutoInsuranceFormRequestVO.class);
            final DefaultAddInsuranceResponse response = addInsuranceHelper.autoInsuranceFormSubmit(autoInsuranceFormRequestVO,200)
                    .getBody().as(DefaultAddInsuranceResponse.class);
            validationHelper.verify_insurance_form_submit(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_HMO.name()) && testSteps.get(TestSteps.STEP_CLICK_HMO.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_HMO.name());
            FileInputStream hmoButtonJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/HMOButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(hmoButtonJson, WelcomeRequestVO.class);
            final AddInsuranceExtractPlanTypeResponseVO response = addInsuranceHelper.hmoButtonClick(welcomeRequestVO,200)
                    .getBody().as(AddInsuranceExtractPlanTypeResponseVO.class);
            validationHelper.verify_hmo_button(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_NO_BUTTON.name()) && testSteps.get(TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_NO_BUTTON.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_NO_BUTTON.name());
            FileInputStream noButtonJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/InsuranceCardUploadNoButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(noButtonJson, WelcomeRequestVO.class);
            final AddInsuranceFormResponseVO response = addInsuranceHelper.insuranceCardUploadNoButtonClick(welcomeRequestVO,200)
                    .getBody().as(AddInsuranceFormResponseVO.class);
            validationHelper.verify_insurance_card_upload_no_button(response);
        }

        if (null != testSteps.get(TestSteps.STEP_SUBMIT_HMO_FORM.name()) && testSteps.get(TestSteps.STEP_SUBMIT_HMO_FORM.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_SUBMIT_HMO_FORM.name());
            FileInputStream hmoFormJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/HMOFormSubmit.json"));
            HMOFormRequestVO hmoFormRequestVO = mapper.readValue(hmoFormJson, HMOFormRequestVO.class);
            final DefaultAddInsuranceResponse response = addInsuranceHelper.hmoFormSubmit(hmoFormRequestVO,200)
                    .getBody().as(DefaultAddInsuranceResponse.class);
            validationHelper.verify_insurance_form_submit(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_PPO.name()) && testSteps.get(TestSteps.STEP_CLICK_PPO.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_PPO.name());
            FileInputStream ppoButtonJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/PPOButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(ppoButtonJson, WelcomeRequestVO.class);
            final AddInsuranceExtractPlanTypeResponseVO response = addInsuranceHelper.ppoButtonClick(welcomeRequestVO,200)
                    .getBody().as(AddInsuranceExtractPlanTypeResponseVO.class);
            validationHelper.verify_ppo_button(response);
        }

        if (null != testSteps.get(TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_YES_BUTTON.name()) && testSteps.get(TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_YES_BUTTON.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_CLICK_INSURANCE_CARD_UPLOAD_YES_BUTTON.name());
            FileInputStream yesButtonJson= new FileInputStream(new File(System.getProperty("user.dir") + "/" + "src/main/resources/addInsurance/InsuranceCardUploadYesButton.json"));
            WelcomeRequestVO welcomeRequestVO = mapper.readValue(yesButtonJson, WelcomeRequestVO.class);
            final AddInsuranceFormResponseVO response = addInsuranceHelper.insuranceCardUploadYesButtonClick(welcomeRequestVO,200)
                    .getBody().as(AddInsuranceFormResponseVO.class);
            validationHelper.verify_insurance_card_upload_yes_button(response);
        }

        if (null != testSteps.get(TestSteps.STEP_SUBMIT_PPO_FORM.name()) && testSteps.get(TestSteps.STEP_SUBMIT_PPO_FORM.name())) {
            MainUtils.stepLog(logger, TestSteps.STEP_SUBMIT_PPO_FORM.name());
            String filePath = System.getProperty("user.dir") + "/src/main/resources/ABCHospital.pdf";
            final DefaultAddInsuranceResponse response = addInsuranceHelper.ppoFormSubmit(filePath,200)
                    .getBody().as(DefaultAddInsuranceResponse.class);
            validationHelper.verify_insurance_form_submit(response);
        }
    }
}
