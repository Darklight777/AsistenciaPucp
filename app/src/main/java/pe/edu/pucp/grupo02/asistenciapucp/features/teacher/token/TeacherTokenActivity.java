package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.messages.StudentMessagesActivity;

public class TeacherTokenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_token);
    }

    public void generarToken(View view){
        int random = new  Random().nextInt(10000) + 10000;
        ((TextView) findViewById(R.id.teacherToken_txt_token)).setText(String.valueOf(random));
    }
}
