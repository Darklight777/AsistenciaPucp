package pe.edu.pucp.grupo02.asistenciapucp.features.principal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.StudentActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.TeacherActivity;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }
    public void MoverAStudent(View view){
        Intent siguiente = new Intent(this, StudentActivity.class);
        startActivity(siguiente);
    }
    public void MoverATeacher(View view){
        Intent siguiente = new Intent(this, TeacherActivity.class);
        startActivity(siguiente);
    }
}
