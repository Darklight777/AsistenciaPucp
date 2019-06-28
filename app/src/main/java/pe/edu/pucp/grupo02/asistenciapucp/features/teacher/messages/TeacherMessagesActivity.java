package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.messages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;

public class TeacherMessagesActivity extends AppCompatActivity {

    private final static String TAG = "AP_TEACHER_ATTENDANCE_VIEW";

    private AppCompatSpinner mCourse;
    private AppCompatSpinner mSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_messages);

        mCourse = super.findViewById(R.id.teacherMessages_spn_course);
        mSchedule = super.findViewById(R.id.teacherMessages_spn_schedule);

        //showInfo();
    }

    public void retroceder(View view){
        Intent anterior = new Intent(this, TeacherActivity.class);
        startActivity(anterior);
    }

    /*
    private void showInfo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener los mensajes enviados por el Intent
            String curso = "Programación Móvil";
            String horario = "INF873";
            // Colocar los valores en el TextView
            mCourse.setText(curso);
            mSchedule.setText(horario);
        }
    }*/


}
