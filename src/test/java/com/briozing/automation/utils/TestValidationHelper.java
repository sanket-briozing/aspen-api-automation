package com.briozing.automation.utils;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.models.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

public class TestValidationHelper {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    public TestValidationHelper() {
    }

    public ArrayList<String> getWelcomeButtonsExpected() {
        ArrayList<String> expectedWelcomeButtons = new ArrayList<>();
        expectedWelcomeButtons.add("Make a Payment");
        expectedWelcomeButtons.add("Check Balance");
        expectedWelcomeButtons.add("Update Profile");
        expectedWelcomeButtons.add("Add Insurance");
        expectedWelcomeButtons.add("Financial Assistance");
        expectedWelcomeButtons.add("Account Payment History");
        expectedWelcomeButtons.add("Other Request");
        Collections.sort(expectedWelcomeButtons);
        return expectedWelcomeButtons;
    }

    public ArrayList<String> getInsuranceButtonsExpected() {
        ArrayList<String> expectedTypeOfInsuranceButtons = new ArrayList<>();
        expectedTypeOfInsuranceButtons.add("HMO");
        expectedTypeOfInsuranceButtons.add("PPO");
        expectedTypeOfInsuranceButtons.add("POS/EPO");
        expectedTypeOfInsuranceButtons.add("Auto Insurance");
        expectedTypeOfInsuranceButtons.add("Workers Compensation (WC)");
        Collections.sort(expectedTypeOfInsuranceButtons);
        return expectedTypeOfInsuranceButtons;
    }

    public ArrayList<String> getPatientRelationsExpected() {
        ArrayList<String> expectedPatientRelations = new ArrayList<>();
        expectedPatientRelations.add("Aunt");
        expectedPatientRelations.add("Brother");
        expectedPatientRelations.add("Daughter");
        expectedPatientRelations.add("Father");
        expectedPatientRelations.add("Friend");
        expectedPatientRelations.add("Granddaughter");
        expectedPatientRelations.add("Grandfather");
        expectedPatientRelations.add("Grandmother");
        expectedPatientRelations.add("Grandson");
        expectedPatientRelations.add("Legal Guardian");
        expectedPatientRelations.add("Mother");
        expectedPatientRelations.add("Other");
        expectedPatientRelations.add("Step Father");
        expectedPatientRelations.add("Sister");
        expectedPatientRelations.add("Self");
        expectedPatientRelations.add("Step Mother");
        expectedPatientRelations.add("Son");
        expectedPatientRelations.add("Spouse");
        expectedPatientRelations.add("Uncle");
        expectedPatientRelations.add("Patient POE");
        expectedPatientRelations.add("Guarantor POE");
        Collections.sort(expectedPatientRelations);
        return expectedPatientRelations;
    }

    public ArrayList<String> getPlanLevelsExpected() {
        ArrayList<String> expectedPlanLevels = new ArrayList<>();
        expectedPlanLevels.add("Primary");
        expectedPlanLevels.add("Secondary");
        expectedPlanLevels.add("Tertiary");
        Collections.sort(expectedPlanLevels);
        return expectedPlanLevels;
    }

