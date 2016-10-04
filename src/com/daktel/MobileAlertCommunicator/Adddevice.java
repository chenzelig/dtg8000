package com.daktel.MobileAlertCommunicator;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
import android.content.ContentResolver;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.ContextMenu;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
//import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
//import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
//import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ListView;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Adddevice extends Activity implements OnClickListener{
//	private TextView device_name_buf1 = null;
//	private TextView device_name_buf2 = null;
//	private TextView device_name_buf3 = null;
	
//	private LinearLayout addlayout1 = null;
//	private LinearLayout addlayout2 = null;
//	private LinearLayout addlayout3 = null;
	
	//private Button add_device = null;
	private Button switch_device = null;
	
	private TextView device_name = null;
	private Button device_search = null;

//	private EditText digitspassword_edit1 = null;
//	private Button digitspassword_set1 = null;
//	
//	private EditText digitspassword_edit2 = null;
//	private Button digitspassword_set2 = null;
//	
//	private EditText digitspassword_edit3 = null;
//	private Button digitspassword_set3 = null;
	
	public static String device_number_disp = "";
	public static String device_name_disp = "";
	public static String digits_password_disp = "";
	
	public static String destnumber = null;
	public static String destname = null;
	public static String password = null;
//	
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
	//ģ�����
	public String username,usernumber;
	
	public ListView listView1;
	
	private Button btnBack;
	
	
	//定义一个HashMap，用来存放EditText的值，Key是position  
    HashMap<Integer, String> hashMap = new HashMap<Integer, String>();  
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.add_device);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		
		this.device_name = (TextView)super.findViewById(R.id.device_name);
		this.device_search = (Button)super.findViewById(R.id.device_search);
		this.device_search.setOnClickListener(new OnClickListenersearch());
		
//		this.addlayout1 = (LinearLayout)super.findViewById(R.id.addlayout1);
//		this.addlayout2 = (LinearLayout)super.findViewById(R.id.addlayout2);
//		this.addlayout3 = (LinearLayout)super.findViewById(R.id.addlayout3);
		
		//this.device_name_buf1 = (TextView)super.findViewById(R.id.device_name_buf1);
		//this.device_name_buf1.setOnLongClickListener(new OnLongClickListenername1());
		//this.device_name_buf2 = (TextView)super.findViewById(R.id.device_name_buf2);
		//this.device_name_buf2.setOnLongClickListener(new OnLongClickListenername2());
		//this.device_name_buf3 = (TextView)super.findViewById(R.id.device_name_buf3);
		//this.device_name_buf3.setOnLongClickListener(new OnLongClickListenername3());
		
//		this.add_device1_addtips = (ImageButton)super.findViewById(R.id.add_device1_addtips);
//		this.add_device2_addtips = (ImageButton)super.findViewById(R.id.add_device2_addtips);
//		this.add_device3_addtips = (ImageButton)super.findViewById(R.id.add_device3_addtips);
//		this.add_device1_addtips.setOnClickListener(new OnClickListeneradd1());
//		this.add_device2_addtips.setOnClickListener(new OnClickListeneradd2());
//		this.add_device3_addtips.setOnClickListener(new OnClickListeneradd3());
		
//		this.digitspassword_edit1 = (EditText)super.findViewById(R.id.digitspassword_edit1);
//		this.digitspassword_set1 = (Button)super.findViewById(R.id.digitspassword_set1);
//		this.digitspassword_set1.setOnClickListener(new OnClickListenerdigitspassword1());
//		
//		this.digitspassword_edit2 = (EditText)super.findViewById(R.id.digitspassword_edit2);
//		this.digitspassword_set2 = (Button)super.findViewById(R.id.digitspassword_set2);
//		this.digitspassword_set2.setOnClickListener(new OnClickListenerdigitspassword2());
//		
//		this.digitspassword_edit3 = (EditText)super.findViewById(R.id.digitspassword_edit3);
//		this.digitspassword_set3 = (Button)super.findViewById(R.id.digitspassword_set3);
//		this.digitspassword_set3.setOnClickListener(new OnClickListenerdigitspassword3());
		
		//this.add_device = (ImageButton)super.findViewById(R.id.add_device);
