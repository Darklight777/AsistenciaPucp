package pe.edu.pucp.grupo02.asistenciapucp.data.api.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseInRO;

public class TeacherAttendanceInRO extends BaseInRO {

    //Me entrega solo los porcentajes, usar una lista
    private String attendance;

    @JsonCreator
    public TeacherAttendanceInRO(@JsonProperty("applicationName") String applicationName,
                                 @JsonProperty("username") String attendance){
        super(applicationName);
        this.attendance = attendance;
    }

    public String getAttendance() {
        return attendance;
    }
}
