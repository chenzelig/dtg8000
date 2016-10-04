package com.daktel.MobileAlertCommunicator;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Switchdevice extends Activity implements OnClickListener {
	private TextView device_name_buf1 = null;
	private TextView device_name_buf2 = null;
	private TextView device_name_buf3 = null;
//	
//	private LinearLayout addlayout1 = null;
//	private LinearLayout addlayout2 = null;
//	private LinearLayout addlayout3 = null;
	
	private Button add_device = null;
	//private Button switch_device = null;
//	private ImageButton switch_device_moveup1 = null;
//	private ImageButton switch_device_moveup2 = null;
//	private ImageButton switch_device_moveup3 = null;
//	private ImageButton switch_device_delete1 = null;
//	private ImageButton switch_device_delete2 = null;
//	private ImageButton switch_device_delete3 = null;
	
	private TextView device_name = null;
	
	public static String device_number_disp = "";
	public static String device_name_disp = "";
	public static String digits_password_disp = "";
	
	public static String destnumber = null;
	public static String destname = null;
	public static String password = null;
	
//	public static String destnumber1 = null;
//	public static String destname1 = null;
//	public static String password1 = null;
//	
//	public static String destnumber2 = null;
//	public static String destname2 = null;
//	public static String password2 = null;
//	
//	public static String destnumber3 = null;
//	public static String destname3 = null;
//	public static String password3 = null;
	
	private Button btnBack;
	public ListView listView1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.switch_device);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		
		this.device_name = (TextView)super.findViewById(R.id.device_name);
		
		
        //����������
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    destname = uiState.getString("destname","" );
 	    password = "";
 	    this.device_name.setText(destname);
 	    
 	   listView1 = (ListView)findViewById(R.id.listView1);
 	  bindData();
 	
 	    
	}
	
	public void bindData()
	{

	 	  getDevices();
	 	  DeviceAdapter adapter = (DeviceAdapter)listView1.getAdapter();
			if(adapter == null){
				adapter = new DeviceAdapter(devices);
				listView1.setAdapter(adapter);
			}else{
				adapter.reload(devices);
				adapter.notifyDataSetChanged();
			}
	}
	
	
	private Object dbLocker = new Object();
	private List<deviceEntity> devices = null;
	/**
	 * 获取列表
	 * @return
	 */
	public List<deviceEntity> getDevices(){
		synchronized (dbLocker) {
			DBDevices db = new DBDevices(this);
			devices = db.getDeviceLists();
			db.close();
			return devices;
		}
	}
	
	protected class DeviceAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		private List<deviceEntity> list;

		public DeviceAdapter(List<deviceEntity> list) {
			super();
			inflater = getLayoutInflater();
			this.list = list;
			
		}

		
		private int currentPosition = 0;

		public int getCurrentPosition() {
			return currentPosition;
		}

		public void setCurrentPosition(int currentPosition) {
			this.currentPosition = currentPosition;
		}

		public int getCount() {
			if(list == null){
				return 0;
			}else{
				return list.size();
			}
		}

		public Object getItem(int position) {
			if (position < list.size()) {
				return list.get(position);
			} else
				return null;
		}

		public long getItemId(int position) {
			return position;
		}
		
		public void reload(List<deviceEntity> list){
			this.list = list;
		}
		
		public void addItem(deviceEntity item){
			list.add(item);
		}
		
		public void removeItem(int position){
			list.remove(position);
		}
		
		public List<deviceEntity> getAllItem(){
			return list;
		}
		
		public void clear(){
			list.clear();
		}


		public View getView(final int position, View view, ViewGroup parent) {
			final deviceEntity item = list.get(position);
			ViewHolder holder;

			
            if (view == null) {
            	view = inflater.inflate(R.layout.item_switchdevice, null);

            	holder = new ViewHolder();
            	holder.switch_device_moveup1 = (ImageButton) view.findViewById(R.id.switch_device_moveup1);
            	holder.device_name_buf1 = (TextView) view.findViewById(R.id.device_name_buf1);
               	holder.switch_device_delete1 = (ImageButton) view.findViewById(R.id.switch_device_delete1);
               	holder.switch_device_moveup1.setOnClickListener(onClickListener);
               	holder.switch_device_delete1.setOnClickListener(onClickListener);
    			view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            
            //设置控件值
	        holder.device_name_buf1.setText(item.name);
	        
	        
			view.setTag(holder);
            
            holder.switch_device_delete1.setTag(item); 
            holder.switch_device_moveup1.setTag(item);
            
            if(item.status.equals("1"))
            {
            	holder.switch_device_moveup1.setImageResource(R.drawable.moveup1);
            }
            else
            {
            	holder.switch_device_moveup1.setImageResource(R.drawable.moveup);
            }
	        
            return view;
		}
		
		/**
		 * 辅助类，将行模板对象统一在这里定义
		 * 
		 * @author Administrator
		 * 
		 */
		private class ViewHolder {
			ImageButton switch_device_moveup1;
			ImageButton switch_device_delete1;
			TextView device_name_buf1;
		}
		
		private OnClickListener onClickListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.switch_device_moveup1:
					deviceEntity device = (deviceEntity)v.getTag();
					
					SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			  		Editor et=uiState.edit();
			  		et.putString("destname", device.name);
			  		et.putString("destnumber", device.mobile);
			  		et.putString("password", device.password);
			  		et.commit();
			  		
			  	    //���ز���
			 	    destnumber = uiState.getString("destnumber","00000000000" );
			 	    destname = uiState.getString("destname","" );
			 	    password = "";
			 	   Switchdevice.this.device_name.setText(destname);
			 	   //Adddevice.this.addlayout1.setVisibility(View.GONE);
			 	   Toast.makeText(Switchdevice.this, R.string.Success, Toast.LENGTH_SHORT).show();
			 	   
					
					DBDevices db = new DBDevices(Switchdevice.this);
					db.updateAllStatus(device.id, 0);
					db.updateStatus(device.id, 1);
					db.close();
					bindData();
					
					break;
					
				case R.id.switch_device_delete1:
					device = (deviceEntity)v.getTag();
					
					if(device.mobile.equals(destnumber))
					{
						uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
				  		et=uiState.edit();
				  		et.putString("destname", "");
				  		et.putString("destnumber", "00000000000");
				  		et.putString("password","9999");
				  		et.commit();
				  		
				  	    //���ز���
				 	    destnumber = uiState.getString("destnumber","00000000000" );
				 	    destname = uiState.getString("destname","" );
				 	    password = "";
				 	   Switchdevice.this.device_name.setText(destname);
					}
			 	   //Adddevice.this.addlayout1.setVisibility(View.GONE);
			 	   Toast.makeText(Switchdevice.this, R.string.Success, Toast.LENGTH_SHORT).show();
			 	   
			 	   
					
					db = new DBDevices(Switchdevice.this);
					db.deleteDevices(device.id);
					db.close();
					bindData();
					
					break;
				}
				
				
			}
		};
		
	}
	
	
