package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.messages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;

public class TeacherMessagesActivity extends AppCompatActivity {

    private final static String TAG = "AP_TEACHER_ATTENDANCE_VIEW";

    private TextView mCourse1;
    private TextView mCourse2;
    private TextView mCourse3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_messages);




        //showInfo();
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, TeacherActivity.class);
        startActivity(anterior);
    }
/*
    private void showInfo()
    {
        String test = "12";
        mCourse3.setText(test);
        mCourse2.setText(test);
        mCourse1.setText(test);

        Bundle extras = getIntent().getExtras();

        if (false){//extras != null) {

            //String c1 = extras.getString(TeacherActivity.TEACHER_ATTENDANCE_EXTRA_P1);
            //String c2 = extras.getString(TeacherActivity.TEACHER_ATTENDANCE_EXTRA_P2);
            //String c3 = extras.getString(TeacherActivity.TEACHER_ATTENDANCE_EXTRA_P3);

            mCourse1.setText("");
            mCourse2.setText(c2);
            mCourse3.setText(c3);

        }
    }*/


}
