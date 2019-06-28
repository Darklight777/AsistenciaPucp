package pe.edu.pucp.grupo02.asistenciapucp.features.student.token;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;

import java.net.UnknownHostException;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.ApiAdapter;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.in.GenerateTokenInRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.GenerateTokenOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token.ITeacherTokenView;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentTokenPresenter implements IStudentTokenPresenter {

    private final static String TAG = "AP_TEACHER_PRESENTER";
    private ITeacherTokenView view;

    public StudentTokenPresenter(ITeacherTokenView view){this.view = view;}

    //No hay reestriccion;
    public boolean verifyTokenData(String s1,String s2,String s3){
        return true;
    }

    public void tokenRest(final String courseN, final String courseS, final String courseT)
    {
        GenerateTokenInRO generateTokenInRO = new GenerateTokenInRO(ApiAdapter.APPLICATION_NAME,
                courseN, courseS, courseT);
        Call<GenerateTokenOutRO> call = ApiAdapter.getInstance().gToken(generateTokenInRO);
        call.enqueue(new Callback<GenerateTokenOutRO>() {
            @Override
            public void onResponse(@NonNull Call<GenerateTokenOutRO> call, @NonNull Response<GenerateTokenOutRO> response) {
                processGenerateTokenResponse(response, courseN, courseS, courseT);
            }

            @Override
            public void onFailure(@NonNull Call<GenerateTokenOutRO> call, @NonNull Throwable t) {
                if (t instanceof UnknownHostException) {
                    // No se encontró la URL, preguntar si se desea iniciar sesión
                    // sin conexión
                    //view.askForLoginOffline();
                } else {
                    // Mostrar mensaje de error en el logcat y en un cuadro de diálogo
                    t.printStackTrace();
                    view.showErrorDialog(t.getMessage());
                }
            }
        });

    }







    private void processGenerateTokenResponse(Response<GenerateTokenOutRO> response, String courseN,
                                              String courseS, String courseT ){
        // Verificar respuesta del servidor REST
        Pair<GenerateTokenOutRO, String> result = validateResponse(response);
        if (result.first == null) {
            // Mostrar mensaje de error
            view.showErrorDialog(result.second);
        } else {
            // Obtener el objeto JSON
            GenerateTokenOutRO generateTokenOutRO = result.first;
            // Guardar los datos del usuario en la base de datos
            //new UserSaveTask(view, username, password, userOutRO).execute();
            // Ir a la pantalla de bienvenida
            view.showToken(generateTokenOutRO.getToken());
        }
    }

    private Pair<GenerateTokenOutRO, String> validateResponse(Response<GenerateTokenOutRO> response) {
        Context context = view.getContext();
// Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_http, response.code());
            return new Pair<>(null, message);
        }
// Verificar el contenido de la respuesta en JSON
        GenerateTokenOutRO generateTokenOutRO = response.body();
        if (generateTokenOutRO == null) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_empty);
            return new Pair<>(null, message);
        }
// Verificar que la respuesta no indique un error
        int errorCode = generateTokenOutRO.getErrorCode();
        String message = generateTokenOutRO.getMessage();
        if (errorCode == 0) {
            return new Pair<>(generateTokenOutRO, message); // Respuesta sin errores
        }
// Verificar que el mensaje de error no está vacío
        if (message == null || message.isEmpty()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest,
                    errorCode);
        }
        return new Pair<>(null, message);
    }


    @Override
    public void onDestroy() {
        view = null;
    }
}
