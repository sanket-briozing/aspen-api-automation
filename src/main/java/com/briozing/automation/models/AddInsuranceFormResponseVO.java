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
public class AddInsuranceFormResponseVO {

    public String type;

    public String message;

    public String sessionId;

    public Float totalBalance;

    public IntentResponseMessage intentResponseMessage;

    public String planType = null;

    public List<PatientRelation> patientRelations = null;

    public List<PlanLevel> planLevels = null;

    public List<String> accountNumbers = null;

    public Boolean downloadStatement;
}
