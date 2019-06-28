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

        mCourse1 = super.findViewById(R.id.teacherAttendance_txt_porcentaje1);
        mCourse2 = super.findViewById(R.id.teacherAttendance_txt_porcentaje2);
        mCourse3 = super.findViewById(R.id.teacherAttendance_txt_porcentaje3);


        showInfo();
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, TeacherActivity.class);
        startActivity(anterior);
    }

    private void showInfo()
    {
        mCourse3.setText("xd");
        mCourse2.setText("xd");
        mCourse1.setText("xd");

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            mCourse1.setText(extras.getString(TeacherActivity.TEACHER_ATTENDANCE_EXTRA_P1));
            mCourse2.setText(extras.getString(TeacherActivity.TEACHER_ATTENDANCE_EXTRA_P2));
            mCourse3.setText(extras.getString(TeacherActivity.TEACHER_ATTENDANCE_EXTRA_P3));

        }
    }


}
