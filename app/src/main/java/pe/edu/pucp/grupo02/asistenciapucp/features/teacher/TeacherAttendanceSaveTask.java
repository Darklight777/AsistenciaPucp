package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.TeacherAttendanceOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.TeacherMessagesOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.AppDatabase;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherAttendance;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;

public class TeacherAttendanceSaveTask extends AsyncTask<Void, Void, Boolean> {

    private final static String TAG = "AP_TEACHERATT_SAVE";
    private WeakReference<ITeacherView> view;
    private TeacherAttendanceOutRO teacherAttendanceOutRO;

    protected TeacherAttendanceSaveTask(ITeacherView view, TeacherAttendanceOutRO teacherAttendanceOutRO) {
        this.view = new WeakReference<>(view);
        this.teacherAttendanceOutRO = teacherAttendanceOutRO;
    }


    protected Boolean doInBackground(Void... voids) {
        // Verificar que la vista todavía está disponible
        ITeacherView view = this.view.get();
        if (view == null) return false;
        // Inicializar la base de datos, si es que aún no se hizo
        AppDatabase database = AppDatabase.getInstance(view.getContext());
        if (database == null) {
            Log.d(TAG, "La base de datos no se inicializó, ¿quizás terminó el Activity?");
            return false;
        }
        // Verificar si no se ha guardado con anterioridad
        int attId = teacherAttendanceOutRO.getAttId();
        if (database.teacherAttendanceDao().findById(attId) == null) {
            try {
                // Guardar los datos de los cursos y horarios en la base de datos
                TeacherAttendance TeacherAttendance = new TeacherAttendance(attId, teacherAttendanceOutRO.getPorc1(),
                            teacherAttendanceOutRO.getPorc2(), teacherAttendanceOutRO.getPorc3());
                database.teacherAttendanceDao().insert(TeacherAttendance);
            } catch (Exception ex) {
                System.out.println("error");
                ex.printStackTrace();
            }
        }
        return true;
    }
}
