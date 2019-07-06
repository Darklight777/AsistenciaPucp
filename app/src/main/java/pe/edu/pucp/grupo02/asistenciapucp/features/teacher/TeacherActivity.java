package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.attendance.TeacherAttendanceActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.messages.TeacherMessagesActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token.TeacherTokenActivity;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;

public class TeacherActivity extends AppCompatActivity implements ITeacherView{

    private final static String TAG = "AP_TEACHER_VIEW";

    public final static String TEACHER_TOKEN_EXTRA_COURSENAME = "TEACHER_EXTRA_COURSENAME";
    public final static String TEACHER_TOKEN_EXTRA_COURSETIME = "TEACHER_EXTRA_COURSETIME";
    public final static String TEACHER_TOKEN_EXTRA_COURSESCH = "TEACHER_EXTRA_COURSESCH";

    public final static String TEACHER_ATTENDANCE_EXTRA_P1 = "TEACHER_ATTENDANCE_EXTRA_P1";
    public final static String TEACHER_ATTENDANCE_EXTRA_P2 = "TEACHER_ATTENDANCE_EXTRA_P2";
    public final static String TEACHER_ATTENDANCE_EXTRA_P3 = "TEACHER_ATTENDANCE_EXTRA_P3";

    public final static String TEACHER_MSJEID = "TEACHER_MSJEID";
    public final static String TEACHER_COURSE1 = "TEACHER_COURSE1";
    public final static String TEACHER_COURSE2 = "TEACHER_COURSE2";
    public final static String TEACHER_COURSE3 = "TEACHER_COURSE3";

    public final static String TEACHER_SCHEDULE1 = "TEACHER_SCHEDULE1";
    public final static String TEACHER_SCHEDULE2 = "TEACHER_SCHEDULE2";
    public final static String TEACHER_SCHEDULE3 = "TEACHER_SCHEDULE3";

    private ITeacherPresenter mPresenter;

    private ImageButton mToken;
    private ImageButton mAttendance;
    private ImageButton mAnnounces;

    private DateFormat dateFormat;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        mToken = findViewById(R.id.teacher_btn_token);
        mAnnounces = findViewById(R.id.teacher_btn_messages);
        mAttendance = findViewById(R.id.teacher_btn_attendance);
        mPresenter = new TeacherPresenter(this);

        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = new Date();

    }


    public void retroceder(View view){
        Intent anterior = new Intent(this, PrincipalActivity.class);
        startActivity(anterior);
    }

    /*
    public void MoverAGenerarToken(View view){
           String name = "Nombre del curso";
           String sch = "Schedule";
           String time = "Time 0:00";
           GenerarToken(name,sch,time);
    }*/

    public void token(View v)
    {
        Utilities.hideKeyboard(this);

        //datos de entrada para verificacion:
        if (mPresenter.verifyTokenData("s")){
            mPresenter.tokenRest(dateFormat.format(date));
            Utilities.showMessage(this,R.string.teacher_msg_loading);
        }
    }

    public void messages(View v)
    {
        Utilities.hideKeyboard(this);

        //datos de entrada para verificacion:
        if (mPresenter.verifyMessagesData()){
            mPresenter.messagesRest();
            //Utilities.showMessage(this,"MsjeId: "+ msjeId);
            Utilities.showMessage(this, R.string.teacher_msg_loading);
        }
    }

    public void gotoTeacherMessages(int msjeId, String cur1, String cur2, String cur3, String[] hor1, String[] hor2, String[] hor3) {
        Intent siguiente = new Intent(this, TeacherMessagesActivity.class);
        siguiente.putExtra(TEACHER_MSJEID, msjeId);
        siguiente.putExtra(TEACHER_COURSE1, cur1);
        siguiente.putExtra(TEACHER_COURSE2, cur2);
        siguiente.putExtra(TEACHER_COURSE3, cur3);
        siguiente.putExtra(TEACHER_SCHEDULE1, hor1);
        siguiente.putExtra(TEACHER_SCHEDULE2, hor2);
        siguiente.putExtra(TEACHER_SCHEDULE3, hor3);
        startActivity(siguiente);
    }

    public void attendance(View v)
    {
        Utilities.hideKeyboard(this);


        //datos de entrada para verificacion:

        if (mPresenter.verifyAttendaceData()){
            mPresenter.attendanceRest("20161505");
            Utilities.showMessage(this, R.string.teacher_msg_loading);
        }

    }
    public void MoverAGenerarToken(String name, String sch, String time){
        Intent siguiente = new Intent(this, TeacherTokenActivity.class);
        siguiente.putExtra(TEACHER_TOKEN_EXTRA_COURSENAME, name);
        siguiente.putExtra(TEACHER_TOKEN_EXTRA_COURSESCH, sch);
        siguiente.putExtra(TEACHER_TOKEN_EXTRA_COURSETIME, time);
        startActivity(siguiente);
        //finish();
    }

    public void MoverATeacherAttendance(String n1, String n2, String n3){//String n1, String n2, String n3){
        Intent siguiente = new Intent(this, TeacherAttendanceActivity.class);
        siguiente.putExtra(TEACHER_ATTENDANCE_EXTRA_P1, n1);
        siguiente.putExtra(TEACHER_ATTENDANCE_EXTRA_P2, n2);
        siguiente.putExtra(TEACHER_ATTENDANCE_EXTRA_P3, n3);
        startActivity(siguiente);
    }

    @Deprecated
    public void MoverATeacherAMessages(){
        Intent siguiente = new Intent(this, TeacherMessagesActivity.class);
        startActivity(siguiente);
    }

    public void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.teacher_dlg_error_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public void askForMessagesOffline() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.teacher_messages_offline1)
                .setMessage(R.string.teacher_messages_offline2)
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Obtener cursos y horarios sin conexión
                                //Bundle extras = getIntent().getExtras();
                                //int msjeId = extras.getInt(TeacherMessagesActivity.TEACHER_MSJEID);

                                mPresenter.messagesOffline(1);
                            }
                        })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

}
