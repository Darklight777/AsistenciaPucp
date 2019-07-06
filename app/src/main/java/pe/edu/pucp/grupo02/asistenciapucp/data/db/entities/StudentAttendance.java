package pe.edu.pucp.grupo02.asistenciapucp.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "STUDENTATTENDANCE")
public class StudentAttendance {

    @PrimaryKey
    @ColumnInfo(name = "USER_ID")
    private int userId;

    @ColumnInfo(name = "PORC_1")
    private String porce1;

    @ColumnInfo(name = "PORC_2")
    private String porce2;

    @ColumnInfo(name = "PORC_3")
    private String porce3;

    public StudentAttendance(int userId, String porce1, String porce2, String porce3) {
        this.userId = userId;
        this.porce1 = porce1;
        this.porce2 = porce2;
        this.porce3 = porce3;
    }
    public int getUserId() { return userId; }

    public String getPorce1() { return porce1; }

    public String getPorce2() { return porce2; }

    public String getPorce3() { return porce3; }

    public void setUserId(int userId) { this.userId = userId; }

    public void setPorce1(String porce1) { this.porce1 = porce1; }

    public void setPorce2(String porce2) { this.porce2 = porce2; }

    public void setPorce3(String porce3) { this.porce3 = porce3; }
}


