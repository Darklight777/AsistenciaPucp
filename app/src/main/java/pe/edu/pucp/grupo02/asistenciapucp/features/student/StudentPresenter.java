package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import java.net.UnknownHostException;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.ApiAdapter;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.in.LoginInRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.StudentAttendanceOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.StudentMessagesOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.StudentTokenOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPresenter implements IStudentPresenter{

    private final static String TAG = "AP_STUDENT_PRESENTER";
    private IStudentView view;

    public StudentPresenter(IStudentView view) {
        this.view = view;
    }

    public boolean verifyAttendanceData(){
        return true;
    }

    public boolean verifyTokenData(){
        return true;
    }

    public boolean verifyMessagesData(){
        return true;
    }

    public void tokenRest() {
        LoginInRO loginInRO = new LoginInRO(ApiAdapter.APPLICATION_NAME, "o", "o");
        Call<StudentTokenOutRO> call = ApiAdapter.getInstance().token(loginInRO);
        call.enqueue(new Callback<StudentTokenOutRO>() {
            @Override
            public void onResponse(@NonNull Call<StudentTokenOutRO> call, @NonNull Response<StudentTokenOutRO> response) {
                processTokenResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<StudentTokenOutRO> call, @NonNull Throwable t) {
                if (t instanceof UnknownHostException) {
                    // No se encontró la URL, preguntar si se desea iniciar sesión
                    // sin conexión
                    view.askForMessagesOffline();
                } else {
                    // Mostrar mensaje de error en el logcat y en un cuadro de diálogo
                    t.printStackTrace();
                    view.showErrorDialog(t.getMessage());
                }
            }
        });
    }

    public void anunciosRest() {
        LoginInRO loginInRO = new LoginInRO(ApiAdapter.APPLICATION_NAME, "o", "o");
        Call<StudentMessagesOutRO> call = ApiAdapter.getInstance().announcements(loginInRO);
        call.enqueue(new Callback<StudentMessagesOutRO>() {
            @Override
            public void onResponse(@NonNull Call<StudentMessagesOutRO> call, @NonNull Response<StudentMessagesOutRO> response) {
                processAnunciosResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<StudentMessagesOutRO> call, @NonNull Throwable t) {
                if (t instanceof UnknownHostException) {
                    // No se encontró la URL, preguntar si se desea iniciar sesión
                    // sin conexión
                    view.askForMessagesOffline();
                } else {
                    // Mostrar mensaje de error en el logcat y en un cuadro de diálogo
                    t.printStackTrace();
                    view.showErrorDialog(t.getMessage());
                }
            }
        });
    }

    public void asistenciaRest() {
        LoginInRO loginInRO = new LoginInRO(ApiAdapter.APPLICATION_NAME, "o", "o");
        Call<StudentAttendanceOutRO> call = ApiAdapter.getInstance().attendance(loginInRO);
        call.enqueue(new Callback<StudentAttendanceOutRO>() {
            @Override
            public void onResponse(@NonNull Call<StudentAttendanceOutRO> call, @NonNull Response<StudentAttendanceOutRO> response) {
                processAsistenciaResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<StudentAttendanceOutRO> call, @NonNull Throwable t) {
                if (t instanceof UnknownHostException) {
                    // No se encontró la URL, preguntar si se desea iniciar sesión
                    // sin conexión
                    view.askForMessagesOffline();
                } else {
                    // Mostrar mensaje de error en el logcat y en un cuadro de diálogo
                    t.printStackTrace();
                    view.showErrorDialog(t.getMessage());
                }
            }
        });
    }

    private void processTokenResponse(Response<StudentTokenOutRO> response) {
        // Verificar respuesta del servidor REST
        Pair<StudentTokenOutRO, String> result = validateTokenResponse(response);
        if (result.first == null) {
            // Mostrar mensaje de error
            view.showErrorDialog(result.second);
        } else {
            // Obtener el objeto JSON
            StudentTokenOutRO studentTokenOutRO = result.first;
            // Ir a la pantalla de mensajes
            view.gotoIngresarToken(studentTokenOutRO.getCurso(), studentTokenOutRO.getHorario(), studentTokenOutRO.getHora());
        }
    }

    private void processAnunciosResponse(Response<StudentMessagesOutRO> response) {
        // Verificar respuesta del servidor REST
        Pair<StudentMessagesOutRO, String> result = validateAnunciosResponse(response);
        if (result.first == null) {
            // Mostrar mensaje de error
            view.showErrorDialog(result.second);
        } else {
            // Obtener el objeto JSON
            StudentMessagesOutRO studentMessagesRO = result.first;
            // Ir a la pantalla de mensajes
            view.gotoStudentMessages(studentMessagesRO.getMessage1(), studentMessagesRO.getMessage2(), studentMessagesRO.getMessage3());
        }
    }

    private void processAsistenciaResponse(Response<StudentAttendanceOutRO> response) {
        // Verificar respuesta del servidor REST
        Pair<StudentAttendanceOutRO, String> result = validateAttendanceResponse(response);
        if (result.first == null) {
            // Mostrar mensaje de error
            view.showErrorDialog(result.second);
        } else {
            // Obtener el objeto JSON
            StudentAttendanceOutRO studentAttendanceRO = result.first;
            // Ir a la pantalla de mensajes
            view.gotoStudentAttendance(studentAttendanceRO.getPorce1(), studentAttendanceRO.getPorce2(), studentAttendanceRO.getPorce3());
        }
    }

    private Pair<StudentTokenOutRO, String> validateTokenResponse(Response<StudentTokenOutRO> response) {
        Context context = view.getContext();
        // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            String message = Utilities.formatString(context, R.string.api_dlg_error_msg_http, response.code());
            return new Pair<>(null, message);
        }
        // Verificar el contenido de la respuesta en JSON
        StudentTokenOutRO studentTokenOutRO = response.body();
        if (studentTokenOutRO == null) {
            String message = Utilities.formatString(context, R.string.api_dlg_error_msg_empty);
            return new Pair<>(null, message);
        }
        // Verificar que la respuesta no indique un error
        int errorCode = studentTokenOutRO.getErrorCode();
        String message = studentTokenOutRO.getMessage();
        if (errorCode == 0) {
            return new Pair<>(studentTokenOutRO, message); // Respuesta sin errores
        }
        // Verificar que el mensaje de error no está vacío
        if (message == null || message.isEmpty()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest, errorCode);
        }
        return new Pair<>(null, message);
    }

    private Pair<StudentMessagesOutRO, String> validateAnunciosResponse(Response<StudentMessagesOutRO> response) {
        Context context = view.getContext();
        // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            String message = Utilities.formatString(context, R.string.api_dlg_error_msg_http, response.code());
            return new Pair<>(null, message);
        }
        // Verificar el contenido de la respuesta en JSON
        StudentMessagesOutRO studentMessagesRO = response.body();
        if (studentMessagesRO == null) {
            String message = Utilities.formatString(context, R.string.api_dlg_error_msg_empty);
            return new Pair<>(null, message);
        }
        // Verificar que la respuesta no indique un error
        int errorCode = studentMessagesRO.getErrorCode();
        String message = studentMessagesRO.getMessage();
        if (errorCode == 0) {
            return new Pair<>(studentMessagesRO, message); // Respuesta sin errores
        }
        // Verificar que el mensaje de error no está vacío
        if (message == null || message.isEmpty()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest, errorCode);
        }
        return new Pair<>(null, message);
    }

    private Pair<StudentAttendanceOutRO, String> validateAttendanceResponse(Response<StudentAttendanceOutRO> response) {
        Context context = view.getContext();
        // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            String message = Utilities.formatString(context, R.string.api_dlg_error_msg_http, response.code());
            return new Pair<>(null, message);
        }
        // Verificar el contenido de la respuesta en JSON
        StudentAttendanceOutRO studentAttendanceRO = response.body();
        if (studentAttendanceRO == null) {
            String message = Utilities.formatString(context, R.string.api_dlg_error_msg_empty);
            return new Pair<>(null, message);
        }
        // Verificar que la respuesta no indique un error
        int errorCode = studentAttendanceRO.getErrorCode();
        String message = studentAttendanceRO.getMessage();
        if (errorCode == 0) {
            return new Pair<>(studentAttendanceRO, message); // Respuesta sin errores
        }
        // Verificar que el mensaje de error no está vacío
        if (message == null || message.isEmpty()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest, errorCode);
        }
        return new Pair<>(null, message);
    }

    @Override
    public void messagesOffline() {
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
