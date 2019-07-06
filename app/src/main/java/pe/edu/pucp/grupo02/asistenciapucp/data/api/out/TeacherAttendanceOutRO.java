package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;

@JsonRootName("teacherAttendanceOutRO")
public class TeacherAttendanceOutRO extends BaseOutRO {

    private String porc1;
    private String porc2;
    private String porc3;
    private int attId;

    @JsonCreator
    public TeacherAttendanceOutRO(@JsonProperty("errorCode") int errorCode,
                     @JsonProperty("message") String message,
                     @JsonProperty("attId") int attId,
                     @JsonProperty("porc1") String porc1,
                     @JsonProperty("porc2") String porc2,
                     @JsonProperty("porc3") String  porc3) {
        super(errorCode, message);
        this.porc1 = porc1;
        this.porc2 = porc2;
        this.porc3 = porc3;
        this.attId = attId;
    }

    public String getPorc1() {
        return porc1;
    }

    public String getPorc2() {
        return porc2;
    }

    public String getPorc3() {
        return porc3;
    }

    public int getAttId() {
        return attId;
    }
}
