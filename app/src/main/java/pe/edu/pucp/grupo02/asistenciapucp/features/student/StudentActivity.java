package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.attendance.StudentAttendanceActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.messages.StudentMessagesActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.token.StudentTokenActivity;

public class StudentActivity extends AppCompatActivity implements IStudentView{

    //TAG = AsistenciaPucp_STUDENT ....
    private final static String TAG = "AP_STUDENT_VIEW";
    public final static String STUDENT_MSJE1 = "STUDENT_MSJE1";
    public final static String STUDENT_MSJE2 = "STUDENT_MSJE2";
    public final static String STUDENT_MSJE3 = "STUDENT_MSJE3";

    public final static String STUDENT_PORC1 = "STUDENT_PORC1";
    public final static String STUDENT_PORC2 = "STUDENT_PORC2";
    public final static String STUDENT_PORC3 = "STUDENT_PORC3";

    private IStudentPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mPresenter = new StudentPresenter(this);
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, PrincipalActivity.class);
        startActivity(anterior);
    }
    public void MoverAIngresarToken(View view){
        Intent siguiente = new Intent(this, StudentTokenActivity.class);
        startActivity(siguiente);
    }
    public void MoverAStudentAttendance(View view){
        // Validar datos del usuario
        if (mPresenter.verifyMessagesData()) {
            // Mostrar mensajes
            mPresenter.asistenciaRest();
        }
    }

    public void MoverAStudentMessages(View view) {
        // Validar datos del usuario
        if (mPresenter.verifyMessagesData()) {
            // Mostrar mensajes
            mPresenter.anunciosRest();
        }
    }

    public void askForMessagesOffline() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.login_dlg_offline_title)
                .setMessage(R.string.login_dlg_offline_msg)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Obtener mensajes sin conexión
                                mPresenter.messagesOffline();
                            }
                        })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    public void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.login_dlg_error_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public void gotoStudentAttendance(String porce1, String porce2, String porce3){
        // Iniciar la actividad de porcentaje de asistencias
        Intent siguiente = new Intent(this, StudentAttendanceActivity.class);
        siguiente.putExtra(STUDENT_PORC1, porce1);
        siguiente.putExtra(STUDENT_PORC2, porce2);
        siguiente.putExtra(STUDENT_PORC3, porce3);
        startActivity(siguiente);
    }

    public void gotoStudentMessages(String msje1, String msje2, String msje3){
        // Iniciar la actividad de mensajes de estudiantes
        Intent siguiente = new Intent(this, StudentMessagesActivity.class);
        siguiente.putExtra(STUDENT_MSJE1, msje1);
        siguiente.putExtra(STUDENT_MSJE2, msje2);
        siguiente.putExtra(STUDENT_MSJE3, msje3);
        startActivity(siguiente);
    }

    @Override
    public Context getContext() { return this; }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }
}
