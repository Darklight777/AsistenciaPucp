package pe.edu.pucp.grupo02.asistenciapucp.features.student.token;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IView;

public interface IStudentTokenView extends IView {

    void showTokenStudent(String token);

    void showErrorDialog(String message);

}
