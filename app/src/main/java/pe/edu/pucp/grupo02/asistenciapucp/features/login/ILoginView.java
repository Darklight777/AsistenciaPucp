package pe.edu.pucp.grupo02.asistenciapucp.features.login;

import pe.edu.pucp.grupo02.asistenciapucp.features.base.IView;

public interface ILoginView extends IView {

    void askForLoginOffline();

    void goToHomePage(String names, String email);

    void showErrorDialog(String message);
}
