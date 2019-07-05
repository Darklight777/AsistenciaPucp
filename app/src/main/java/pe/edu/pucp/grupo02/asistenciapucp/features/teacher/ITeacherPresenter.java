package pe.edu.pucp.grupo02.asistenciapucp.features.teacher;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IPresenter;

public interface ITeacherPresenter extends IPresenter {

    boolean verifyAttendaceData();

    boolean verifyMessagesData();

    boolean verifyTokenData(String s);


    void attendanceRest(String username);

    void messagesRest();

    void tokenRest(String s);

    void messagesOffline(int msjeid);
}
