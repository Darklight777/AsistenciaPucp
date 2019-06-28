package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;

@JsonRootName("studentMessagesRO")
public class StudentMessagesOutRO extends BaseOutRO {

    private int userId;
    private String message1;
    private String message2;
    private String message3;

    @JsonCreator
    public StudentMessagesOutRO(@JsonProperty("errorCode") int errorCode,
                                @JsonProperty("message") String message,
                                @JsonProperty("userId") int userId,
                                @JsonProperty("msje1") String message1,
                                @JsonProperty("msje2") String message2,
                                @JsonProperty("msje3") String message3) {
        super(errorCode, message);
        this.userId = userId;
        this.message1 = message1;
        this.message2 = message2;
        this.message3 = message3;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage1() {
        return message1;
    }

    public String getMessage2() {
        return message2;
    }

    public String getMessage3() {
        return message3;
    }
}
