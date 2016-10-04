package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
//import android.telephony.SmsMessage;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuSetting extends Activity implements OnClickListener {
	private TextView device_name = null;
	private Button add_device = null;
	private Button switch_device = null;
	
	private Button find_location = null;
	//private Button change_device = null;
	private Button call_now = null;
	private ImageView fall_detect_state = null;
	private ImageView low_battery_state = null;
	private ImageView geofence_switch = null;

	private Button menu_sos_number = null;
	private Button menu_m1m2 = null;
	private Button menu_sos_message = null;
	private Button menu_gps = null;
	private Button menu_tone_profile = null;
	private Button menu_fall_detect = null;
	private Button menu_low_battery = null;
	private Button menu_eci = null;
	private Button menu_device_check = null;
	private Button menu_language = null;
	private Button menu_help = null;
	private Button menu_alerts = null;
	
	private LinearLayout linerMenu;
	private Button btnMenu;
	private Button btnCloseMenu;	
	
	
	private String destnumber = null;
	private String desname = null;
	private int falldetectstate = 0;//1:on   0:off
	private int lowbatterystate = 0;//1:on   0:off
	private String geofence_status = "0";
	private String password = null;
	
	
	private TextView labAbout;
	private TextView labContact;
	private TextView labPrivacy;
	private TextView labSos;
	private TextView labMessage;
	private TextView labMM;
	private TextView labGPSSetting;
	private TextView labFall;
	private TextView labTone;
	private TextView labLow;
	private TextView labAuto;
	private TextView labLanguage;
	private TextView labalert;
	private TextView labHelp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.main_menu);
		Log.i("log:", "begin log");
		this.device_name = (TextView)super.findViewById(R.id.device_name);
		this.add_device = (Button)super.findViewById(R.id.add_device);
		this.add_device.setOnTouchListener(new OnTouchListeneradddevice());
		this.switch_device = (Button)super.findViewById(R.id.switch_device);
		this.switch_device.setOnTouchListener(new OnTouchListenerswitchdevice());
		
		this.find_location = (Button)super.findViewById(R.id.find_location);
		this.find_location.setOnTouchListener(new OnTouchListenerfindlocation());
		this.call_now = (Button)super.findViewById(R.id.call_now);
		this.call_now.setOnTouchListener(new OnTouchListenercallnow());

		this.fall_detect_state = (ImageView)super.findViewById(R.id.menu_fall_alert_state);
		this.fall_detect_state.setOnClickListener(new OnClickListenerfalldetect());
		
		this.low_battery_state = (ImageView)super.findViewById(R.id.menu_low_battery_alert_state);
		this.low_battery_state.setOnClickListener(new OnClickListenerlowbattery());
		
		this.geofence_switch = (ImageView)super.findViewById(R.id.menu_safe_zone_alert_state);
		this.geofence_switch.setOnClickListener(new OnClickListenerGeofenceEnable());
	
		this.menu_sos_number = (Button)super.findViewById(R.id.menu_sos_number);
		this.menu_m1m2 = (Button)super.findViewById(R.id.menu_m1m2);
		this.menu_sos_message = (Button)super.findViewById(R.id.menu_sos_message);
		this.menu_gps = (Button)super.findViewById(R.id.menu_gps);
		this.menu_tone_profile = (Button)super.findViewById(R.id.menu_tone_profile);
		this.menu_fall_detect = (Button)super.findViewById(R.id.menu_fall_detect);
		this.menu_low_battery = (Button)super.findViewById(R.id.menu_low_battery);
		this.menu_eci = (Button)super.findViewById(R.id.menu_eci);
		this.menu_device_check = (Button)super.findViewById(R.id.menu_device_check);
		this.menu_language = (Button)super.findViewById(R.id.menu_language);
