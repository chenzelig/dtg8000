package com.daktel.MobileAlertCommunicator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 数据访问类基类
 * 负责维护数据库版本
 * 以及公用的方法
 * SQLITE不适于多线程情况使用，请尽量避免多线程写数据库
 * @author Administrator
 *
 */
public class DBFactory extends SQLiteOpenHelper {
	private static final String DataBase_Name = "thomson.db";
	//private String playListDefaultName = "Default Play List";
	private String[] playLists = null;
	public DBFactory(Context context) {
		super(context, DataBase_Name, null, 13);
		//playListDefaultName = context.getString(R.string.playlist_default_name);
		//playLists = context.getResources().getStringArray(com.senty.android.babystory.R.array.DefaultPlayLists);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//播放列表
		db.execSQL("CREATE TABLE Devices" + 
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT," + 
				" name NVARCHAR(128)," + 
				" mobile NVARCHAR(128),"+
				" password NVARCHAR(32),"+
				" status NVARCHAR(1)"				
				+ ")"
				);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS Devices");

		onCreate(db);
		//Log.d("DbHelper", "onDBUpgrade, ver:" + newVer);
	}
	
	
	/**
	 * 打开数据库
	 * 带锁模式
	 */
	public SQLiteDatabase openDB(){
		SQLiteDatabase db = this.getReadableDatabase();
		return db;
	}
	
	
	/**
	 * 关闭数据库
	 * 带锁模式
	 * @return
	 */
	@Override
	public void close() {
		super.close();
	}

	
}
