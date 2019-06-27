package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.home.HomeActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.attendance.StudentAttendanceActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.messages.StudentMessagesActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.token.StudentTokenActivity;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, PrincipalActivity.class);
        startActivity(anterior);
    }
    public void MoverAIngresarToken(View view){
        Intent siguiente = new Intent(this, StudentTokenActivity.class);
        startActivity(siguiente);
    }
    public void MoverAStudentAttendance(View view){
        Intent siguiente = new Intent(this, StudentAttendanceActivity.class);
        startActivity(siguiente);
    }
    public void MoverAStudentAMessages(View view){
        Intent siguiente = new Intent(this, StudentMessagesActivity.class);
        startActivity(siguiente);
    }

}
