package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.messages.StudentMessagesActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;


public class TeacherTokenActivity extends AppCompatActivity {

    private TextView mCourseName;
    private TextView mCourseSchedule;
    private TextView mCourseTime;
    private Switch swt; // = (Switch) findViewById(R.id.teacherToken_swt_enable);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_token);


        mCourseName = super.findViewById(R.id.teacherToken_txt_courseName);
        mCourseSchedule = super.findViewById(R.id.teacherToken_txt_schedule);
        mCourseTime = super.findViewById(R.id.teacherToken_txt_time);


        showInfo();

    }
    public void generateToken(View view){

        int random = new  Random().nextInt(10000) + 10000;
        ((TextView) findViewById(R.id.teacherToken_txt_token)).setText(String.valueOf(random));
    }

    public  void swtOnClick(View view){

    }


    private void showInfo(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mCourseName.setText(extras.getString(TeacherActivity.TEACHER_EXTRA_COURSENAME));
            mCourseSchedule.setText(extras.getString(TeacherActivity.TEACHER_EXTRA_COURSESCH));
            mCourseTime.setText(extras.getString(TeacherActivity.TEACHER_EXTRA_COURSETIME));
        }
    }

}