//		this.menu_help = (Button)super.findViewById(R.id.menu_help);
//		this.menu_alerts = (Button)super.findViewById(R.id.menu_alerts);
		
		this.menu_sos_number.setOnTouchListener(new OnTouchListenersosnumber());
		this.menu_m1m2.setOnTouchListener(new OnTouchListenerm1m2());
		this.menu_sos_message.setOnTouchListener(new OnTouchListenersosmessage());
		this.menu_gps.setOnTouchListener(new OnTouchListenergps());
		this.menu_tone_profile.setOnTouchListener(new OnTouchListenertoneprofile());
		this.menu_fall_detect.setOnTouchListener(new OnTouchListenerfalldetect());
		this.menu_low_battery.setOnTouchListener(new OnTouchListenerlowbattery());
		this.menu_eci.setOnTouchListener(new OnTouchListenereci());
		this.menu_device_check.setOnTouchListener(new OnTouchListenerdevicecheck());
		this.menu_language.setOnTouchListener(new OnTouchListenerlanguage());
//		this.menu_help.setOnTouchListener(new OnTouchListenerhelp());
//		this.menu_alerts.setOnTouchListener(new OnTouchListeneralerts());
		
		
		linerMenu = (LinearLayout)findViewById(R.id.linerMenu);
		btnMenu = (Button)findViewById(R.id.btnMenu);
		btnMenu.setOnClickListener(this);
		btnCloseMenu = (Button)findViewById(R.id.btnCloseMenu);
		btnCloseMenu.setOnClickListener(this);	
		
		
		labAbout = (TextView)findViewById(R.id.labAbout);
		labAbout.setOnClickListener(this);	
		
		labContact = (TextView)findViewById(R.id.labContact);
		labContact.setOnClickListener(this);	
		
		labPrivacy = (TextView)findViewById(R.id.labPrivacy);
		labPrivacy.setOnClickListener(this);	
		labSos = (TextView)findViewById(R.id.labSos);
		labSos.setOnClickListener(this);	
		
		labMessage = (TextView)findViewById(R.id.labMessage);
		labMessage.setOnClickListener(this);	
		labMM = (TextView)findViewById(R.id.labMM);
		labMM.setOnClickListener(this);	
		labGPSSetting = (TextView)findViewById(R.id.labGPSSetting);
		labGPSSetting.setOnClickListener(this);	
		labFall = (TextView)findViewById(R.id.labFall);
		labFall.setOnClickListener(this);	
		labTone = (TextView)findViewById(R.id.labTone);
		labTone.setOnClickListener(this);	
		labLow = (TextView)findViewById(R.id.labLow);
		labLow.setOnClickListener(this);	
		labAuto = (TextView)findViewById(R.id.labAuto);
		labAuto.setOnClickListener(this);	
		labLanguage = (TextView)findViewById(R.id.labLanguage);
		labLanguage.setOnClickListener(this);	
		labalert = (TextView)findViewById(R.id.labalert);
		labalert.setOnClickListener(this);	
		labHelp = (TextView)findViewById(R.id.labHelp);
		labHelp.setOnClickListener(this);	
		
		
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    desname = uiState.getString("destname","" );
 	    password = "";
 	    this.device_name.setText(desname);
 	    
  	   falldetectstate = uiState.getInt("fallstate", 0);
  	   if(falldetectstate == 0)
  	   {
  		  this.fall_detect_state.setImageResource(R.drawable.app_home_disable);
  	   }
  	   else if(falldetectstate == 1)
  	   {
  		  this.fall_detect_state.setImageResource(R.drawable.app_home_enable);
  	   }
  	   
 	   lowbatterystate = uiState.getInt("lowbatterystate", 0);
  	   if(lowbatterystate == 0)
  	   {
  		  this.low_battery_state.setImageResource(R.drawable.app_home_disable);
  	   }
  	   else if(lowbatterystate == 1)
  	   {
  		  this.low_battery_state.setImageResource(R.drawable.app_home_enable);
  	   }
	    geofence_status = uiState.getString("geofencesetting", "0");

	    if(geofence_status.equals("0")){
	    	this.geofence_switch.setImageResource(R.drawable.app_home_disable);
	    }else if(geofence_status.equals("1")){
	    	this.geofence_switch.setImageResource(R.drawable.app_home_enable); 	    	
	    }

	}
	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    desname = uiState.getString("destname","" );
 	    //password = uiState.getString("password", "9999"); 
 	    this.device_name.setText(desname);
 	    
		super.onRestart();
	}
	
	public void onBackPressed() { 
        new AlertDialog.Builder(this).setTitle(getResources().getString( R.string.Confirm_Exit))
            .setIcon(android.R.drawable.ic_dialog_info) 
            .setPositiveButton(getResources().getString( R.string.YES), new DialogInterface.OnClickListener() { 
         
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
	                // �����ȷ�ϡ���Ĳ��� 
                	MainMenuSetting.this.finish(); 
                } 
            }) 
            .setNegativeButton(getResources().getString( R.string.NO), new DialogInterface.OnClickListener() { 
         
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                	// ��������ء���Ĳ���,���ﲻ����û���κβ��� 
                } 
            }).show(); 
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(Menu.NONE,Menu.FIRST+1,1,getResources().getString( R.string.About));
		menu.add(Menu.NONE,Menu.FIRST+2,2,getResources().getString( R.string.Contact_Us));
		menu.add(Menu.NONE,Menu.FIRST+3,3,getResources().getString( R.string.Privacy___Terms));
		menu.add(Menu.NONE,Menu.FIRST+4,4,getResources().getString( R.string.Help));
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case Menu.FIRST+1:
			//Toast.makeText(MainMenuSetting.this, "Select About.", Toast.LENGTH_SHORT).show();
		break;
		
		case Menu.FIRST+2:
			//Toast.makeText(MainMenuSetting.this, "Select Contact us.", Toast.LENGTH_SHORT).show();
		break;
		
		case Menu.FIRST+3:
			//Toast.makeText(MainMenuSetting.this, "Select Privacy & Terms.", Toast.LENGTH_SHORT).show();
		break;
		
		case Menu.FIRST+4:
			//Toast.makeText(MainMenuSetting.this, "Select Help.", Toast.LENGTH_SHORT).show();
		break;
		}
		
		return false;
	}

	private class OnClickListenerGeofenceEnable implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			if(destnumber.equals("00000000000")){
				Toast.makeText(MainMenuSetting.this, "Please Add one device first.", Toast.LENGTH_SHORT).show();
				return;
			}
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if (geofence_status.equals("0")) {
	  			geofence_status = "1";
	  			MainMenuSetting.this.geofence_switch.setImageResource(R.drawable.app_home_enable);
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWGEOFENCE#1#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MainMenuSetting.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		} else if(geofence_status.equals("1")) {
	  			geofence_status = "0";
	  			MainMenuSetting.this.geofence_switch.setImageResource(R.drawable.app_home_disable);

				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWGEOFENCE#0#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MainMenuSetting.this,  R.string.Success, Toast.LENGTH_SHORT).show();
	  		}
	  		et.putString("geofencesetting", geofence_status);
	  		et.commit();
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		if (intent.hasExtra("language")){
			finish();
			Intent i = new Intent(this, MainMenuSetting.class);
			intent.putExtra("language","language");
			startActivity(i);
			return;
		}
	}

	
	private class OnClickListenerfalldetect implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

			if(destnumber.equals("00000000000")){
				Toast.makeText(MainMenuSetting.this,  R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
				return;
			}
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if(falldetectstate == 0)
	  		{
	  			falldetectstate = 1;
	  			MainMenuSetting.this.fall_detect_state.setImageResource(R.drawable.app_home_enable);
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWFALL#1#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MainMenuSetting.this, R.string.Success, Toast.LENGTH_SHORT).show();
				
	  		}
	  		else if(falldetectstate == 1)
	  		{
	  			falldetectstate = 0;
	  			MainMenuSetting.this.fall_detect_state.setImageResource(R.drawable.app_home_disable);
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWFALL#0#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MainMenuSetting.this, R.string.Success, Toast.LENGTH_SHORT).show();
	  		}
	  		
	  		et.putInt("fallstate", falldetectstate);
	  		et.commit();
		}
		
	}
	
	private class OnClickListenerlowbattery implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			if(destnumber.equals("00000000000")){
				Toast.makeText(MainMenuSetting.this, "Please Add one device first.", Toast.LENGTH_SHORT).show();
				return;
			}

			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if(lowbatterystate == 0)
	  		{
	  			lowbatterystate = 1;
	  			MainMenuSetting.this.low_battery_state.setImageResource(R.drawable.app_home_enable);
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWLOWBATT#1#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MainMenuSetting.this,  R.string.Success, Toast.LENGTH_SHORT).show();
				
	  		}
	  		else if(lowbatterystate == 1)
	  		{
	  			lowbatterystate = 0;
	  			MainMenuSetting.this.low_battery_state.setImageResource(R.drawable.app_home_disable);
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWLOWBATT#0#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(MainMenuSetting.this,  R.string.Success, Toast.LENGTH_SHORT).show();
	  		}
	  		
	  		et.putInt("lowbatterystate", lowbatterystate);
	  		et.commit();
		}
	}

	//sos number
	private class OnTouchListenersosnumber implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_sos_number.setImageResource(R.drawable.app_home_sos_number_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_sos_number.setImageResource(R.drawable.app_home_sos_number);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,Sosnumber.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_sos_number.setImageResource(R.drawable.app_home_sos_number);
				////MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
	}
	
	//m1m2
	private class OnTouchListenerm1m2 implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_m1m2.setImageResource(R.drawable.app_home_m1m2_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_m1m2.setImageResource(R.drawable.app_home_m1m2);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,MMset.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_m1m2.setImageResource(R.drawable.app_home_m1m2);
				////MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//sos message
	private class OnTouchListenersosmessage implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_sos_message.setImageResource(R.drawable.app_home_sos_sms_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_sos_message.setImageResource(R.drawable.app_home_sos_sms);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,SosMessage.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_sos_message.setImageResource(R.drawable.app_home_sos_sms);
				////MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//gps
	private class OnTouchListenergps implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_gps.setImageResource(R.drawable.app_home_gps_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_gps.setImageResource(R.drawable.app_home_gps);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,gpsapp.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_gps.setImageResource(R.drawable.app_home_gps);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//tone profile
	private class OnTouchListenertoneprofile implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_tone_profile.setImageResource(R.drawable.app_home_profile_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_tone_profile.setImageResource(R.drawable.app_home_profile);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,RingToneset.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_tone_profile.setImageResource(R.drawable.app_home_profile);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//fall detect
	private class OnTouchListenerfalldetect implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_fall_detect.setImageResource(R.drawable.app_home_fall_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_fall_detect.setImageResource(R.drawable.app_home_fall);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,FallDetectset.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_fall_detect.setImageResource(R.drawable.app_home_fall);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//low battery
	private class OnTouchListenerlowbattery implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_low_battery.setImageResource(R.drawable.app_home_low_battery_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_low_battery.setImageResource(R.drawable.app_home_low_battery);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,LowBatteryset.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_low_battery.setImageResource(R.drawable.app_home_low_battery);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//eci
	private class OnTouchListenereci implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_eci.setImageResource(R.drawable.app_home_answer_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_eci.setImageResource(R.drawable.app_home_answer);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,AutoAnswerset.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_eci.setImageResource(R.drawable.app_home_answer);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//device check
	private class OnTouchListenerdevicecheck implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_device_check.setImageResource(R.drawable.app_home_device_check_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_device_check.setImageResource(R.drawable.app_home_device_check);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,PeriodicCheckset.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_device_check.setImageResource(R.drawable.app_home_device_check);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//language
	private class OnTouchListenerlanguage implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_language.setImageResource(R.drawable.app_home_language_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_language.setImageResource(R.drawable.app_home_language);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,Languageset.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_language.setImageResource(R.drawable.app_home_language);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
	}
	
	//help
	private class OnTouchListenerhelp implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_help.setImageResource(R.drawable.app_home_help_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_help.setImageResource(R.drawable.app_home_help);
				break;
				
			case MotionEvent.ACTION_UP:
//				Intent it = new Intent(MainMenuSetting.this,Sosnumber.class);//ʵ��Intent
//				it.putExtra("myinfosub", "entry sub menu");//Key
//				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_help.setImageResource(R.drawable.app_home_help);
				break;
				
			}
			return false;
		}
		
	}
	
	//alerts notification
	private class OnTouchListeneralerts implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.menu_alerts.setImageResource(R.drawable.app_home_alert_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.menu_alerts.setImageResource(R.drawable.app_home_alert);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,AlertNotification.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.menu_alerts.setImageResource(R.drawable.app_home_alert);
				//MainMenuSetting.this.finish();
				break;
				
			}
			return false;
		}
		
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
				//MainMenuSetting.this.add_device.setImageResource(R.drawable.app_add_device_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.add_device.setImageResource(R.drawable.app_add_device);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,Adddevice.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry add device");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.add_device.setImageResource(R.drawable.app_add_device);
				break;
				
			}
			return false;
		}
		
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
				//MainMenuSetting.this.switch_device.setImageResource(R.drawable.app_add_switch_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.switch_device.setImageResource(R.drawable.app_add_switch);
				break;
				
			case MotionEvent.ACTION_UP:
				Intent it = new Intent(MainMenuSetting.this,Switchdevice.class);//ʵ��Intent
				it.putExtra("myinfosub", "entry switch device");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.switch_device.setImageResource(R.drawable.app_add_switch);
				break;
				
			}
			return false;
		}
		
	}
	
	//find location
	private class OnTouchListenerfindlocation implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
