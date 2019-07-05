package pe.edu.pucp.grupo02.asistenciapucp.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "TEACHERCURHOR")
public class TeacherCurHor {

    @PrimaryKey
    @ColumnInfo(name = "MSJE_ID")
    private int msjeId;

    @ColumnInfo(name = "CURSO1")
    private String curso1;

    @ColumnInfo(name = "CURSO2")
    private String curso2;

    @ColumnInfo(name = "CURSO3")
    private String curso3;

    @ColumnInfo(name = "HORARIO1")
    private String horario1;

    @ColumnInfo(name = "HORARIO2")
    private String horario2;

    @ColumnInfo(name = "HORARIO3")
    private String horario3;

    public TeacherCurHor() {
    }

    public TeacherCurHor(int msjeId, String curso1, String curso2, String curso3,
                         String[] horario1, String[] horario2, String[] horario3) {
        this.msjeId = msjeId;
        this.curso1 = curso1;
        this.curso2 = curso2;
        this.curso3 = curso3;
        this.horario1 = horario1[0]+","+horario1[1]+","+horario1[2];
        this.horario2 = horario2[0]+","+horario2[1]+","+horario2[2];
        this.horario3 = horario3[0]+","+horario3[1]+","+horario3[2];
    }

    public int getMsjeId() {
        return msjeId;
    }

    public String getCurso1() {
        return curso1;
    }

    public String getCurso2() {
        return curso2;
    }

    public String getCurso3() {
        return curso3;
    }

    public String getHorario1() {
        return horario1;
    }

    public String getHorario2() {
        return horario2;
    }

    public String getHorario3() {
        return horario3;
    }

    public void setMsjeId(int msjeId) {
        this.msjeId = msjeId;
    }

    public void setCurso1(String curso1) {
        this.curso1 = curso1;
    }

    public void setCurso2(String curso2) {
        this.curso2 = curso2;
    }

    public void setCurso3(String curso3) {
        this.curso3 = curso3;
    }

    public void setHorario1(String horario1) {
        this.horario1 = horario1;
    }

    public void setHorario2(String horario2) {
        this.horario2 = horario2;
    }

    public void setHorario3(String horario3) {
        this.horario3 = horario3;
    }
}
