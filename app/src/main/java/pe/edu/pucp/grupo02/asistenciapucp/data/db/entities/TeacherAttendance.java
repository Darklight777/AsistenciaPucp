package pe.edu.pucp.grupo02.asistenciapucp.data.db.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "TEACHERATTENDANCE")
public class TeacherAttendance {


    @PrimaryKey
    @ColumnInfo(name = "ATT_ID")
    private int att_Id;

    @ColumnInfo(name = "PERCENTAGE1")
    private String p1;

    @ColumnInfo(name = "PERCENTAGE2")
    private String p2;

    @ColumnInfo(name = "PERCENTAGE3")
    private String p3;


    public TeacherAttendance(int att_Id, String p1, String p2, String p3) {
        this.att_Id = att_Id;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public int getAtt_Id() {
        return att_Id;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }
}
