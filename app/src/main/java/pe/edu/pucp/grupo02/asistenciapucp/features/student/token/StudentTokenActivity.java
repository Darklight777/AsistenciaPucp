package pe.edu.pucp.grupo02.asistenciapucp.features.student.token;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.StudentActivity;

public class StudentTokenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_token);
    }
    public void retroceder(View view){
        Intent anterior = new Intent(this, StudentActivity.class);
        startActivity(anterior);
    }
}
