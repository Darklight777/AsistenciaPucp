package pe.edu.pucp.grupo02.asistenciapucp.features.student.token;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IPresenter;

public interface IStudentTokenPresenter extends IPresenter {

    void showToken(String token);

    void showErrorDialog(String message);
}
