package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.AppDatabase;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;

public class TeacherCurHorTask extends AsyncTask<Void, Void, TeacherCurHor> {

    private final static String TAG = "MFL_TEACHER_TASK";
    private WeakReference<ITeacherView> view;
    private int msjeId;

    protected TeacherCurHorTask(ITeacherView view, int msjeId) {
        this.view = new WeakReference<>(view);
        this.msjeId = msjeId;
    }

    @Override
    protected TeacherCurHor doInBackground(Void... voids) {
        // Verificar que la vista todavía está disponible
        ITeacherView view = this.view.get();
        if (view == null) return null;
        // Iniciar la base de datos, si es que aún no se hizo
        AppDatabase database = AppDatabase.getInstance(view.getContext());
        if (database == null) {
            Log.d(TAG, "La base de datos no se inicializó, ¿quizás terminó el Activity?");
            return null;
        }
        // Buscar los cursos y horarios con el id mensaje proporcionado
        TeacherCurHor teacherCurHor = database.teacherMessageDao().findById(msjeId);
        if (teacherCurHor == null) return null;
        // Evaluar la contraseña
        return true ? teacherCurHor : null;
    }

    @Override
    protected void onPostExecute(TeacherCurHor teacherCurHor) {
        // Verificar que la vista todavía está disponible
        ITeacherView view = this.view.get();
        if (view == null) return;
        // Verificar si los cursos y horarios fueron encontrados
        if (teacherCurHor != null) {
            view.gotoTeacherMessages(teacherCurHor.getMsjeId(), teacherCurHor.getCurso1(), teacherCurHor.getCurso2(), teacherCurHor.getCurso3(),
                    teacherCurHor.getHorarioArreglo1(), teacherCurHor.getHorarioArreglo2(), teacherCurHor.getHorarioArreglo3());
        } else {
            String message = Utilities.formatString(view.getContext(),
                    R.string.teacher_messages_not_found);
            view.showErrorDialog(message);
        }
    }

}
