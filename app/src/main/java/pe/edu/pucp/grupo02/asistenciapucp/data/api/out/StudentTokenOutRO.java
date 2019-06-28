package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;


@JsonRootName("studentTokenOutRO")
public class StudentTokenOutRO extends BaseOutRO {

    private int userId;
    private String curso;
    private String horario;
    private String hora;

    @JsonCreator
    public StudentTokenOutRO(@JsonProperty("errorCode") int errorCode,
                             @JsonProperty("message") String message,
                             @JsonProperty("userId") int userId,
                             @JsonProperty("curso") String curso,
                             @JsonProperty("horario") String horario,
                             @JsonProperty("hora") String hora){
        super(errorCode,message);
        this.userId = userId;
        this.curso = curso;
        this.horario = horario;
        this.hora = hora;
    }

    public int getUserId() {
        return userId;
    }

    public String getCurso() {
        return curso;
    }

    public String getHorario() {
        return horario;
    }

    public String getHora() {
        return hora;
    }
}
