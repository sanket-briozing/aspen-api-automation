package com.briozing.automation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AddInsuranceExtractPlanTypeResponseVO {

    public String type;

    public String message;

    public String sessionId;

    public Float totalBalance;

    public IntentResponseMessage intentResponseMessage;

    public String planType;

    public Boolean downloadStatement;
}