//	//switch device
//	private class OnTouchListenerswitchdevice implements OnTouchListener
//	{
//
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			// TODO Auto-generated method stub
//			switch (event.getAction())
//			{
//			case MotionEvent.ACTION_DOWN:
//				Adddevice.this.switch_device.setImageResource(R.drawable.app_add_switch_press);
//				break;
//				
//			case MotionEvent.ACTION_MOVE:
//				Adddevice.this.switch_device.setImageResource(R.drawable.app_add_switch);
//				break;
//				
//			case MotionEvent.ACTION_UP:
////				Intent it = new Intent(Adddevice.this,Switchdevice.class);//ʵ��Intent
////				it.putExtra("myinfosub", "entry add device");//Key
////				Adddevice.this.startActivity(it);
//				Adddevice.this.switch_device.setImageResource(R.drawable.app_add_switch);
//				break;
//				
//			}
//			return false;
//		}
//		
//	}
	@Override
	protected void onResume() {
		super.onResume();
		
		bindData();
	}
	//add device
	private class OnTouchListeneradddevice implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//Switchdevice.this.add_device.setImageResource(R.drawable.app_add_device_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//Switchdevice.this.add_device.setImageResource(R.drawable.app_add_device);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(Switchdevice.this,Adddevice.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry add device");//Key
				Switchdevice.this.startActivity(it);
				//Switchdevice.this.add_device.setImageResource(R.drawable.app_add_device);
				Switchdevice.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
