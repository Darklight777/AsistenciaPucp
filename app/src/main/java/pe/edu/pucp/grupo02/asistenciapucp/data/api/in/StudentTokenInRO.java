package pe.edu.pucp.grupo02.asistenciapucp.data.api.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseInRO;


@JsonRootName("studentTokenInRO")
public class StudentTokenInRO extends BaseInRO {

    private String data;

    @JsonCreator
    public StudentTokenInRO(@JsonProperty("applicationName") String applicationName,
                                 @JsonProperty("data") String data) {
        super(applicationName);
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
