package android.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Mani
 * Date: 27/11/2013
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class SharedPreferencesManager {

    /*
    # write properties from shared preferences:
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString("lanecount", "10001000");
    editor.putString("time", "12:00");
    editor.commit();
    */
    public static void storeSharedPreferences(Context ctx, Properties properties){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("lanecount", "10001000");
        editor.putString("time", "12:00");
        editor.commit();

    }

    /*
    # Read properties from shared preferences:
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    String lanecount = prefs.getString("lanecount", "defauleValue");
    String time = prefs.getString("time", "defauleValue");
     */
    public static Properties readSharedPreferences(Context ctx, Properties properties){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        String lanecount = prefs.getString("lanecount", "defauleValue");
        String time = prefs.getString("time", "defauleValue");
        return new Properties();
    }

}
