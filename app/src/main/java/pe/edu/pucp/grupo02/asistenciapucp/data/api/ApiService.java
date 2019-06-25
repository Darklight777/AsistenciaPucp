package pe.edu.pucp.grupo02.asistenciapucp.data.api;

import pe.edu.pucp.grupo02.asistenciapucp.data.api.in.LoginInRO;
import pe.edu.pucp.grupo02.asistenciapucp.data.api.out.UserOutRO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("user/login")
    Call<UserOutRO> login(@Body LoginInRO user);
}
