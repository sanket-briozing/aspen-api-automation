package com.briozing.automation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AuthorizedAccount {

    public String facilityNumber;

    public String facilityKey;

    public String clientRefNumber;

    public Integer internalAccountNumber;

    public Float accountBalance;

    public String patientFirstName;

    public String patientLastName;

    public String authenticationMethod;

    public String serviceDate;
}