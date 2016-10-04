package com.daktel.MobileAlertCommunicator;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**数据保存类
 * Created by li on 2014/9/24.
 */
public class SharePreferenceUtil {


    public static void setPreference(Context context, String key,String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
        editor = null;
    }

    public static String getPreference(Context context,String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, "");
        return value;
    }

}
