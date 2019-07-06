package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.AppDatabase;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.StudentAttendance;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;
import pe.edu.pucp.grupo02.asistenciapucp.features.base.IView;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.ITeacherView;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;

public class StudentAttendanceLoginTask extends AsyncTask<Void,Void, StudentAttendance>{

    private final static String TAG = "MFL_STUDENT_LOGINTASK";
    private WeakReference<IStudentView> view;
    private int userId;

    protected StudentAttendanceLoginTask(IStudentView view, int userId) {
        this.view = new WeakReference<>(view);
        this.userId = userId;
    }

    @Override
    protected StudentAttendance doInBackground(Void... voids) {
        // Verificar que la vista todavía está disponible
        IStudentView view = this.view.get();
        if (view == null) return null;
        // Iniciar la base de datos, si es que aún no se hizo
        AppDatabase database = AppDatabase.getInstance(view.getContext());
        if (database == null) {
            Log.d(TAG, "La base de datos no se inicializó, ¿quizás terminó el Activity?");
            return null;
        }
        // Buscar los cursos y horarios con el id mensaje proporcionado
        StudentAttendance studentAttendance = database.studentAttendanceDao().findById(userId);
        if (studentAttendance == null) return null;
        // Evaluar la contraseña
        return true ? studentAttendance : null;
    }

    @Override
    protected void onPostExecute(StudentAttendance studentAttendance) {
        // Verificar que la vista todavía está disponible
        IStudentView view = this.view.get();
        if (view == null) return;
        // Verificar si los cursos y horarios fueron encontrados
        if (studentAttendance != null) {
            view.gotoStudentAttendance(studentAttendance.getUserId(),
                    studentAttendance.getPorce1(),
                    studentAttendance.getPorce2(),
                    studentAttendance.getPorce3());
        } else {
            String message = Utilities.formatString(view.getContext(),
                    R.string.teacher_messages_not_found);
            view.showErrorDialog(message);
        }
    }
}
