package pe.edu.pucp.grupo02.asistenciapucp.features.student;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IPresenter;

public interface IStudentPresenter extends IPresenter {

    boolean verifyMessagesData();

    void anunciosRest();

    void messagesOffline();
}
