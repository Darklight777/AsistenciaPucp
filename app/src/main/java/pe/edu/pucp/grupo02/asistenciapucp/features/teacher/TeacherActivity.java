package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.home.HomeActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;

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
}
