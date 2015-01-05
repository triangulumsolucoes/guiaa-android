package triangulum.com.guiaa.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by triangulum on 05/01/15.
 */
public class Utils {
    public static boolean verifyConection(Activity activity) {
        ConnectivityManager connectivity = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivity.getActiveNetworkInfo();


        if (network != null) {
            if (network.isAvailable() && network.isConnected()) {

                return true;
            } else {
                // Crouton.makeText(this, "Sem conexão", Style.ALERT).show();
                return false;
            }
        } else {
            // Crouton.makeText(this, "Sem conexão", Style.ALERT).show();
            return false;
        }
    }
}
