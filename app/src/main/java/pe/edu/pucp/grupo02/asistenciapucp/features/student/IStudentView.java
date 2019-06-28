package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IView;

public interface IStudentView extends IView {

    void askForMessagesOffline();

    void gotoStudentMessages(String msje1, String msje2, String msje3);

    void gotoStudentAttendance(String porce1, String porce2, String porce3);

    void showErrorDialog(String message);
}