    public void verify_welcome(WelcomeResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"WELCOME","Type :- ");
        AppAssert.assertTrue(actualResponse.getSessionId()!= null, "Session id is not null");
        logger.info("Response session id:- "+ actualResponse.getSessionId());
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Hello, my name is Aspen. I am your patient liaison and I can help you with:","Display message: ");
        ArrayList<String> actualWelcomeButtons = new ArrayList<>();
        for (Option option : actualResponse.getIntentResponseMessage().getOptions()) {
            actualWelcomeButtons.add(option.getDisplayMessage());
        }
        Collections.sort(actualWelcomeButtons);
        AppAssert.assertEqual(actualWelcomeButtons, getWelcomeButtonsExpected(),"Welcome Buttons List : ");
    }

    public void verify_add_insurance_button(WelcomeResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"LOGIN_FORM_WITH_PREVIOUS_INTENT","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "I can help you with that but before that I need to validate your identity. Please login with your username and password","Display message: ");
    }

    public void verify_login_for_add_insurance(DefaultAddInsuranceResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_ADD_INSURANCE","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "What type of Insurance you would like to add?","Display message: ");
        ArrayList<String> actualInsuranceButtons = new ArrayList<>();
        for (Option option : actualResponse.getIntentResponseMessage().getOptions()) {
            actualInsuranceButtons.add(option.getDisplayMessage());
        }
        Collections.sort(actualInsuranceButtons);
        AppAssert.assertEqual(actualInsuranceButtons, getInsuranceButtonsExpected(),"Insurance Buttons List : ");
    }

    public void verify_auto_insurance_button(AddInsuranceFormResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ADD_INSURANCE_FORM","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Displaying Insurance Form","Display message: ");
        AppAssert.assertEqual(actualResponse.getPlanType(), "Auto Insurance","Plan Type : ");
        ArrayList<String> actualPatientRelation = new ArrayList<>();
        for (PatientRelation patientRelation : actualResponse.getPatientRelations()) {
            actualPatientRelation.add(patientRelation.getDescription());
        }
        Collections.sort(actualPatientRelation);
        ArrayList<String> actualPlanLevels = new ArrayList<>();
        for (PlanLevel planLevel : actualResponse.getPlanLevels()) {
            actualPlanLevels.add(planLevel.getDescription());
        }
        Collections.sort(actualPlanLevels);
        AppAssert.assertEqual(actualPatientRelation  ,getPatientRelationsExpected() ,"Patient Relations List : ");
        AppAssert.assertEqual(actualPlanLevels  ,getPlanLevelsExpected() ,"Plan Levels List : ");
        AppAssert.assertEqual(actualResponse.getAccountNumbers().get(0), "Test001","Account numbers : ");
    }

    public void verify_insurance_form_submit(DefaultAddInsuranceResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_ADD_INSURANCE","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Your insurance card has been added to your profile. What else can I assist you today with?","Display message: ");
    }

    public void verify_hmo_button(AddInsuranceExtractPlanTypeResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ADD_INSURANCE_EXTRACT_PLAN_TYPE","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Do you have an insurance card to upload?","Display message: ");
        AppAssert.assertEqual(actualResponse.getPlanType(), "HMO","Plan Type : ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Yes","Yes button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"No","No button text:- ");
    }

    public void verify_insurance_card_upload_no_button(AddInsuranceFormResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ADD_INSURANCE_FORM","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Displaying Insurance Form","Display message: ");
        ArrayList<String> actualPatientRelation = new ArrayList<>();
        for (PatientRelation patientRelation : actualResponse.getPatientRelations()) {
            actualPatientRelation.add(patientRelation.getDescription());
        }
        Collections.sort(actualPatientRelation);
        ArrayList<String> actualPlanLevels = new ArrayList<>();
        for (PlanLevel planLevel : actualResponse.getPlanLevels()) {
            actualPlanLevels.add(planLevel.getDescription());
        }
        Collections.sort(actualPlanLevels);
        AppAssert.assertEqual(actualPatientRelation  ,getPatientRelationsExpected() ,"Patient Relations List : ");
        AppAssert.assertEqual(actualPlanLevels  ,getPlanLevelsExpected() ,"Plan Levels List : ");
        AppAssert.assertEqual(actualResponse.getAccountNumbers().get(0), "Test001","Account numbers : ");
    }

    public void verify_ppo_button(AddInsuranceExtractPlanTypeResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ADD_INSURANCE_EXTRACT_PLAN_TYPE","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Do you have an insurance card to upload?","Display message: ");
        AppAssert.assertEqual(actualResponse.getPlanType(), "PPO","Plan Type : ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Yes","Yes button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"No","No button text:- ");
    }

    public void verify_insurance_card_upload_yes_button(AddInsuranceFormResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ADD_INSURANCE_UPLOAD_CARD_FORM","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Displaying Upload Card Insurance Form","Display message: ");
        ArrayList<String> actualPatientRelation = new ArrayList<>();
        for (PatientRelation patientRelation : actualResponse.getPatientRelations()) {
            actualPatientRelation.add(patientRelation.getDescription());
        }
        Collections.sort(actualPatientRelation);
        ArrayList<String> actualPlanLevels = new ArrayList<>();
        for (PlanLevel planLevel : actualResponse.getPlanLevels()) {
            actualPlanLevels.add(planLevel.getDescription());
        }
        Collections.sort(actualPlanLevels);
        AppAssert.assertEqual(actualPatientRelation  ,getPatientRelationsExpected() ,"Patient Relations List : ");
        AppAssert.assertEqual(actualPlanLevels  ,getPlanLevelsExpected() ,"Plan Levels List : ");
        AppAssert.assertEqual(actualResponse.getAccountNumbers().get(0), "Test001","Account numbers : ");
    }

    public void verify_enter_why_billed(WelcomeResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"LOGIN_FORM_WITH_PREVIOUS_INTENT","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "I can help you with that but before that I need to validate your identity. Please login with your username and password","Display message: ");
    }

    public void verify_login_for_why_billed(WhyIAmBeingBilledResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"WHY_I_AM_BEING_BILLED","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Your account details are listed below. Did you find what you were looking for?","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Yes","Yes button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"No","No button text:- ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getFacilityNumber(),"2345","Facility number: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getFacilityKey(),"74564027-79ac-4847-a46c-eb369e1d5516","Facility key: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getClientRefNumber(),"Test001","Client ref number: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getInternalAccountNumber().toString(),"3171525","Internal account number: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getAccountBalance().toString(),"946.0","Account balance: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getPatientFirstName(),"Bruce","Patient First name: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getPatientLastName(),"DOE","Patient Last name: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getAuthenticationMethod(),"PORTALACCOUNT","Authentication Method: ");
        AppAssert.assertEqual(actualResponse.getAuthorizedAccounts().get(0).getServiceDate(),"2005-06-25T00:00:00.000+0000","Service Date: ");
    }

    public void verify_found_what_looking_for_yes(DefaultWhyIAmBeingBilledResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_WHY_I_AM_BEING_BILLED","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "What else can I assist you today with?","Display message: ");
    }

    public void verify_found_what_looking_for_no(DefaultWhyIAmBeingBilledResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_WHY_I_AM_BEING_BILLED","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "For additional account details, please contact our customer service department.","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Live Chat with an Agent","1st option:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"Request a Call Back","2nd option:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(2).getDisplayMessage(),"Display Contact Information","3rd option:- ");
    }

    public void verify_contact_customer_service_button_click(DefaultWhyIAmBeingBilledResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_FALLBACK","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
//        AppAssert.assertEqual(actualResponse.getMessage(),"What was that?","Message:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Sorry, what was that?","Display message: ");
    }

    public void verify_account_payment_history_button_click(WelcomeResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"LOGIN_FORM_WITH_PREVIOUS_INTENT","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "I can help you with that but before that I need to validate your identity. Please login with your username and password","Display message: ");
    }

    public void verify_login_for_account_payment_history(AccountPaymentsResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ACCOUNT_PAYMENTS","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Your recent payments are listed below. Would you like to see additional payment history?","Display message: ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getTotalRecords().toString(),"14","Total records:- ");
        for (PaymentRecord paymentRecord : actualResponse.getAccountPayments().getPaymentRecords()) {
            AppAssert.assertEqual(paymentRecord.getInternalAccountNumber(),"3171525","Internal account number:- ");
            AppAssert.assertEqual(paymentRecord.getClientRefNumber(),"Test001","Client Ref number:- ");
        }
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(0).getDateOfpayment(),"2017-02-17T00:00:00","Date of payment for 1st record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(1).getDateOfpayment(),"2017-02-17T00:00:00","Date of payment for 2nd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(2).getDateOfpayment(),"2017-02-17T00:00:00","Date of payment for 3rd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(3).getDateOfpayment(),"2017-02-17T00:00:00","Date of payment for 4th record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(4).getDateOfpayment(),"2014-12-18T00:00:00","Date of payment for 5th record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(0).getAmountApplied().toString(),"30.0","Amount applied for 1st record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(1).getAmountApplied().toString(),"29.0","Amount applied for 2nd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(2).getAmountApplied().toString(),"1000.0","Amount applied for 3rd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(3).getAmountApplied().toString(),"10.0","Amount applied for 4th record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(4).getAmountApplied().toString(),"5.0","Amount applied for 5th record:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Yes","Yes button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"No","No button text:- ");
    }

    public void verify_additional_payments_no_button_click(DefaultAccountPaymentsResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_ACCOUNT_PAYMENTS","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "What else can I assist you today with?","Display message: ");
    }

    public void verify_additional_payments_yes_button_click(DefaultAccountPaymentsResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_ACCOUNT_PAYMENTS","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "How far back would you like to go?","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"3 months","display message for 1st option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"6 months","display message for 2nd option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(2).getDisplayMessage(),"9 months","display message for 3rd option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(3).getDisplayMessage(),"1 year","display message for 4th option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(4).getDisplayMessage(),"custom date range","display message for 5th option:-");
    }

    public void verify_custom_date_range_button_click(DefaultAccountPaymentsResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ACCOUNT_PAYMENTS_USER_SELECTS_CUSTOM_DATE_RANGE_FOR_ADDITIONAL_PAYMENT","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
    }

    public void verify_custom_date_range_submit(AccountPaymentsResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ACCOUNT_PAYMENTS_USER_SUBMITS_CUSTOM_DATE_RANGE_FOR_ADDITIONAL_PAYMENT","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getTotalRecords().toString(),"5","Total records:- ");
        for (PaymentRecord paymentRecord : actualResponse.getAccountPayments().getPaymentRecords()) {
            AppAssert.assertEqual(paymentRecord.getInternalAccountNumber(),"3171525","Internal account number:- ");
            AppAssert.assertEqual(paymentRecord.getClientRefNumber(),"Test001","Client Ref number:- ");
        }
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(0).getDateOfpayment(),"2014-05-15T00:00:00","Date of payment for 1st record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(1).getDateOfpayment(),"2014-03-27T00:00:00","Date of payment for 2nd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(2).getDateOfpayment(),"2014-01-15T00:00:00","Date of payment for 3rd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(3).getDateOfpayment(),"2013-05-24T00:00:00","Date of payment for 4th record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(4).getDateOfpayment(),"2013-05-24T00:00:00","Date of payment for 5th record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(0).getAmountApplied().toString(),"0.01","Amount applied for 1st record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(1).getAmountApplied().toString(),"50.0","Amount applied for 2nd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(2).getAmountApplied().toString(),"50.0","Amount applied for 3rd record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(3).getAmountApplied().toString(),"20.0","Amount applied for 4th record:- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getPaymentRecords().get(4).getAmountApplied().toString(),"10.0","Amount applied for 5th record:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Your payments are listed below. Did you find what you were looking for?","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Yes","Yes button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"No","No button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(2).getDisplayMessage(),"Change Date Range","Change date range button text:- ");
    }

    public void verify_3_months_button_click(AccountPaymentsResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ACCOUNT_PAYMENTS_USER_PROVIDES_DURATION_FOR_ADDITIONAL_PAYMENT","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getAccountPayments().getTotalRecords().toString(),"0","Total records:- ");
        AppAssert.assertTrue(actualResponse.getAccountPayments().getPaymentRecords().size()==0,"Payment records are empty ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Your payments are listed below. Did you find what you were looking for?","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Yes","Yes button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"No","No button text:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(2).getDisplayMessage(),"Change Date Range","Change date range button text:- ");
    }

    public void verify_found_what_looking_for_yes(DefaultAccountPaymentsResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_ACCOUNT_PAYMENTS","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "What else can I assist you today with?","Display message: ");
        AppAssert.assertTrue(actualResponse.getIntentResponseMessage().getOptions().size()==0,"Options are empty: ");
    }

    public void verify_found_what_looking_for_no(DefaultAccountPaymentsResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_ACCOUNT_PAYMENTS","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "If you don’t see your payment listed, it could have been applied to an account that has been closed. Please contact our customer service department with any questions.","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Request a Call Back","1st option:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"Live Chat with an Agent","2nd option:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(2).getDisplayMessage(),"Display Contact Information","3rd option:- ");
    }

    public void verify_change_date_range_button_click(DefaultAccountPaymentsResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_ACCOUNT_PAYMENTS","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "How far back would you like to go?","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"3 months","display message for 1st option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"6 months","display message for 2nd option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(2).getDisplayMessage(),"9 months","display message for 3rd option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(3).getDisplayMessage(),"1 year","display message for 4th option:-");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(4).getDisplayMessage(),"custom date range","display message for 5th option:-");
    }

    public void verify_enter_already_paid(WelcomeResponseVO actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"LOGIN_FORM_WITH_PREVIOUS_INTENT","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "I can help you with that but before that I need to validate your identity. Please login with your username and password","Display message: ");
    }

    public void verify_login_for_already_paid(DefaultResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"ALREADY_PAID","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "I’m sorry, I’m unable to locate any recent payments on this account. Please contact our customer service department with any questions.","Display message: ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(0).getDisplayMessage(),"Live Chat with an Agent","1st option:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(1).getDisplayMessage(),"Request a Call Back","2nd option:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getOptions().get(2).getDisplayMessage(),"Display Contact Information","3rd option:- ");
    }

    public void verify_contact_customer_service_button_click(DefaultResponse actualResponse) {
        AppAssert.assertEqual(actualResponse.getType(),"DEFAULT_FALLBACK","Type :- ");
        AppAssert.assertEqual(actualResponse.getSessionId(), TestConstants.sessionId,"Session Id :- ");
//        AppAssert.assertEqual(actualResponse.getMessage(),"What was that?","Message:- ");
        AppAssert.assertEqual(actualResponse.getIntentResponseMessage().getDisplayMessage(), "Sorry, can you say that again?","Display message: ");
    }
}



