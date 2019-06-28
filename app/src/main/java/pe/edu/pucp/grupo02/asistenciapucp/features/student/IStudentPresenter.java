package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IPresenter;

public interface IStudentPresenter extends IPresenter {

    boolean verifyMessagesData();

    boolean verifyTokenData();

    boolean verifyAttendanceData();

    void anunciosRest();

    void tokenRest();

    void asistenciaRest();

    void messagesOffline();
}
