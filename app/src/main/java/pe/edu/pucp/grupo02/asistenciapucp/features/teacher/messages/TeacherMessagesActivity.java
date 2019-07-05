package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.messages;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;

public class TeacherMessagesActivity extends AppCompatActivity {

    private final static String TAG = "AP_TEACHER_ATTENDANCE_VIEW";

    private Context mContext = this;
    private Spinner spinnerCur, spinnerHor;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_messages);


        spinnerCur = (Spinner) findViewById(R.id.teacherMessages_spn_course);
        spinnerHor = (Spinner) findViewById(R.id.teacherMessages_spn_schedule);

        List<String> list1 = new ArrayList<String>();

        showCursos(list1);

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCur.setAdapter(dataAdapter1);

        spinnerCur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);

                List<String> list2 = new ArrayList<String>();
                showHorarios(i, list2);

                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, list2);
                spinnerHor.setAdapter(dataAdapter2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Utilities.showMessage(mContext, "No selection");
            }
        });
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

    private void showHorarios(int pos, List<String> list) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener los mensajes enviados por el Intent

            String[] horario = {"0","0","0"};
            switch (pos) {
                case 0: {
                    horario = extras.getStringArray(TeacherActivity.TEACHER_SCHEDULE1);
                    break;
                }
                case 1: {
                    horario = extras.getStringArray(TeacherActivity.TEACHER_SCHEDULE2);
                    break;
                }
                case 2: {
                    horario = extras.getStringArray(TeacherActivity.TEACHER_SCHEDULE3);
                }
            }

            // Colocar los valores en el Spinner
            list.add(horario[0]);
            list.add(horario[1]);
            list.add(horario[2]);
        }
    }

    public void retroceder(View view){
        Intent anterior = new Intent(this, TeacherActivity.class);
        startActivity(anterior);
    }

}
