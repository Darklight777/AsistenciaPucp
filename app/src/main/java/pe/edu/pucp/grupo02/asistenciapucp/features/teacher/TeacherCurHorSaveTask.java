package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.TeacherMessagesOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.AppDatabase;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;

public class TeacherCurHorSaveTask extends AsyncTask<Void, Void, Boolean> {

    private final static String TAG = "MFL_TEACHERMSJ_SAVETASK";
    private WeakReference<ITeacherView> view;
    private TeacherMessagesOutRO teacherMessagesOutRO;

    protected TeacherCurHorSaveTask(ITeacherView view, TeacherMessagesOutRO teacherMessagesOutRO) {
        this.view = new WeakReference<>(view);
        this.teacherMessagesOutRO = teacherMessagesOutRO;
    }

    @Override
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
        int msjeId = teacherMessagesOutRO.getMsjeId();
        if (database.teacherMessageDao().findById(msjeId) == null) {
            try {
                // Guardar los datos de los cursos y horarios en la base de datos
                TeacherCurHor TeacherCurHor = new TeacherCurHor(msjeId, teacherMessagesOutRO.getCurso1(), teacherMessagesOutRO.getCurso2(), teacherMessagesOutRO.getCurso3(),
                        teacherMessagesOutRO.getHorarios1(), teacherMessagesOutRO.getHorarios2(), teacherMessagesOutRO.getHorarios3());

                database.teacherMessageDao().insert(TeacherCurHor);
            } catch (Exception ex) {
                System.out.println("error");
                ex.printStackTrace();
            }
        }
        return true;
    }
}