//		this.switch_device = (Button)super.findViewById(R.id.switch_device);
//		this.switch_device.setOnTouchListener(new OnTouchListenerswitchdevice());
		
        //����������
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    destname = uiState.getString("destname","" );
 	    password = "";
 	    this.device_name.setText(destname);
 	    
 	   listView1 = (ListView)findViewById(R.id.listView1);
// 	   listView1.setOnItemClickListener(new OnItemClickListener(){
//
//			public void onItemClick(AdapterView<?> parent, View view, int position,
//					long rowId) {
//				
//				//deviceEntity item = (deviceEntity)(((DeviceAdapter)parent.getAdapter()).getItem(position - listView1.getHeaderViewsCount()));
//				
//			}
//      	
// 	   });
 	  bindData();
 	    
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
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
            	view = inflater.inflate(R.layout.item_device, null);

            	holder = new ViewHolder();
            	holder.digitspassword_edit1 = (EditText) view.findViewById(R.id.digitspassword_edit1);
            	holder.device_name_buf1 = (TextView) view.findViewById(R.id.device_name_buf1);
               	holder.digitspassword_set1 = (Button) view.findViewById(R.id.digitspassword_set1);
               	holder.digitspassword_set1.setOnClickListener(onClickListener);
    			view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            
           
            
            
            //设置控件值
	        holder.digitspassword_edit1.setText(item.password);
	        holder.device_name_buf1.setText(item.name);
	        
	        
	        holder.digitspassword_edit1.setText(item.password);  
	        holder.digitspassword_set1.setOnClickListener(onClickListener);
			view.setTag(holder);
            //为editText设置TextChangedListener，每次改变的值设置到hashMap  
            //我们要拿到里面的值根据position拿值  
	        holder.digitspassword_edit1.addTextChangedListener(new TextWatcher() {  
                @Override  
                public void onTextChanged(CharSequence s, int start, int before, int count) {  
                      
                }  
                  
                @Override  
                public void beforeTextChanged(CharSequence s, int start,   
                        int count,int after) {  
                      
                }  
                  
                @Override  
                public void afterTextChanged(Editable s) {  
                    //将editText中改变的值设置的HashMap中  
                    hashMap.put(position, s.toString());  
                    item.password1 = s.toString();
                }
 
            });  
              
	        holder.digitspassword_edit1.setText(item.password);  
            //item.password1 =hashMap.get(position); 
            
            holder.digitspassword_set1.setTag(item); 
	        
            return view;
		}
		
		/**
		 * 辅助类，将行模板对象统一在这里定义
		 * 
		 * @author Administrator
		 * 
		 */
		private class ViewHolder {
			Button digitspassword_set1;
			EditText digitspassword_edit1;
			TextView device_name_buf1;
		}
		
		private OnClickListener onClickListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.digitspassword_set1:
					deviceEntity device = (deviceEntity)v.getTag();
					//for example:SET9999#PHONE1#911#
					String smstel = device.mobile;//����
//					String smsnote = "SET"+device.password+"#SMSPW#"+  device.password1 +"#";//���
					String smsnote = "Go Conecto";
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
					Toast.makeText(Adddevice.this, R.string.Set_password_OK, Toast.LENGTH_SHORT).show();
					
					DBDevices db = new DBDevices(Adddevice.this);
					db.update(device.id, device.password1);
					db.close();
					break;
				}
			}
		};
		
	}
	
	
	//switch device
	private class OnTouchListenerswitchdevice implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//Adddevice.this.switch_device.setImageResource(R.drawable.app_add_switch_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//Adddevice.this.switch_device.setImageResource(R.drawable.app_add_switch);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(Adddevice.this,Switchdevice.class);//ʵ��Intent
				it.putExtra("myinfosub", getResources().getString(R.string.entry_add_device));//Key
				Adddevice.this.startActivity(it);
				//Adddevice.this.switch_device.setImageResource(R.drawable.app_add_switch);
				Adddevice.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
