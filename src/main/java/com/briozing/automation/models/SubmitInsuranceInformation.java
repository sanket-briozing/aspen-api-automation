package com.briozing.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class SubmitInsuranceInformation {

    public String requestAction;

    public String insurerName=null;

    public String insurerPhone=null;

    public String planType;

    public InsurerAddress insurerAddress=null;

    public List<RelatedAccount> relatedAccounts = null;

    public String relationToPatient;

    public String effectiveFrom = null;

    public String effectiveTo = null;

    public String planLevel;

    public String accidentDate = null;

    public String claimNumber = null;

    public String adjusterName = null;

    public String adjusterPhone = null;

    public String subscriberName = null;

    public String subscriberNumber = null;

    public String groupNumber = null;
}