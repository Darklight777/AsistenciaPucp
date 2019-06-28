package pe.edu.pucp.grupo02.asistenciapucp.features.student.token;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.StudentActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token.ITeacherTokenPresenter;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token.TeacherTokenPresenter;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;

public class StudentTokenActivity extends AppCompatActivity {

    private final static String TAG = "AP_STUDENT_TOKEN_VIEW";

    private TextView mCourseName;
    private TextView mCourseSchedule;
    private TextView mCourseTime;
    private TextView mToken;
    private IStudentTokenPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_token);

        mCourseName = super.findViewById(R.id.studentToken_txt_courseName);
        mCourseSchedule = super.findViewById(R.id.studentToken_txt_schedule);
        mCourseTime = super.findViewById(R.id.studentToken_txt_time);
        mToken = this.findViewById(R.id.studentToken_txt_token);
        mPresenter = new StudentTokenPresenter(this);
        showInfo();
    }

    public void btnOnClick(View view){

        //int random = new  Random().nextInt(10000) + 10000;
        //((TextView) findViewById(R.id.teacherToken_txt_token)).setText(String.valueOf(random));
        Utilities.hideKeyboard(this);

        //datos de entrada para verificacion:
        if (mPresenter.verifyTokenData( mCourseName.getText().toString(), mCourseSchedule.getText().toString(),
                mCourseTime.getText().toString())){
            mPresenter.tokenRest( mCourseName.getText().toString(), mCourseSchedule.getText().toString(),
                    mCourseTime.getText().toString());
        }

    }


    private void showInfo(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCourseName.setText(extras.getString(StudentActivity.STUDENT_TOKEN_EXTRA_COURSENAME));
            mCourseSchedule.setText(extras.getString(StudentActivity.STUDENT_TOKEN_EXTRA_COURSESCH));
            mCourseTime.setText(extras.getString(StudentActivity.STUDENT_TOKEN_EXTRA_COURSETIME));
        }
    }


    public void retroceder(View view){
        Intent anterior = new Intent(this, StudentActivity.class);
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
