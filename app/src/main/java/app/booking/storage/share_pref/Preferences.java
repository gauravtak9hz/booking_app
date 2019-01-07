package app.booking.storage.share_pref;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * Created by NineHertz-and-team on 5/1/19.
 */

//All the shared preference keys will be stored and retrieved using this class
public final class Preferences {

    private final static String PREF_TAG = "booking_app";

    public static void setPreferences(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    public static void setPreferences(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    public static void setPreferences(Context context, String key, Boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static void setPreferences(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public static String getPreferences(Context context, String key, String defValue) {


        return getPreferences(context).getString(key, defValue);

    }

    public static int getPreferences(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static float getPreferences(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static boolean isContains(Context context, String key) {
        return getPreferences(context).contains(key);
    }

    public static void remove(Context context, String key) {
        getEditor(context).remove(key).commit();
    }

    public static void clearAll(Context context) {
        getEditor(context).clear().commit();
    }

    private static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }



    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
    }

}

