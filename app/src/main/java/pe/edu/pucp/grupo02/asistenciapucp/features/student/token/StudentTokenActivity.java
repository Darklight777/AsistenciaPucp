package pe.edu.pucp.grupo02.asistenciapucp.features.student.token;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.features.student.StudentActivity;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;

public class StudentTokenActivity extends AppCompatActivity {

    private TextView mCourseName;
    private TextView mCourseSchedule;
    private TextView mCourseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_token);

        mCourseName = super.findViewById(R.id.studentToken_txt_courseName);
        mCourseSchedule = super.findViewById(R.id.lbl_student_token_horario);
        mCourseTime = super.findViewById(R.id.lbl_student_token_hora);

        showInfo();
    }

    /*
    public void btnOnClick(View view){

        //int random = new  Random().nextInt(10000) + 10000;
        //((TextView) findViewById(R.id.teacherToken_txt_token)).setText(String.valueOf(random));
        Utilities.hideKeyboard(this);

        //datos de entrada para verificacion:
        if (mPresenter.verifyTokenData( mCourseName.getText().toString(), mCourseSchedule.getText().toString(),
                mCourseTime.getText().toString())){
            mPresenter.tokenRest( mCourseName.getText().toString(), mCourseSchedule.getText().toString(),
                    mCourseTime.getText().toString());
        }

    }

    public void showTokenStudent(String token)
    {
        mToken.setText(token);
    }*/

    private void showInfo(){
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtener los mensajes enviados por el Intent
            String curso = extras.getString(StudentActivity.STUDENT_TOKEN_EXTRA_COURSENAME);
            String horario = extras.getString(StudentActivity.STUDENT_TOKEN_EXTRA_COURSESCH);
            String hora = extras.getString(StudentActivity.STUDENT_TOKEN_EXTRA_COURSETIME);
            // Colocar los valores en el TextView
            mCourseName.setText(curso);
            mCourseSchedule.setText(horario);
            mCourseTime.setText(hora);
        }
    }


    public void retroceder(View view){
        Intent anterior = new Intent(this, StudentActivity.class);
        startActivity(anterior);
    }

    public void marcarAsistencia(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Enhorabuena!")
                .setMessage("Asistencia marcada")
                .setPositiveButton(android.R.string.ok, null)
                .show();

        retroceder(view);
    }

}
