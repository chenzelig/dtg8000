package com.daktel.MobileAlertCommunicator;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBDevices extends DBFactory {
	private static final String TB_DEVICES = "Devices";
	
	public DBDevices(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 获取所有播放列表
	 * @return
	 */
	public List<deviceEntity> getDeviceLists(){
		List<deviceEntity> lists = new ArrayList<deviceEntity>();
		SQLiteDatabase db = this.openDB();
		Cursor cursor = db.rawQuery("select _id,name,mobile,password,status from " + TB_DEVICES, null);
		while(cursor != null && cursor.moveToNext()){
			deviceEntity c = new deviceEntity();
			c.id = cursor.getInt(0);
			c.name = cursor.getString(1);
			c.mobile = cursor.getString(2);
			c.password = cursor.getString(3);
			c.status = cursor.getString(4);
			lists.add(c);
		}
		cursor.close();
		this.close();
		
		return lists;
	}

	/**
	 * 获取对象
	 * @param id
	 * @return
	 */
	public deviceEntity getDevice(int id){
		deviceEntity c = new deviceEntity();
		SQLiteDatabase db = this.openDB();
		Cursor cursor = db.rawQuery("select _id,name,mobile,password,status from " + TB_DEVICES + " where _id=" + id, null);
		if(cursor != null && cursor.moveToNext()){
			c.id = cursor.getInt(0);
			c.name = cursor.getString(1);
			c.mobile = cursor.getString(2);
			c.password = cursor.getString(3);
			c.status = cursor.getString(4);
		}
		cursor.close();
		this.close();
		
		return c;
	}
	
	
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public boolean deleteDevices(int id){
		boolean result = false;
		SQLiteDatabase db = this.openDB();
		db.beginTransaction();
		try{
			db.execSQL("delete from " + TB_DEVICES + " where _id=" + id);
			db.setTransactionSuccessful();
			result = true;
		}catch(SQLiteException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//提交事务
			db.endTransaction();
			this.close();
		}
		
		return result;
	}
	
	/**
	 * 删除所有
	 * @param id
	 * @return
	 */
	public boolean clearAll(){
		boolean result = false;
		SQLiteDatabase db = this.openDB();
		db.beginTransaction();
		try{
			db.execSQL("delete from " + TB_DEVICES);
			db.setTransactionSuccessful();
			result = true;
		}catch(SQLiteException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//提交事务
			db.endTransaction();
			this.close();
		}
		
		return result;
	}
	
	/**
	 * 重命名
	 * @param id
	 * @param name,mobile,password
	 * @return
	 */
	public boolean update(int id,String password){
		SQLiteDatabase db = this.openDB();
		boolean result = false;
		try{
			db.execSQL("update " + TB_DEVICES + " set password=? where _id=" + id, new String[]{password});
			result = true;
		}catch(SQLiteException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return result;
	}
	
	/**
	 * 重命名
	 * @param id
	 * @param name,mobile,password
	 * @return
	 */
	public boolean updateStatus(int id,int status){
		SQLiteDatabase db = this.openDB();
		boolean result = false;
		try{
			db.execSQL("update " + TB_DEVICES + " set status=? where _id=" + id, new String[]{String.valueOf(status)});
			result = true;
		}catch(SQLiteException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return result;
	}
	/**
	 * 重命名
	 * @param id
	 * @param name,mobile,password
	 * @return
	 */
	public boolean updateAllStatus(int id,int status){
		SQLiteDatabase db = this.openDB();
		boolean result = false;
		try{
			db.execSQL("update " + TB_DEVICES + " set status=?", new String[]{String.valueOf(status)});
			result = true;
		}catch(SQLiteException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return result;
	}
	
	
	/**
	 * save
	 * @param id
	 * @param name,mobile,password
	 * @return
	 */
	public boolean save(deviceEntity de){
		SQLiteDatabase db = this.openDB();
		boolean result = false;
		try{
			db.execSQL("insert into " + TB_DEVICES + "(name,mobile,password,status) values (?,?,?,?)", new String[]{de.name,de.mobile,de.password,"0"});
			result = true;
		}catch(SQLiteException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return result;
	}
}