//				MainMenuSetting.this.find_location.setImageResourc(R.drawable.app_find_location_press);
				break;

			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.find_location.setImageResource(R.drawable.app_find_location);
				break;

			case MotionEvent.ACTION_UP:

				
				if(destnumber.equals("00000000000")){
					Toast.makeText(MainMenuSetting.this,  R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
					
				}
				else {
					String smstel = destnumber;//����

					String smsnote1 = "GET"+password+"#GPSINFO#GPSRANGE#SWGEOFENCE#SWGPS#AGPS#GEOFENCEPHONE#";//
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
					
				}

				
				Intent it = new Intent(MainMenuSetting.this,GetGpsApp.class);//ʵ��Intent
//				it.putExtra("myinfosub", "entry sub menu");//Key
				MainMenuSetting.this.startActivity(it);
				//MainMenuSetting.this.find_location.setImageResource(R.drawable.app_find_location);
				break;
				
			}
			return false;
		}
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.btnMenu:
				linerMenu.setVisibility(View.VISIBLE);
				break;
			case R.id.btnCloseMenu:
				linerMenu.setVisibility(View.GONE);
				break;	
				
			case R.id.labAbout:
				//linerMenu.setVisibility(View.VISIBLE);
				break;
			case R.id.labContact:
				//linerMenu.setVisibility(View.VISIBLE);
				break;
			case R.id.labPrivacy:
				//linerMenu.setVisibility(View.VISIBLE);
				break;
			case R.id.labSos:
				startActivity(new Intent(this,Sosnumber.class));
				break;
			case R.id.labMessage:
				startActivity(new Intent(this,SosMessage.class));
				break;
			case R.id.labMM:
				startActivity(new Intent(this,MMset.class));
				break;
			case R.id.labGPSSetting:
				startActivity(new Intent(this,gpsapp.class));
				break;
			case R.id.labFall:
				startActivity(new Intent(this,FallDetectset.class));
				break;
			case R.id.labTone:
				startActivity(new Intent(this,RingToneset.class));
				break;
			case R.id.labLow:
				startActivity(new Intent(this,LowBatteryset.class));
				break;
			case R.id.labAuto:
				startActivity(new Intent(this,AutoAnswerset.class));
				break;
			case R.id.labLanguage:
				startActivity(new Intent(this,Languageset.class));
				break;
			case R.id.labalert:
				//startActivity(new Intent(this,MMset.class));
				break;
			case R.id.labHelp:
				//startActivity(new Intent(this,MMset.class));
				break;
			
				
				
		}
		
	}
	
	
	
	//call now
	private class OnTouchListenercallnow implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				//MainMenuSetting.this.call_now.setImageResource(R.drawable.app_call_now_press);
				break;
				
			case MotionEvent.ACTION_MOVE:
				//MainMenuSetting.this.call_now.setImageResource(R.drawable.app_call_now);
				break;
				
			case MotionEvent.ACTION_UP:
				if(!destnumber.equals("00000000000")){
					Intent it = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+destnumber));//ʵ��Intent
					it.putExtra("myinfosub", "entry sub menu");//Key
					MainMenuSetting.this.startActivity(it);
				}else{
					Toast.makeText(MainMenuSetting.this,  R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
				}
				break;
			}
			return false;
		}
		
	}
	
}

