package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;


@JsonRootName("studentTokenOutRO")
public class StudentTokenOutRO extends BaseOutRO {

    private boolean ack;

    @JsonCreator
    public StudentTokenOutRO(@JsonProperty("errorCode") int errorCode,
                              @JsonProperty("message") String message,
                              @JsonProperty("message") boolean ack){
        super(errorCode,message);
        this.ack = ack;

    }

    public boolean isAck() {
        return ack;
    }
}
