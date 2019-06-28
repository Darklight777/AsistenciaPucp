package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;


@JsonRootName("generateTokenOutRO")
public class GenerateTokenOutRO extends BaseOutRO {

    private String token;

    public String getToken() {
        return token;
    }

    @JsonCreator
    public GenerateTokenOutRO(@JsonProperty("errorCode") int errorCode,
                              @JsonProperty("message") String message,
                              @JsonProperty("token") String token){
        super(errorCode,message);
        this.token = token;

    }


}