//	//add device
//	private class OnTouchListeneradddevice implements OnTouchListener
//	{
//
//		@Override
//		public boolean onTouch(View v, MotionEvent event) {
//			// TODO Auto-generated method stub
//			switch (event.getAction())
//			{
//			case MotionEvent.ACTION_DOWN:
//				MMset.this.add_device.setImageResource(R.drawable.app_add_device_press);
//				break;
//				
//			case MotionEvent.ACTION_MOVE:
//				MMset.this.add_device.setImageResource(R.drawable.app_add_device);
//				break;
//				
//			case MotionEvent.ACTION_UP:
//				Intent it = new Intent(MMset.this,Adddevice.class);//ʵ��Intent
//				it.putExtra("myinfosub", "entry add device");//Key
//				MMset.this.startActivity(it);
//				MMset.this.add_device.setImageResource(R.drawable.app_add_device);
//				break;
//				
//			}
//			return false;
//		}
//		
//	}
	
	//password1
	private class OnClickListenerdigitspassword1 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			String editphonenumber_temp = Adddevice.this.digitspassword_edit1.getText().toString().trim();//���
//			if(destnumber.equals("00000000000"))
//			{	
//				Toast.makeText(Adddevice.this, "Please set destination number first��", Toast.LENGTH_SHORT).show();
//			}
//			else
			{
				
				
				//save data
//				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et=uiState.edit();
//		  		et.putString("password1", editphonenumber_temp);
//		  		et.commit();
//		  		password1 = editphonenumber_temp;
				
			
			}
			
		}
		
	}
	
	//password2
	private class OnClickListenerdigitspassword2 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			String editphonenumber_temp = Adddevice.this.digitspassword_edit2.getText().toString().trim();//���
//			if(editphonenumber_temp.equals(""))
//			{	
//				Toast.makeText(Adddevice.this, "Please input password2!", Toast.LENGTH_SHORT).show();
//			}
//			else
			{
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber2;//����
//				String smsnote = "SET"+password2+"#SMSPW#"+editphonenumber_temp+"#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(Adddevice.this, "Set password2 OK.", Toast.LENGTH_SHORT).show();
//				
//				//save data
//				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et=uiState.edit();
//		  		et.putString("password2", editphonenumber_temp);
//		  		et.commit();
//		  		password2 = editphonenumber_temp;
			
			}
		}
		
	}
	
	//password3
	private class OnClickListenerdigitspassword3 implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			String editphonenumber_temp = Adddevice.this.digitspassword_edit3.getText().toString().trim();//���
//			if(editphonenumber_temp.equals(""))
//			{	
//				Toast.makeText(Adddevice.this, "Please input password3!", Toast.LENGTH_SHORT).show();
//			}
//			else
			{
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber3;//����
//				String smsnote = "SET"+password3+"#SMSPW#"+editphonenumber_temp+"#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(Adddevice.this, "Set password3 OK.", Toast.LENGTH_SHORT).show();
//				
//				//save data
//				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et=uiState.edit();
//		  		et.putString("password3", editphonenumber_temp);
//		  		et.commit();
//		  		password3 = editphonenumber_temp;
			
			}
		}
		
	}
	
	//name1 long click
