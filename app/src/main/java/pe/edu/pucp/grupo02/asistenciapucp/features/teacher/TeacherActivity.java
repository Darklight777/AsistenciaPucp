package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.internal.Util;
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
            Utilities.showMessage(this, R.string.teacher_msg_loading);
        }
    }

    public void attendance(View v)
    {
        Utilities.hideKeyboard(this);

        //datos de entrada para verificacion:
        if (mPresenter.verifyAttendaceData()){
            mPresenter.attendanceRest();
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

    public void MoverATeacherAttendance(){//String n1, String n2, String n3){
        Intent siguiente = new Intent(this, TeacherAttendanceActivity.class);
        startActivity(siguiente);
    }
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
