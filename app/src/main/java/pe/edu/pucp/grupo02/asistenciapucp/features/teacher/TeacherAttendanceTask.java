package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.AppDatabase;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherAttendance;
import pe.edu.pucp.grupo02.asistenciapucp.data.db.entities.TeacherCurHor;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;

public class TeacherAttendanceTask extends AsyncTask<Void, Void, TeacherAttendance> {

    private final static String TAG = "AP_TEACHER_TASK";
    private WeakReference<ITeacherView> view;
    private int attId;

    protected TeacherAttendanceTask(ITeacherView view, int attId)
    {
        this.view = new WeakReference<>(view);
        this.attId = attId;
    }

    @Override
    protected TeacherAttendance doInBackground(Void... voids)
    {
        //Verificar que la vista esta aun disponible
        ITeacherView view = this.view.get();
        if (view == null) return null;
        //Iniciar la base de datos
        AppDatabase database = AppDatabase.getInstance(view.getContext());
        if (database == null) {
            Log.d(TAG, "La base de datos no se inicializó, ¿quizás terminó el Activity?");
            return null;
        }
        // Buscar los cursos y horarios con el id mensaje proporcionado
        TeacherAttendance teacherAttendance = database.teacherAttendanceDao().findById(attId);
        if (teacherAttendance == null) return null;
        // Evaluar
        return teacherAttendance;
    }

    @Override
    protected void onPostExecute(TeacherAttendance teacherAttendance) {
        // Verificar que la vista todavía está disponible
        ITeacherView view = this.view.get();
        if (view == null) return;
        // Verificar si los cursos y horarios fueron encontrados
        if (teacherAttendance != null) {
            view.MoverATeacherAttendance(teacherAttendance.getAtt_Id(), teacherAttendance.getP1(), teacherAttendance.getP2(), teacherAttendance.getP3());
        } else {
            String message = Utilities.formatString(view.getContext(),
                    R.string.teacher_messages_not_found);
            view.showErrorDialog(message);
        }
    }



}


