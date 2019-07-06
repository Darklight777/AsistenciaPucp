package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.StudentAttendanceOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.AppDatabase;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.StudentAttendance;


public class StudentAttendanceSaveTask extends AsyncTask<Void,Void, Boolean> {

    private final static String TAG = "MFL_STUDENT_SAVETASK";
    private WeakReference<IStudentView> view;
    private StudentAttendanceOutRO studentAttendanceOutRO;

    protected StudentAttendanceSaveTask(IStudentView view, StudentAttendanceOutRO studentAttendanceOutRO){
        this.view = new WeakReference<>(view);
        this.studentAttendanceOutRO = studentAttendanceOutRO;
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        // Verificar que la vista todavía está disponible
        IStudentView view = this.view.get();
        if (view == null) return false;
        // Inicializar la base de datos, si es que aún no se hizo
        AppDatabase database = AppDatabase.getInstance(view.getContext());
        if (database == null) {
            Log.d(TAG, "La base de datos no se inicializó, ¿quizás terminó el Activity?");
            return false;
        }
        // Verificar si no se ha guardado con anterioridad
        int userId = studentAttendanceOutRO.getUserId();
        if (database.studentAttendanceDao().findById(userId) == null) {
            try {
                // Guardar los datos de los cursos y horarios en la base de datos
                StudentAttendance StudentAttendance = new StudentAttendance(userId, studentAttendanceOutRO.getPorce1(),
                                                                                    studentAttendanceOutRO.getPorce2(),
                                                                                    studentAttendanceOutRO.getPorce3());
                database.studentAttendanceDao().insert(StudentAttendance);
            } catch (Exception ex) {
                System.out.println("error");
                ex.printStackTrace();
            }
        }
        return true;
    }
}