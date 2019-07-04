package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.messages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.StudentActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;

public class TeacherMessagesActivity extends AppCompatActivity {

    private final static String TAG = "AP_TEACHER_ATTENDANCE_VIEW";


    private Spinner spinner1, spinner2;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_messages);

        spinner2 = (Spinner) findViewById(R.id.teacherMessages_spn_course);
        spinner1 = (Spinner) findViewById(R.id.teacherMessages_spn_schedule);

        List<String> list1 = new ArrayList<String>();

        showCursos(list1);

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> list2 = new ArrayList<String>();

        showHorarios(list2);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list2);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(dataAdapter1);
        spinner1.setAdapter(dataAdapter2);
    }

    private void showCursos(List<String> list) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener los mensajes enviados por el Intent
            String curso1 = extras.getString(TeacherActivity.TEACHER_COURSE1);
            String curso2 = extras.getString(TeacherActivity.TEACHER_COURSE2);
            String curso3 = extras.getString(TeacherActivity.TEACHER_COURSE3);
            // Colocar los valores en el Spinner
            list.add(curso1);
            list.add(curso2);
            list.add(curso3);
        }
    }

    private void showHorarios(List<String> list) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener los mensajes enviados por el Intent
            String horario1[] = extras.getStringArray(TeacherActivity.TEACHER_SCHEDULE1);
            // Colocar los valores en el Spinner
            list.add(horario1[0]);
            list.add(horario1[1]);
            list.add(horario1[2]);
        }
    }

    public void retroceder(View view){
        Intent anterior = new Intent(this, TeacherActivity.class);
        startActivity(anterior);
    }



}