//	private class OnLongClickListenername1 implements OnLongClickListener
//	{
//		@Override
//		public boolean onLongClick(View v) {
//			// TODO Auto-generated method stub
//	 		//dilog
//			linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.password_edit, null); 
//			sjledit = (EditText) linearLayout.findViewById(R.id.pwd_edit);
//			
//	 		Dialog dialog = new AlertDialog.Builder(Adddevice.this)
//	 		.setTitle("Edit 4 digits password")
//	 		.setView(linearLayout)
//	 		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					String editphonenumber_temp = sjledit.getText().toString().trim();//���
//					if(editphonenumber_temp.equals(""))
//					{	
//						Toast.makeText(Adddevice.this, "Please input password!", Toast.LENGTH_SHORT).show();
//					}
//					else
//					{
//						//save data
//						SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//				  		Editor et=uiState.edit();
//				  		et.putString("password1", editphonenumber_temp);
//				  		et.commit();
//				  		password1 = editphonenumber_temp;
//						Toast.makeText(Adddevice.this, "Set password OK.", Toast.LENGTH_SHORT).show();
//					
//					}
//				}
//			})
//			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					dialog.dismiss();
//				}
//			})
//			.create();
//	 		
//	 		dialog.show();
//	 		
//			return false;
//		}
//		
//	}
	
	//name2 long click
//	private class OnLongClickListenername2 implements OnLongClickListener
//	{
//		@Override
//		public boolean onLongClick(View v) {
//			// TODO Auto-generated method stub
//	 		//dilog
//			linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.password_edit, null); 
//			sjledit = (EditText) linearLayout.findViewById(R.id.pwd_edit);
//			
//	 		Dialog dialog = new AlertDialog.Builder(Adddevice.this)
//	 		.setTitle("Edit 4 digits password")
//	 		.setView(linearLayout)
//	 		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					String editphonenumber_temp = sjledit.getText().toString().trim();//���
//					if(editphonenumber_temp.equals(""))
//					{	
//						Toast.makeText(Adddevice.this, "Please input password!", Toast.LENGTH_SHORT).show();
//					}
//					else
//					{
//						//save data
//						SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//				  		Editor et=uiState.edit();
//				  		et.putString("password2", editphonenumber_temp);
//				  		et.commit();
//				  		password2 = editphonenumber_temp;
//						Toast.makeText(Adddevice.this, "Set password OK.", Toast.LENGTH_SHORT).show();
//					
//					}
//				}
//			})
//			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					dialog.dismiss();
//				}
//			})
//			.create();
//	 		
//	 		dialog.show();
//	 		
//			return false;
//		}
//		
//	}
	
	//name3 long click
//	private class OnLongClickListenername3 implements OnLongClickListener
//	{
//		@Override
//		public boolean onLongClick(View v) {
//			// TODO Auto-generated method stub
//	 		//dilog
//			linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.password_edit, null); 
//			sjledit = (EditText) linearLayout.findViewById(R.id.pwd_edit);
//			
//	 		Dialog dialog = new AlertDialog.Builder(Adddevice.this)
//	 		.setTitle("Edit 4 digits password")
//	 		.setView(linearLayout)
//	 		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					String editphonenumber_temp = sjledit.getText().toString().trim();//���
//					if(editphonenumber_temp.equals(""))
//					{	
//						Toast.makeText(Adddevice.this, "Please input password!", Toast.LENGTH_SHORT).show();
//					}
//					else
//					{
//						//save data
//						SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//				  		Editor et=uiState.edit();
//				  		et.putString("password3", editphonenumber_temp);
//				  		et.commit();
//				  		password3 = editphonenumber_temp;
//						Toast.makeText(Adddevice.this, "Set password OK.", Toast.LENGTH_SHORT).show();
//					
//					}
//				}
//			})
//			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					dialog.dismiss();
//				}
//			})
//			.create();
//	 		
//	 		dialog.show();
//	 		
//			return false;
//		}
//		
//	}
	
	//add device1
