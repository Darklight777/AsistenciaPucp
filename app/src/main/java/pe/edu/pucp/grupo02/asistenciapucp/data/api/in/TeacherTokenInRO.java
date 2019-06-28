package pe.edu.pucp.grupo02.asistenciapucp.data.api.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseInRO;

@JsonRootName("teacherTokenInRO")
public class TeacherTokenInRO extends BaseInRO {

    private String date;

    @JsonCreator
    public TeacherTokenInRO(@JsonProperty("applicationName") String applicationName,
                     @JsonProperty("date") String date){
        super(applicationName);
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}

