package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.IStudentView;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.messages.StudentMessagesActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;


public class TeacherTokenActivity extends AppCompatActivity implements ITeacherTokenView {

    private final static String TAG = "AP_TEACHER_TOKEN_VIEW";

    private TextView mCourseName;
    private TextView mCourseSchedule;
    private TextView mCourseTime;
    private TextView mToken;
    private Switch mSwt; // = (Switch) findViewById(R.id.teacherToken_swt_enable);
    private ITeacherTokenPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_token);


        mCourseName = super.findViewById(R.id.teacherToken_txt_courseName);
        mCourseSchedule = super.findViewById(R.id.teacherToken_txt_schedule);
        mCourseTime = super.findViewById(R.id.teacherToken_txt_time);
        mToken = this.findViewById(R.id.teacherToken_txt_token);
        mSwt = this.findViewById(R.id.teacherToken_swt_token);
        mPresenter = new TeacherTokenPresenter(this);

        showInfo();

    }
    public void generateToken(View view){

        //int random = new  Random().nextInt(10000) + 10000;
        //((TextView) findViewById(R.id.teacherToken_txt_token)).setText(String.valueOf(random));
        Utilities.hideKeyboard(this);

        //datos de entrada para verificacion:
        if (mPresenter.verifyTokenData( mCourseName.getText().toString(), mCourseSchedule.getText().toString(),
                mCourseTime.getText().toString())){
            mPresenter.tokenRest( mCourseName.getText().toString(), mCourseSchedule.getText().toString(),
                    mCourseTime.getText().toString());
            Utilities.showMessage(this,R.string.teacher_msg_loading);
        }
    }

    public void showToken(String token)
    {
        mToken.setText(token);
    }

    /*
    public  void swtOnClick(View view){
        if(mSwt.isChecked()){
            generateToken();
        }


    }*/


    private void showInfo(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCourseName.setText(extras.getString(TeacherActivity.TEACHER_TOKEN_EXTRA_COURSENAME));
            mCourseSchedule.setText(extras.getString(TeacherActivity.TEACHER_TOKEN_EXTRA_COURSESCH));
            mCourseTime.setText(extras.getString(TeacherActivity.TEACHER_TOKEN_EXTRA_COURSETIME));
        }
    }

    public void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.teacher_dlg_error_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, TeacherActivity.class);
        startActivity(anterior);
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