//	private class OnClickListenerdevicemoveup1 implements OnClickListener
//	{
//		@Override
//		public void onClick(View arg0) {
//			// TODO Auto-generated method stub
//			//save data
//			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//	  		Editor et=uiState.edit();
//	  		et.putString("destname", destname1);
//	  		et.putString("destnumber", destnumber1);
//	  		et.putString("password", password1);
//	  		et.commit();
//	  		
//	  	    //���ز���
//	 	    destnumber = uiState.getString("destnumber","00000000000" );
//	 	    destname = uiState.getString("destname","" );
//	 	    password = uiState.getString("password", "9999"); 
//	 	   Switchdevice.this.device_name.setText(destname);
//	 	   //Adddevice.this.addlayout1.setVisibility(View.GONE);
//	 	   Toast.makeText(Switchdevice.this, "Set device1 OK.", Toast.LENGTH_SHORT).show();
//	 	   
//	 	  switch_device_moveup1.setImageResource(R.drawable.moveup1);
//	 	 switch_device_moveup2.setImageResource(R.drawable.moveup);
//	 	switch_device_moveup3.setImageResource(R.drawable.moveup);
//	 	   
//		}
//		
//	}
//	
//	private class OnClickListenerdevicemoveup2 implements OnClickListener
//	{
//		@Override
//		public void onClick(View arg0) {
//			// TODO Auto-generated method stub
//			//save data
//			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//	  		Editor et=uiState.edit();
//	  		et.putString("destname", destname2);
//	  		et.putString("destnumber", destnumber2);
//	  		et.putString("password", password2);
//	  		et.commit();
//	  		
//	  	    //���ز���
//	 	    destnumber = uiState.getString("destnumber","00000000000" );
//	 	    destname = uiState.getString("destname","" );
//	 	    password = uiState.getString("password", "9999"); 
//	 	    Switchdevice.this.device_name.setText(destname);
//	 	   //Adddevice.this.addlayout2.setVisibility(View.GONE);
//	 	   Toast.makeText(Switchdevice.this, "Set device2 OK.", Toast.LENGTH_SHORT).show();
//	 	  switch_device_moveup1.setImageResource(R.drawable.moveup);
//	 	 switch_device_moveup2.setImageResource(R.drawable.moveup1);
//	 	switch_device_moveup3.setImageResource(R.drawable.moveup);
//		}
//		
//	}
//	
//	private class OnClickListenerdevicemoveup3 implements OnClickListener
//	{
//		@Override
//		public void onClick(View arg0) {
//			// TODO Auto-generated method stub
//			//save data
//			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//	  		Editor et=uiState.edit();
//	  		et.putString("destname", destname3);
//	  		et.putString("destnumber", destnumber3);
//	  		et.putString("password", password3);
//	  		et.commit();
//	  		
//	  	    //���ز���
//	 	    destnumber = uiState.getString("destnumber","00000000000" );
//	 	    destname = uiState.getString("destname","" );
//	 	    password = uiState.getString("password", "9999"); 
//	 	    Switchdevice.this.device_name.setText(destname);
//	 	    //Adddevice.this.addlayout3.setVisibility(View.GONE);
//	 	    Toast.makeText(Switchdevice.this, "Set device3 OK.", Toast.LENGTH_SHORT).show();
//	 	   switch_device_moveup1.setImageResource(R.drawable.moveup);
//	 	  switch_device_moveup2.setImageResource(R.drawable.moveup);
//	 	 switch_device_moveup3.setImageResource(R.drawable.moveup1);
//		}
//		
//	}
	
//	private class OnClickListenerdevicedelete1 implements OnClickListener
//	{
//		@Override
//		public void onClick(View arg0) {
//			// TODO Auto-generated method stub
//			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//	  		Editor et=uiState.edit();
//	  		
//	 	   String devicename2_temp = destname2;
//	 	   if(!devicename2_temp.equals(""))
//	 	   {
//	 		   	//device2 ��Ϊ���򸲸�device1
//		  		et.putString("destname1", destname2);
//		  		et.putString("destnumber1", destnumber2);
//		  		et.putString("password1", password2);
//		  		et.commit();
//		  		
//		 	    //name1
//		 	    destnumber1 = uiState.getString("destnumber1","00000000000" );
//		 	    destname1 = uiState.getString("destname1","" );
//		 	    password1 = uiState.getString("password1", "9999"); 
//
//		 	   Switchdevice.this.addlayout1.setVisibility(View.VISIBLE);
//		 	  Switchdevice.this.device_name_buf1.setText(destname1);
//		 	   
//		 	   String devicename3_temp = destname3;
//		 	   if(!devicename3_temp.equals(""))
//		 	   {
//		 		   //device3 ����device2
//			  		et.putString("destname2", destname3);
//			  		et.putString("destnumber2", destnumber3);
//			  		et.putString("password2", password3);
//			  		et.commit();
//			  		
//			 	    //name1
//			 	    destnumber2 = uiState.getString("destnumber2","00000000000" );
//			 	    destname2 = uiState.getString("destname2","" );
//			 	    password2 = uiState.getString("password2", "9999"); 
//
//			 	   Switchdevice.this.addlayout2.setVisibility(View.VISIBLE);
//			 	  Switchdevice.this.device_name_buf2.setText(destname2);
//			 	   
//			  		et.putString("destname3", "");
//			  		et.putString("destnumber3", "00000000000");
//			  		et.putString("password3", "9999");
//			  		et.commit();
//			  		destname3 = "";
//			  		destnumber3 = "00000000000";
//			  		password3 = "9999";
//			  		Switchdevice.this.addlayout3.setVisibility(View.GONE);
//		 	   }
//		 	   else
//		 	   {
//			  		et.putString("destname2", "");
//			  		et.putString("destnumber2", "00000000000");
//			  		et.putString("password2", "9999");
//			  		et.commit();
//			  		destname2 = "";
//			  		destnumber2 = "00000000000";
//			  		password2 = "9999";
//			  		Switchdevice.this.addlayout2.setVisibility(View.GONE);
//		 	   }
//		}
// 	   else
// 	   {
//	  		et.putString("destname1", "");
//	  		et.putString("destnumber1", "00000000000");
//	  		et.putString("password1", "9999");
//	  		et.commit();
//	  		destname1 = "";
//	  		destnumber1 = "00000000000";
//	  		password1 = "9999";
//	  		Switchdevice.this.addlayout1.setVisibility(View.GONE);
// 	   }
//	 	   
//	 	  Toast.makeText(Switchdevice.this, "Delete device1 OK.", Toast.LENGTH_SHORT).show();
//		}
//		
//	}
	

	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    destname = uiState.getString("destname","" );
 	    password = "";
 	    this.device_name.setText(destname);
		
		super.onRestart();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.btnBack:
				finish();
				break;				
				
		}
		
	}
}
