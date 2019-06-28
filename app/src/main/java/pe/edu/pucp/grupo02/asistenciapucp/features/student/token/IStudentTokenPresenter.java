package pe.edu.pucp.grupo02.asistenciapucp.features.student.token;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IPresenter;

public interface IStudentTokenPresenter extends IPresenter {

    boolean verifyTokenData(String courseN, String courseS, String courseT);

    void tokenRest(String courseN, String courseS, String courseT);
}
