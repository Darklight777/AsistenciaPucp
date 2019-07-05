package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;

@JsonRootName("teacherMessagesOutRO")
public class TeacherMessagesOutRO extends BaseOutRO {

    private int msjeId;
    private String curso1;
    private String[] horarios1;
    private String curso2;
    private String[] horarios2;
    private String curso3;
    private String[] horarios3;

    @JsonCreator
    public TeacherMessagesOutRO(@JsonProperty("errorCode") int errorCode,
                                @JsonProperty("message") String message,
                                @JsonProperty("curso1") String curso1,
                                @JsonProperty("horarios1") String[] horarios1,
                                @JsonProperty("curso2") String curso2,
                                @JsonProperty("horarios2") String[] horarios2,
                                @JsonProperty("curso3") String curso3,
                                @JsonProperty("horarios3") String[] horarios3) {
        super(errorCode, message);
        this.curso1 = curso1;
        this.horarios1 = horarios1;
        this.curso2 = curso2;
        this.horarios2 = horarios2;
        this.curso3 = curso3;
        this.horarios3 = horarios3;
    }

    public int getMsjeId() {
        return msjeId;
    }

    public String getCurso1() {
        return curso1;
    }

    public String[] getHorarios1() {
        return horarios1;
    }

    public String getCurso2() {
        return curso2;
    }

    public String[] getHorarios2() {
        return horarios2;
    }

    public String getCurso3() {
        return curso3;
    }

    public String[] getHorarios3() {
        return horarios3;
    }
}
