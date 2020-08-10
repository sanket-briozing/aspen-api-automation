package com.briozing.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class WelcomeResponseVO {

    private String type;

    private String message;

    private String sessionId;

    private IntentResponseMessage intentResponseMessage;

    private Boolean downloadStatement;
}
