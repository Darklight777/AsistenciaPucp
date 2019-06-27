package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.attendance.TeacherAttendanceActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.messages.TeacherMessagesActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token.TeacherTokenActivity;

public class TeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, PrincipalActivity.class);
        startActivity(anterior);
    }
    public void MoverAGenerarToken(View view){
        Intent siguiente = new Intent(this, TeacherTokenActivity.class);
        startActivity(siguiente);
    }
    public void MoverATeacherAttendance(View view){
        Intent siguiente = new Intent(this, TeacherAttendanceActivity.class);
        startActivity(siguiente);
    }
    public void MoverATeacherAMessages(View view){
        Intent siguiente = new Intent(this, TeacherMessagesActivity.class);
        startActivity(siguiente);
    }
}
