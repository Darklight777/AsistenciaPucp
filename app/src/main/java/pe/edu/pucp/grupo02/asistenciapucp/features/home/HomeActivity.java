package pe.edu.pucp.grupo02.asistenciapucp.features.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView mFullName;
    private TextView mEmail;
    private Button mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mFullName = super.findViewById(R.id.home_txt_fullname);
        mEmail = super.findViewById(R.id.home_txt_email);
        mLogout = super.findViewById(R.id.home_btn_logout);

        showUserDetails();
        initButtons();
    }

    private void showUserDetails() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener los valores enviados por el Intent
            String fullName = extras.getString(LoginActivity.LOGIN_EXTRA_FULLNAME);
            String email = extras.getString(LoginActivity.LOGIN_EXTRA_EMAIL);
            // Colocar los valores en el TextView
            mFullName.setText(fullName);
            mEmail.setText(email);
        }
    }

    private void initButtons() {
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });
    }

    public void logout(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
