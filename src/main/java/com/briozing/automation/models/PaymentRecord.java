package com.briozing.automation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PaymentRecord {

    public String internalAccountNumber;

    public String clientRefNumber;

    public String dateOfpayment;

    public Float amountApplied;
}