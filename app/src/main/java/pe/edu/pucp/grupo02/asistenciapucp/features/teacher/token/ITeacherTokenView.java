package pe.edu.pucp.grupo02.asistenciapucp.features.teacher.token;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IView;

public interface ITeacherTokenView extends IView {


    void showToken(String token);

    void showErrorDialog(String message);
}
