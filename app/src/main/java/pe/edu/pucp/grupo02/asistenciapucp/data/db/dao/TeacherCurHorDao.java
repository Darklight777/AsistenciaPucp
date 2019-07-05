package pe.edu.pucp.grupo02.asistenciapucp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;

@Dao
public interface TeacherCurHorDao {

    @Query("SELECT * FROM TEACHERCURHOR WHERE MSJE_ID = :msjeId LIMIT 1")
    TeacherCurHor findById(int msjeId);

    @Insert
    void insert(TeacherCurHor teacherCurHor);

    @Delete
    void delete(TeacherCurHor teacherCurHor);
}
