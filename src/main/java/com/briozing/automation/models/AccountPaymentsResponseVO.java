package com.briozing.automation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AccountPaymentsResponseVO {

    public String type;

    public String message;

    public String sessionId;

    public Float totalBalance;

    public AccountPayments accountPayments;

    public IntentResponseMessage intentResponseMessage;

    public Boolean downloadStatement;
}