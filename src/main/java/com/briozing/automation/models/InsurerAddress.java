package com.briozing.automation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class InsurerAddress {

    public String line1;

    public String line2;

    public String city;

    public String state;

    public String zipCode;

    public String country;
}