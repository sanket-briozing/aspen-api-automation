package com.briozing.automation.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PlanLevel {

    public Integer lookupID;

    public Integer parentID;

    public String category;

    public Integer sortOrder;

    public String customData;

    public String description;

    public Object iconURL;

    public Boolean active;

    public String created;
}