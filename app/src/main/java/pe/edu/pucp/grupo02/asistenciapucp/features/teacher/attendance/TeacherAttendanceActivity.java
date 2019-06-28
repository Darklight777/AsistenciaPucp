package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.attendance;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;

public class TeacherAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance);
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, TeacherActivity.class);
        startActivity(anterior);
    }
}