//	private class OnClickListeneradd1 implements OnClickListener
//	{
//		@Override
//		public void onClick(View v) {
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
//	 	   Adddevice.this.device_name.setText(destname);
//	 	   //Adddevice.this.addlayout1.setVisibility(View.GONE);
//	 	   Toast.makeText(Adddevice.this, "Set device OK.", Toast.LENGTH_SHORT).show();
//	 	   
//   		   //Adddevice.this.addlayout2.setVisibility(View.VISIBLE);
//	 	   String devicename2_temp = destname2;
//	 	   if(!devicename2_temp.equals(""))
//	 	   {
//	 		   	//device2 ��Ϊ���򸲸�device1
//				//save data
//				//SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		//Editor et=uiState.edit();
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
//		 	   Adddevice.this.addlayout1.setVisibility(View.VISIBLE);
//		 	   Adddevice.this.device_name_buf1.setText(destname1);
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
//			 	   Adddevice.this.addlayout2.setVisibility(View.VISIBLE);
//			 	   Adddevice.this.device_name_buf2.setText(destname2);
//			 	   
//			  		et.putString("destname3", "");
//			  		et.putString("destnumber3", "00000000000");
//			  		et.putString("password3", "9999");
//			  		et.commit();
//			  		destname3 = "";
//			  		destnumber3 = "00000000000";
//			  		password3 = "9999";
//			  		Adddevice.this.addlayout3.setVisibility(View.GONE);
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
//			  		Adddevice.this.addlayout2.setVisibility(View.GONE);
//		 	   }
//		}
//	 	   else
//	 	   {
//		  		et.putString("destname1", "");
//		  		et.putString("destnumber1", "00000000000");
//		  		et.putString("password1", "9999");
//		  		et.commit();
//		  		destname1 = "";
//		  		destnumber1 = "00000000000";
//		  		password1 = "9999";
//		  		Adddevice.this.addlayout1.setVisibility(View.GONE);
//	 	   }
//
//		}
//	}
	
	//add device2
//	private class OnClickListeneradd2 implements OnClickListener
//	{
//		@Override
//		public void onClick(View v) {
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
//	 	   Adddevice.this.device_name.setText(destname);
//	 	  //Adddevice.this.addlayout2.setVisibility(View.GONE);
//	 	   Toast.makeText(Adddevice.this, "Set device OK.", Toast.LENGTH_SHORT).show();
//	 	   
//	 	   
//	 	   String devicename3_temp = destname3;
//	 	   if(!devicename3_temp.equals(""))
//	 	   {
//	 		   //device3 ����device2
//		  		et.putString("destname2", destname3);
//		  		et.putString("destnumber2", destnumber3);
//		  		et.putString("password2", password3);
//		  		et.commit();
//		  		
//		 	    //name1
//		 	    destnumber2 = uiState.getString("destnumber2","00000000000" );
//		 	    destname2 = uiState.getString("destname2","" );
//		 	    password2 = uiState.getString("password2", "9999"); 
//
//		 	   Adddevice.this.addlayout2.setVisibility(View.VISIBLE);
//		 	   Adddevice.this.device_name_buf2.setText(destname2);
//		 	   
//		  		et.putString("destname3", "");
//		  		et.putString("destnumber3", "00000000000");
//		  		et.putString("password3", "9999");
//		  		et.commit();
//		  		destname3 = "";
//		  		destnumber3 = "00000000000";
//		  		password3 = "9999";
//		  		Adddevice.this.addlayout3.setVisibility(View.GONE);
//	 	   }
//	 	   else
//	 	   {
//		  		et.putString("destname2", "");
//		  		et.putString("destnumber2", "00000000000");
//		  		et.putString("password2", "9999");
//		  		et.commit();
//		  		destname2 = "";
//		  		destnumber2 = "00000000000";
//		  		password2 = "9999";
//		  		Adddevice.this.addlayout2.setVisibility(View.GONE);
//	 	   }
//		}
//		
//	}
	
	//add device3
//	private class OnClickListeneradd3 implements OnClickListener
//	{
//		@Override
//		public void onClick(View v) {
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
//	 	   Adddevice.this.device_name.setText(destname);
//	 	  //Adddevice.this.addlayout3.setVisibility(View.GONE);
//	 	   Toast.makeText(Adddevice.this, "Set device OK.", Toast.LENGTH_SHORT).show();
//	 	   
//	  		et.putString("destname3", "");
//	  		et.putString("destnumber3", "00000000000");
//	  		et.putString("password3", "9999");
//	  		et.commit();
//	  		destname3 = "";
//	  		destnumber3 = "00000000000";
//	  		password3 = "9999";
//	  		Adddevice.this.addlayout3.setVisibility(View.GONE);
//		}
//		
//	}
	
	//search
	private class OnClickListenersearch implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		       startActivityForResult(new Intent(
		                Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI), 0);
		}
		
	}
