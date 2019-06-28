package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;

@JsonRootName("studentAttendanceRO")
public class StudentAttendanceOutRO extends BaseOutRO {

    private int userId;
    private String porce1;
    private String porce2;
    private String porce3;

    public StudentAttendanceOutRO(@JsonProperty("errorCode") int errorCode,
                                  @JsonProperty("message") String message,
                                  @JsonProperty("userId") int userId,
                                  @JsonProperty("porc1") String porce1,
                                  @JsonProperty("porc2") String porce2,
                                  @JsonProperty("porc3") String porce3) {
        super(errorCode, message);
        this.userId = userId;
        this.porce1 = porce1;
        this.porce2 = porce2;
        this.porce3 = porce3;
    }

    public int getUserId() {
        return userId;
    }

    public String getPorce1() {
        return porce1;
    }

    public String getPorce2() {
        return porce2;
    }

    public String getPorce3() {
        return porce3;
    }
}
