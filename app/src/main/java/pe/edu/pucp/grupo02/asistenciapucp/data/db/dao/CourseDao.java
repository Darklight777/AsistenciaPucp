package pe.edu.pucp.grupo02.asistenciapucp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.Course;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM COURSE WHERE COURSE_ID = :courseName LIMIT 1")
    Course findByCourseName (String courseName);

    @Query("SELECT * FROM COURSE WHERE COURSE_NAME = :userId LIMIT 1")
    Course findById(int userId);

}
