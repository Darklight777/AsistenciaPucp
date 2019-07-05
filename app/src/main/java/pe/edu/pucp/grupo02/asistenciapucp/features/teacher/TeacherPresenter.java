package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;

import java.net.UnknownHostException;

import pe.edu.pucp.grupo02.asistenciapucp.R;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.ApiAdapter;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.in.LoginInRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.in.TeacherAttendanceInRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.in.TeacherTokenInRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.TeacherAttendanceOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.TeacherMessagesOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.TeacherTokenOutRO;
import pe.edu.pucp.grupo02.asistenciapucp.utils.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherPresenter implements ITeacherPresenter {

    private final static String TAG = "AP_TEACHER_PRESENTER";
    private ITeacherView view;

    public TeacherPresenter(ITeacherView view){this.view = view; }

    public boolean verifyAttendaceData(){
        return true;
    }

    public boolean verifyTokenData(String s){
        return true;
    }

    public boolean verifyMessagesData(){
        return true;
    }

    public void tokenRest(final String date) {
        TeacherTokenInRO teacherTokenInRO = new TeacherTokenInRO(ApiAdapter.APPLICATION_NAME, date);
        Call<TeacherTokenOutRO> call = ApiAdapter.getInstance().token(teacherTokenInRO);
        call.enqueue(new Callback<TeacherTokenOutRO>() {
            @Override
            public void onResponse(@NonNull Call<TeacherTokenOutRO> call, @NonNull Response<TeacherTokenOutRO> response) {
                processTeacherTokenResponse(response, date);
            }

            @Override
            public void onFailure( @NonNull Call<TeacherTokenOutRO> call, @NonNull  Throwable t) {
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

    private void processTeacherTokenResponse(Response<TeacherTokenOutRO> response, String date) {
            // Verificar respuesta del servidor REST
        Pair<TeacherTokenOutRO, String> result = validateResponse(response);
        if (result.first == null) {
            // Mostrar mensaje de error
            view.showErrorDialog(result.second);
        } else {
            // Obtener el objeto JSON
            TeacherTokenOutRO teacherTokenOutRO = result.first;
            // Guardar los datos del usuario en la base de datos
            //new UserSaveTask(view, date, teacherTokenOutRO).execute();
            // Ir a la pantalla de bienvenida
            view.MoverAGenerarToken(teacherTokenOutRO.getCourseName(), teacherTokenOutRO.getCourseSchedule(),
                                    teacherTokenOutRO.getCourseTime());
        }
    }

    private Pair<TeacherTokenOutRO, String> validateResponse(Response<TeacherTokenOutRO> response) {
        Context context = view.getContext();
            // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_http, response.code());
            return new Pair<>(null, message);
        }
            // Verificar el contenido de la respuesta en JSON
        TeacherTokenOutRO teacherTokenOutRO = response.body();
        if (teacherTokenOutRO == null) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_empty);
            return new Pair<>(null, message);
        }
            // Verificar que la respuesta no indique un error
        int errorCode = teacherTokenOutRO.getErrorCode();
        String message = teacherTokenOutRO.getMessage();
        if (errorCode == 0) {
            return new Pair<>(teacherTokenOutRO, message); // Respuesta sin errores
        }
            // Verificar que el mensaje de error no está vacío
        if (message == null || message.isEmpty()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest,
                    errorCode);
        }
        return new Pair<>(null, message);
    }


    public void messagesRest() {
        LoginInRO loginInRO = new LoginInRO(ApiAdapter.APPLICATION_NAME, "o", "o");
        Call<TeacherMessagesOutRO> call = ApiAdapter.getInstance().cursosHorarios(loginInRO);
        call.enqueue(new Callback<TeacherMessagesOutRO>() {
            @Override
            public void onResponse(@NonNull Call<TeacherMessagesOutRO> call, @NonNull Response<TeacherMessagesOutRO> response) {
                processMessagesResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<TeacherMessagesOutRO> call, @NonNull Throwable t) {
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

    private void processMessagesResponse(Response<TeacherMessagesOutRO> response) {
        // Verificar respuesta del servidor REST
        Pair<TeacherMessagesOutRO, String> result = validateMessagesResponse(response);
        if (result.first == null) {
            // Mostrar mensaje de error
            view.showErrorDialog(result.second);
        } else {
            // Obtener el objeto JSON
            TeacherMessagesOutRO teacherMessagesOutRO = result.first;

            // Guardar los datos del mensaje en la base de datos
            new TeacherCurHorSaveTask(view, teacherMessagesOutRO).execute();

            // Ir a la pantalla de mensajes
            view.gotoTeacherMessages(teacherMessagesOutRO.getMsjeId(), teacherMessagesOutRO.getCurso1(), teacherMessagesOutRO.getCurso2(), teacherMessagesOutRO.getCurso3(),
                    teacherMessagesOutRO.getHorarios1(), teacherMessagesOutRO.getHorarios2(), teacherMessagesOutRO.getHorarios3());
        }
    }

    public void attendanceRest(final String username){

        TeacherAttendanceInRO teacherAttendanceInRO = new TeacherAttendanceInRO(ApiAdapter.APPLICATION_NAME, username);
        Call<TeacherAttendanceOutRO> call = ApiAdapter.getInstance().attendance(teacherAttendanceInRO);
        call.enqueue(new Callback<TeacherAttendanceOutRO>() {
            @Override
            public void onResponse(@NonNull Call<TeacherAttendanceOutRO> call, @NonNull Response<TeacherAttendanceOutRO> response) {
                processTeacherAttendanceResponse(response, username);
            }

            @Override
            public void onFailure(@NonNull Call<TeacherAttendanceOutRO> call, @NonNull Throwable t) {
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

    private void processTeacherAttendanceResponse(Response<TeacherAttendanceOutRO> response, String username) {
        // Verificar respuesta del servidor REST
        Pair<TeacherAttendanceOutRO, String> result = validateResponseA(response);
        if (result.first == null) {
            // Mostrar mensaje de error
            view.showErrorDialog(result.second);
        } else {
            // Obtener el objeto JSON
            TeacherAttendanceOutRO teacherAttendanceOutRO = result.first;
            // Guardar los datos del usuario en la base de datos
            //new UserSaveTask(view, date, teacherTokenOutRO).execute();
            // Ir a la pantalla de bienvenida
            view.MoverATeacherAttendance(teacherAttendanceOutRO.getPorc1(),teacherAttendanceOutRO.getPorc2(),
                    teacherAttendanceOutRO.getPorc3());
        }
    }

    private Pair<TeacherAttendanceOutRO, String> validateResponseA(Response<TeacherAttendanceOutRO> response) {
        Context context = view.getContext();
        // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_http, response.code());
            return new Pair<>(null, message);
        }
        // Verificar el contenido de la respuesta en JSON
        TeacherAttendanceOutRO teacherAttendanceOutRO = response.body();
        if (teacherAttendanceOutRO == null) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_empty);
            return new Pair<>(null, message);
        }
        // Verificar que la respuesta no indique un error
        int errorCode = teacherAttendanceOutRO.getErrorCode();
        String message = teacherAttendanceOutRO.getMessage();
        if (errorCode == 0) {
            return new Pair<>(teacherAttendanceOutRO, message); // Respuesta sin errores
        }
        // Verificar que el mensaje de error no está vacío
        if (message == null || message.isEmpty()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest,
                    errorCode);
        }
        return new Pair<>(null, message);
    }

    private Pair<TeacherMessagesOutRO, String> validateMessagesResponse(Response<TeacherMessagesOutRO> response) {
        Context context = view.getContext();
        // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_http, response.code());
            return new Pair<>(null, message);
        }
        // Verificar el contenido de la respuesta en JSON
        TeacherMessagesOutRO teacherMessagesOutRO = response.body();
        if (teacherMessagesOutRO == null) {
            String message = Utilities.formatString(context,
                    R.string.api_dlg_error_msg_empty);
            return new Pair<>(null, message);
        }
        // Verificar que la respuesta no indique un error
        int errorCode = teacherMessagesOutRO.getErrorCode();
        String message = teacherMessagesOutRO.getMessage();
        if (errorCode == 0) {
            return new Pair<>(teacherMessagesOutRO, message); // Respuesta sin errores
        }
        // Verificar que el mensaje de error no está vacío
        if (message == null || message.isEmpty()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest,
                    errorCode);
        }
        return new Pair<>(null, message);
    }

    @Override
    public void messagesOffline(int msjeid) {
        new TeacherCurHorTask(view, msjeid).execute();
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
