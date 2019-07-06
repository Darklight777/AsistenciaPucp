package pe.edu.pucp.grupo02.asistenciapucp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.StudentAttendance;

@Dao
public interface StudentAttendanceDao {

    @Query("SELECT * FROM STUDENTATTENDANCE WHERE USER_ID = :userId LIMIT 1")
    StudentAttendance findById(int userId);

    @Insert
    void insert(StudentAttendance studentAttendance);

    @Delete
    void delete(StudentAttendance studentAttendance);
}
