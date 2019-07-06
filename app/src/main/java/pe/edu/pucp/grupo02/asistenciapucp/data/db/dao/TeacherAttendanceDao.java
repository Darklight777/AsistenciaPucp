package pe.edu.pucp.grupo02.asistenciapucp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherAttendance;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;

@Dao
public interface TeacherAttendanceDao {
    @Query("SELECT * FROM TEACHERATTENDANCE WHERE ATT_ID = :att_Id LIMIT 1")
    TeacherAttendance findById(int att_Id);

    @Insert
    void insert(TeacherAttendance teacherAttendance);

    @Delete
    void delete(TeacherAttendance teacherAttendance);
}
