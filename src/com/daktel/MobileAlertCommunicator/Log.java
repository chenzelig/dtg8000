package com.daktel.MobileAlertCommunicator;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

import android.os.Environment;

public class Log {
	
	public static final String APP_DIR = Environment.getExternalStorageDirectory() + "/DTG8000/";
	public static final boolean debug = true;
	public static void i(String tag, String msg){
		android.util.Log.i(tag, msg);
		if(debug){
			FileUtils.writeTextToFile(APP_DIR + "debug.txt", new Date().toString() + "\tINFO:\t" + msg + "\r\n", true);
		}
	}
	
	public static void d(String tag, String msg){
		android.util.Log.d(tag, msg);
		if(debug){
			FileUtils.writeTextToFile(APP_DIR + "debug.txt", new Date().toString() + "\tDEBUG:\t" + msg + "\r\n", true);
		}
	}
	
	public static void w(String tag, String msg){
		android.util.Log.w(tag, msg);
		if(debug){
			FileUtils.writeTextToFile(APP_DIR + "debug.txt", new Date().toString() + "\tWARING:\t" + msg + "\r\n", true);
		}
	}

	
	public static void e(String tag, String msg){
		android.util.Log.e(tag, msg);
		if(debug){
			FileUtils.writeTextToFile(APP_DIR + "debug.txt", new Date().toString() + "\tERROR:\t" + msg + "\r\n", true);
		}
	}
	
	public static void e(String tag, String msg, Throwable e){
		android.util.Log.e(tag, msg, e);
		if(debug){
			PrintStream ps = null;
			try {
				ps = new PrintStream(APP_DIR + "debug.txt");
				ps.println(new Date().toString() + "\tERROR:\t" + msg + "\r\n");
				e.printStackTrace(ps);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				if(ps != null){
					ps.close();
				}
			}
			//FileUtils.writeTextToFile(APP_DIR + "debug.log", new Date().toString() + "\tERROR:\t" + msg + "\r\n" + e, true);
		}
	}
}
