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

    public final static String TEACHER_EXTRA_COURSENAME = "TEACHER_EXTRA_COURSENAME";
    public final static String TEACHER_EXTRA_COURSETIME = "TEACHER_EXTRA_COURSETIME";
    public final static String TEACHER_EXTRA_COURSESCH = "TEACHER_EXTRA_COURSESCH";

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

           String name = "Nombre del curso";
           String sch = "Schedule";
           String time = "Time 0:00";
           GenerarToken(name,sch,time);

    }

    public void GenerarToken(String name, String sch, String time){
        Intent siguiente = new Intent(this, TeacherTokenActivity.class);
        siguiente.putExtra(TEACHER_EXTRA_COURSENAME, name);
        siguiente.putExtra(TEACHER_EXTRA_COURSESCH, sch);
        siguiente.putExtra(TEACHER_EXTRA_COURSETIME, time);
        startActivity(siguiente);
        //finish();
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
