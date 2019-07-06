package pe.edu.pucp.grupo02.asistenciapucp.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "USER")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "USER_ID")
    private int userId;

    @ColumnInfo(name = "FULL_NAME")
    private String fullName;

    @ColumnInfo(name = "EMAIL")
    private String email;

    @ColumnInfo(name = "USERNAME")
    private String username;

    @ColumnInfo(name = "HASH")
    private String hash;

    @ColumnInfo(name = "SERVIDORID")
    private String servidor;

    @ColumnInfo(name = "KEY_AUTORIZACION")
    private int keyAutorizacion;

    @ColumnInfo(name = "USUARIO_AUTORIZACION")
    private String nombreAlumno;

    @ColumnInfo(name = "ROL_USUARIO")
    private String rol;

    public User(int userId, String fullName, String email, String username, String hash, String servidor, int keyAutorizacion, String nombreAlumno, String rol) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.hash = hash;
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

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
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