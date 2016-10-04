package com.daktel.MobileAlertCommunicator;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Utility {

	
	public static String getShareValue(Context context,String key){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(key,"");
	}
	public static void setShareValue(Context context,String key, String value){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
		editor = null;
	}	
	
	
	private static Toast toast;
	/**
	 * 显示文本型Toast
	 * @param context
	 * @param text
	 * @param duration
	 */
	public static void showToast(Context context, CharSequence text, int duration){
		try{
			if(toast == null){
				toast = Toast.makeText(context, text, duration);
			}else{
				toast.setText(text);
				toast.setDuration(duration);
			}
			
			toast.show();
		}catch(RuntimeException e){
			
		}
	}
	/**
	 * 显示文本型Toast
	 * @param context
	 * @param StringId
	 * @param duration
	 */
	public static void showToast(Context context, int resId, int duration){
		showToast(context, context.getString(resId), duration);
	}	
	
}
