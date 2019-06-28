package pe.edu.pucp.grupo02.asistenciapucp.data.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "COURSE")
public class Course {
    @PrimaryKey
    @ColumnInfo(name = "COURSE_ID")
    private int courseId;

    @ColumnInfo(name = "COURSE_NAME")
    private String courseName;

    public int getCourseId() { return courseId; }

    public String getCourseName() { return courseName; }
}