//	
//    //��ʱ���¼�����������
//    Handler handler=new Handler(); 
//    Runnable runnable=new Runnable() { 
//        @Override
//        public void run() { 
//            // TODO Auto-generated method stub 
//            //Ҫ�������� 
//        	if(destname1.equals(""))
//        	{
//        		destname1 = device_name_disp;
//        		destnumber1 = device_number_disp;
//        		
//        		Adddevice.this.addlayout1.setVisibility(View.VISIBLE);
//        		Adddevice.this.device_name_buf1.setText(device_name_disp);
//        		
//				//save data
//				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et=uiState.edit();
//		  		et.putString("destname1", device_name_disp);
//		  		et.putString("destnumber1", device_number_disp);
//		  		et.commit();
//        	}
//        	else if(destname2.equals(""))
//        	{
//        		destname2 = device_name_disp;
//        		destnumber2 = device_number_disp;
//        		
//        		Adddevice.this.addlayout2.setVisibility(View.VISIBLE);
//        		Adddevice.this.device_name_buf2.setText(device_name_disp);
//        		
//				//save data
//				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et=uiState.edit();
//		  		et.putString("destname2", device_name_disp);
//		  		et.putString("destnumber2", device_number_disp);
//		  		et.commit();
//        	}
//        	else if(destname3.equals(""))
//        	{
//        		destname3 = device_name_disp;
//        		destnumber3 = device_number_disp;
//        		
//        		Adddevice.this.addlayout3.setVisibility(View.VISIBLE);
//        		Adddevice.this.device_name_buf3.setText(device_name_disp);
//        		
//				//save data
//				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et=uiState.edit();
//		  		et.putString("destname3", device_name_disp);
//		  		et.putString("destnumber3", device_number_disp);
//		  		et.commit();
//        	}
//        	else
//        	{
//        		destname3 = device_name_disp;
//        		destnumber3 = device_number_disp;
//        		
//        		Adddevice.this.addlayout3.setVisibility(View.VISIBLE);
//        		Adddevice.this.device_name_buf3.setText(device_name_disp);
//        		
//				//save data
//				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et=uiState.edit();
//		  		et.putString("destname3", device_name_disp);
//		  		et.putString("destnumber3", device_number_disp);
//		  		et.commit();
//        	}
//        	
//        	
//        	handler.removeCallbacks(runnable);
//        	
//			//������ʱ�� 100ms�ٴν���
//			//handler.postDelayed(runnable, 100);
//        	
//        } 
//    };

    /*
             ������ʱ��
     handler.postDelayed(runnable, 2000);//ÿ����ִ��һ��runnable
	ֹͣ��ʱ��
	handler.removeCallbacks(runnable);
     */
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ContentResolver reContentResolverol = getContentResolver();
             Uri contactData = data.getData();
             @SuppressWarnings("deprecation")
            Cursor cursor = managedQuery(contactData, null, null, null, null);
             cursor.moveToFirst();
             username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                     null, 
                     ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, 
                     null, 
                     null);
             while (phone.moveToNext()) {
                 usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                 //Adddevice.this.device_name_search.setText(usernumber+" ("+username+")");
                 //Adddevice.this.device_name_search.setText(username);
             	device_number_disp = usernumber;
            	device_name_disp = username;
            	
            	deviceEntity de = new deviceEntity();
            	de.name = username;
            	de.mobile = usernumber;
            	de.password = "";
            	
            	DBDevices db = new DBDevices(Adddevice.this);
            	db.save(de);
            	db.close();
            	
            	bindData();
            	//������ʱ��
    		//	handler.postDelayed(runnable, 500);
             }

         }
    }
	
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
