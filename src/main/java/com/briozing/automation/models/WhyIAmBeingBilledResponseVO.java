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
public class WhyIAmBeingBilledResponseVO {

    public String type;

    public String message;

    public String sessionId;

    public Float totalBalance;

    public List<AuthorizedAccount> authorizedAccounts = null;

    public IntentResponseMessage intentResponseMessage;

    public Boolean downloadStatement;
}