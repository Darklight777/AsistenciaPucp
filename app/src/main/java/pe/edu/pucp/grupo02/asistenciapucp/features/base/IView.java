package pe.edu.pucp.grupo02.asistenciapucp.features.base;

import android.content.Context;

public interface IView {

    Context getContext();

    void onDestroy();
}
