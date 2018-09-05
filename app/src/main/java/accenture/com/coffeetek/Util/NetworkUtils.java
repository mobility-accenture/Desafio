package accenture.com.coffeetek.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import accenture.com.coffeetek.R;

public class NetworkUtils {

    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                    && networkInfo.getState() == NetworkInfo.State.CONNECTED) {

                return TYPE_WIFI;

            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                    && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return TYPE_MOBILE;
            }
        }
        return TYPE_NOT_CONNECTED;
    }

    public static boolean isNetworkConnected(Context context) {
        int networkStatus = getConnectivityStatus(context);
        if (networkStatus == TYPE_WIFI || networkStatus == TYPE_MOBILE) {
            return true;
        } else {
            return false;
        }
    }


    public static void checkConection(final Context context, final Activity activity){

        if(!NetworkUtils.isNetworkConnected(context)){

            AlertDialog.Builder builder = new AlertDialog.Builder( context );
            if (context.getResources() != null){
                builder.setTitle( context.getResources().getString(R.string.alert_title_text) );
                builder.setMessage( context.getResources().getString(R.string.alert_message_text) );
            }

            builder.setPositiveButton(context.getResources().getString(R.string.ok_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    if(!NetworkUtils.isNetworkConnected(context)){
                        activity.finish();
                        System.exit(0);
                    }


                }
            });
            builder.create().show();

        }

    }

}
