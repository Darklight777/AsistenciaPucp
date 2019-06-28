package pe.edu.pucp.grupo02.asistenciapucp.features.student.messages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.principal.PrincipalActivity;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.StudentActivity;

public class StudentMessagesActivity extends AppCompatActivity {

    private TextView mMsje1;
    private TextView mMsje2;
    private TextView mMsje3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_messages);

        mMsje1 = super.findViewById(R.id.student_msje1);
        mMsje2 = super.findViewById(R.id.student_msje2);
        mMsje3 = super.findViewById(R.id.student_msje3);

        showUserDetails();
    }

    private void showUserDetails() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener los mensajes enviados por el Intent
            String mensaje1 = extras.getString(StudentActivity.STUDENT_MSJE1);
            String mensaje2 = extras.getString(StudentActivity.STUDENT_MSJE2);
            String mensaje3 = extras.getString(StudentActivity.STUDENT_MSJE3);
            // Colocar los valores en el TextView
            mMsje1.setText(mensaje1);
            mMsje2.setText(mensaje2);
            mMsje3.setText(mensaje3);
        }
    }

    public void retroceder(View view){
        Intent anterior = new Intent(this, PrincipalActivity.class);
        startActivity(anterior);
    }
}
