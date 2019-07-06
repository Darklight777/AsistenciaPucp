package pe.edu.pucp.grupo02.asistenciapucp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pe.edu.pucp.grupo02.asistenciapucp.data.db.dao.StudentAttendanceDao;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.dao.TeacherCurHorDao;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.dao.UserDao;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.StudentAttendance;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.User;

@Database(entities = {User.class, StudentAttendance.class, TeacherCurHor.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "asistenciapucp.db";
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
    public abstract StudentAttendanceDao studentAttendanceDao();
    public abstract TeacherCurHorDao teacherMessageDao();
}
