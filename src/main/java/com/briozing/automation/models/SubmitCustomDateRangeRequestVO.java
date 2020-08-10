package com.briozing.automation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class SubmitCustomDateRangeRequestVO {

    public String queryText;

    public String intentType;

    public CustomDateRangeRequest customDateRangeRequest;
}