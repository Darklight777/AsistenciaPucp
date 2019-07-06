package pe.edu.pucp.grupo02.asistenciapucp.data.api.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.base.BaseOutRO;

@JsonRootName("userOutRO")
public class UserOutRO extends BaseOutRO {

    private int userId;
    private String fullName;
    private String email;

    private String servidor;
    private int keyAutorizacion;
    private String nombreAlumno;
    private String rol;

    @JsonCreator
    public UserOutRO(@JsonProperty("errorCode") int errorCode,
                     @JsonProperty("message") String message,
                     @JsonProperty("userId") int userId,
                     @JsonProperty("fullName") String fullName,
                     @JsonProperty("email") String email,
                     @JsonProperty("servidorID") String servidor,
                     @JsonProperty("keyAutorizacion") int keyAutorizacion,
                     @JsonProperty("usuarioAutorizacion") String nombreAlumno,
                     @JsonProperty("rolUsuario") String rol
                     ) {
        super(errorCode, message);
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.servidor = servidor;
        this.keyAutorizacion = keyAutorizacion;
        this.nombreAlumno = nombreAlumno;
        this.rol = rol;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }


    public String getServidor() {
        return servidor;
    }

    public int getKeyAutorizacion() {
        return keyAutorizacion;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public String getRol() {
        return rol;
    }
}