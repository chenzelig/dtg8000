package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
//import android.annotation.SuppressLint;
import android.graphics.Color;
//import android.os.Handler;
//import android.os.Message;

public class gpsapp extends FragmentActivity  implements OnClickListener , OnSeekBarChangeListener,
OnMarkerDragListener, OnMapLongClickListener, OnMapReadyCallback  {
	
	
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private RadioGroup group1 = null;
	
	private RadioButton gps = null;
	private RadioButton userplane = null;
	private RadioButton controlplane = null;

	private String agps_status = null;
	private String agps_mode_pre = null;
	private String agps_mode = null;
	
	private ImageView agps_switch = null;
	private TextView agps_text = null;
	
	private String geofence_status  = null;
	private TextView geofence_text = null;
	private ImageView geofence_switch = null;

	private String agps_gpsuatoreport_status  = null;
	private TextView agps_gpsuatoreport = null;
	private ImageView agps_gpsuatoreport_status_switch = null;
	
	
	private NumberSeekBar range_seek = null;
	private SeekBar volume_seek = null;
	private String ringvolume = null;
	private String ringvolumebak = null;
	//private NumberSeekBar pb=null;
	private String geofence_range = null;
	
	private Button btnBack;
	
	private GoogleMap mMap;
	
	private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

	public LatLng lastpoint = null;
	
	
	private EditText geofencenumber_edit;
	
	private LocationManager locationManager; 
	private String locationProvider;  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.gpsapp);
		
		try{
			geofencenumber_edit = (EditText)findViewById(R.id.geofencenumber_edit);
			
			SupportMapFragment mapFragment =
	                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			mapFragment.getMapAsync(this);
			
			this.volume_seek = (SeekBar)super.findViewById(R.id.volume_seek);
			
			//��̬ע����ն�����Ϣ
			IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
			//����������ȼ�
	        localIntentFilter.setPriority(Integer.MAX_VALUE);
	        smsReceiver = new SmsReceiver();
	        registerReceiver(smsReceiver, localIntentFilter);
	        
	        btnBack = (Button)findViewById(R.id.btnBack);
			btnBack.setOnClickListener(this);
			
	 	    //ʵ��
			this.set_enable = (Button)super.findViewById(R.id.set_enable);
			this.set_enable.setOnClickListener(new OnClickListenerGpsSetenble());
			this.get_disable = (Button)super.findViewById(R.id.get_disable);
			this.get_disable.setOnClickListener(new OnClickListenerGpsGetdisable());
		    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			this.get_disable.setBackgroundResource(R.drawable.buttonform2);
			
			this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
			this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
			
			this.group1 = (RadioGroup)super.findViewById(R.id.group1);
			this.group1.setOnCheckedChangeListener(new MyRadioGroupOnCheckedChangedListener());
			
			this.gps = (RadioButton)super.findViewById(R.id.gps);
			this.userplane = (RadioButton)super.findViewById(R.id.userplane);
			this.controlplane = (RadioButton)super.findViewById(R.id.controlplane);
	
			this.agps_text = (TextView)super.findViewById(R.id.agps_status_name);
			this.agps_switch = (ImageView)super.findViewById(R.id.agps_status);
			this.agps_switch.setOnClickListener(new OnClickListenerAgpsEnable());
	
			this.geofence_text = (TextView)super.findViewById(R.id.geofence_status_name);
			this.geofence_switch = (ImageView)super.findViewById(R.id.geofence_status);
			this.geofence_switch.setOnClickListener(new OnClickListenerGeofenceEnable());
			
			this.agps_gpsuatoreport = (TextView)super.findViewById(R.id.agps_gpsuatoreport);
			this.agps_gpsuatoreport_status_switch = (ImageView)super.findViewById(R.id.agps_gpsuatoreport_status);
			this.agps_gpsuatoreport_status_switch.setOnClickListener(new OnClickListenerAutoReportEnable());
	
			//this.range_seek = (SeekBar)super.findViewById(R.id.range_seek);
			//this.range_seek.setOnSeekBarChangeListener(new OnSeekBarChangeListenerRange());
	
	        //���ز���
	 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	 	    destnumber = uiState.getString("destnumber","00000000000" );
	 	    password = "";
	 	    
	 	    agps_status = uiState.getString("agpssetting", "0");
	
	 	    
	 	   geofencenumber_edit.setText(uiState.getString("geofencenumber", ""));
	 	    
	 	    if(agps_status.equals("0")){
	 	    	this.agps_text.setText(getResources().getString(R.string.Disable));
	 	    	this.agps_switch.setImageResource(R.drawable.app_home_disable);
	 	    }else if(agps_status.equals("1")){
	 	    	this.agps_text.setText(getResources().getString(R.string.Enable));
	 	    	this.agps_switch.setImageResource(R.drawable.app_home_enable); 	    	
	 	    }
	
	 	    geofence_status = uiState.getString("geofencesetting", "0");
	
	 	    if(geofence_status.equals("0")){
	 	    	this.geofence_text.setText(getResources().getString(R.string.Disable));
	 	    	this.geofence_switch.setImageResource(R.drawable.app_home_disable);
	 	    }else if(geofence_status.equals("1")){
	 	    	this.geofence_text.setText(getResources().getString(R.string.Enable));
	 	    	this.geofence_switch.setImageResource(R.drawable.app_home_enable); 	    	
	 	    }
	 	    
	 	   agps_gpsuatoreport_status = uiState.getString("agps_gpsuatoreport_setting", "0");
	
	 	    if(agps_gpsuatoreport_status.equals("0")){
	 	    	this.agps_gpsuatoreport.setText(getResources().getString(R.string.Disable));
	 	    	this.agps_gpsuatoreport_status_switch.setImageResource(R.drawable.app_home_disable);
	 	    }else if(agps_gpsuatoreport_status.equals("1")){
	 	    	this.agps_gpsuatoreport.setText(getResources().getString(R.string.Enable));
	 	    	this.agps_gpsuatoreport_status_switch.setImageResource(R.drawable.app_home_enable); 	    	
	 	    } 	    
	 	    
	 	    
	 	    agps_mode = uiState.getString("gpsmode","");
	 	    agps_mode_pre = agps_mode;
	 	    if(agps_mode.equals("0")){
	 	    	this.gps.setChecked(true);
	 	    } else if(agps_mode.equals("1")){
	 	    	this.userplane.setChecked(true);
	 	    } else if(agps_mode.equals("2")){
	 	    	this.controlplane.setChecked(true);
	 	    }
	 	    
	 	   range_seek = (NumberSeekBar)findViewById(R.id.bar0);
	 	   init();
	 	   range_seek.setOnSeekBarChangeListener(new OnSeekBarChangeListenerRange());
	 	  range_seek.setProgress(100);
	 	   //start();
	 	   geofence_range = uiState.getString("geofencerange","0");
	 	  
	 	   range_seek.setProgress(Integer.parseInt(geofence_range));
	 	   
	 	   
	 	   
	 	   //获取地理位置管理器  
	       locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	       
	       //获取所有可用的位置提供器  
	       List<String> providers = locationManager.getProviders(true);  
	       
	//       for(String s : providers)
	//       {
	//    	   Toast.makeText(this, s+"  _xxx", Toast.LENGTH_SHORT).show();
	//       }
	       
	       if(providers.contains(LocationManager.GPS_PROVIDER)){  
	           //如果是GPS  
	           locationProvider = LocationManager.GPS_PROVIDER;  
	       }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){  
	           //如果是Network  
	           locationProvider = LocationManager.NETWORK_PROVIDER;  
	       }else if(providers.contains(LocationManager.PASSIVE_PROVIDER)){  
	           //如果是GPS  
	           locationProvider = LocationManager.PASSIVE_PROVIDER;
	       }       
	       else{  
	           Toast.makeText(this, "no location service", Toast.LENGTH_SHORT).show();  
	           //return ;  
	       }  
	       
	       
	       if(locationProvider!= null && locationProvider.length()>0)
	       {
	    	 //获取Location  
	           Location location = locationManager.getLastKnownLocation(locationProvider);  
	           if(location!=null){  
	               //不为空,显示地理位置经纬度  
	               showLocation(location);  
	           }  
	           //监视地理位置变化  
	           locationManager.requestLocationUpdates(locationProvider, 2000, 1, locationListener);
	       }
	       
	       
	       
	       
	       //ring volume
		    ringvolume = uiState.getString("autoreport_volume_seek", "");
		    if(ringvolume.equals("")){
		    	ringvolume = "1";
		    }
		    this.volume_seek.setProgress(Integer.parseInt(ringvolume));
		}
		catch(Exception ex)
		{
			Utility.showToast(this, "has error in log", Toast.LENGTH_LONG);
			Log.e("error", ex.toString());
		}
 	   
	}
	
	private double latitude=0.0;  
	private double longitude =0.0; 
	
	/** 
     * 显示地理位置经度和纬度信息 
     * @param location 
     */  
    private void showLocation(Location location){  
        String locationStr = "Latitude：" + location.getLatitude() +"\n"   
                + "Longitude：" + location.getLongitude();  
        //postionView.setText(locationStr); 
        if(mMap!=null)
        {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(new LatLng( location.getLatitude(), location.getLongitude())).title("Marker"));
    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15.0f));
        }
       // Utility.showToast(gpsapp.this, locationStr, Toast.LENGTH_LONG);
    }  
    
    /** 
     * LocationListern监听器 
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器 
     */  
      
    LocationListener locationListener =  new LocationListener() {  
          
        @Override  
        public void onStatusChanged(String provider, int status, Bundle arg2) {  
              
        }  
          
        @Override  
        public void onProviderEnabled(String provider) {  
              
        }  
          
        @Override  
        public void onProviderDisabled(String provider) {  
              
        }  
          
        @Override  
        public void onLocationChanged(Location location) {  
            //如果位置发生变化,重新显示  
            showLocation(location);  
              
        }  
    };  
	

	
	
	
    private void init() {
        range_seek.setTextSize(20);// ���������С
        range_seek.setTextColor(Color.WHITE);// ��ɫ
        range_seek.setMyPadding(10, 10, 10, 10);// ����padding ����setpadding����Ч
        range_seek.setImagePadding(0, 1);// ���Բ�����
        range_seek.setTextPadding(0, 0);// ���Բ�����        
    }

	//seekbar for volume
	private class OnSeekBarChangeListenerRange implements SeekBar.OnSeekBarChangeListener
	{
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

			if(destnumber.equals("00000000000")){
				Toast.makeText(gpsapp.this, R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
				return;
			}

			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		SmsManager smsmanager = SmsManager.getDefault();
			
	  		final int range_var = seekBar.getProgress();
			et.putString("geofencerange", Integer.toString(range_var));
			et.commit();
			
//			String smstel = destnumber;//����
//			String smsnote = "SET"+password+"#GPSRANGE#"+Integer.toString(range_var)+"#";//���
//			
//			smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//			Toast.makeText(gpsapp.this, "Setting range to "+Integer.toString(range_var), Toast.LENGTH_SHORT).show();
		}
	}

	private class MyRadioGroupOnCheckedChangedListener implements OnCheckedChangeListener 
	{
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(destnumber.equals("00000000000")){
				Toast.makeText(gpsapp.this, R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
				return;
			}
	        if(checkedId == gps.getId()) {
	        	agps_mode = "0";
	        	if(agps_mode.equals(agps_mode_pre)){
	        		return;
	        	} else {
	        		agps_mode_pre = agps_mode;
	        	}
	        	//save data
	        	SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        	Editor et=uiState.edit();
	        	et.putString("gpsmode", "0");
	        	et.commit();
		        
//	        	String smstel = destnumber;//����
//		        String smsnote = "SET"+password+"#AGPS#0#";//���
//		        SmsManager smsmanager = SmsManager.getDefault();
//		        smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//		        Toast.makeText(gpsapp.this, "Setting GPS to GPS.", Toast.LENGTH_SHORT).show();
		   } else if(checkedId == userplane.getId()) {
	        	agps_mode = "1";
	        	if(agps_mode.equals(agps_mode_pre)){
	        		return;
	        	} else {
	        		agps_mode_pre = agps_mode;
	        	}

			   //save data
			   SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			   Editor et=uiState.edit();
			   et.putString("gpsmode", "1");
			   et.commit();
	        		  		
//		        String smstel = destnumber;//����
//		        String smsnote = "SET"+password+"#AGPS#1#";//���
//		        SmsManager smsmanager = SmsManager.getDefault();
//		        smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//		        Toast.makeText(gpsapp.this, "Setting GPS to user plane", Toast.LENGTH_SHORT).show();
	       } else if(checkedId == controlplane.getId()) {
	        	agps_mode = "2";
	        	if(agps_mode.equals(agps_mode_pre)){
	        		return;
	        	} else {
	        		agps_mode_pre = agps_mode;
	        	}

			   //save data
			   SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			   Editor et=uiState.edit();
			   et.putString("gpsmode", "2");
			   et.commit();

//		       String smstel = destnumber;//����
//		       String smsnote = "SET"+password+"#AGPS#2#";//���
//		       SmsManager smsmanager = SmsManager.getDefault();
//		       smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//		       Toast.makeText(gpsapp.this, "Setting GPS to control plane", Toast.LENGTH_SHORT).show();
	       }
		}
	}
	
	private class OnClickListenerAgpsEnable implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			if(destnumber.equals("00000000000")){
				Toast.makeText(gpsapp.this, R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
				return;
			}
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if (agps_status.equals("0")) {
	  			agps_status = "1";
	  			gpsapp.this.agps_switch.setImageResource(R.drawable.app_home_enable);
	  			gpsapp.this.agps_text.setTextSize(18);
	  			gpsapp.this.agps_text.setText(getResources().getString(R.string.Enable));
	  			
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWAGPS#1#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(gpsapp.this, "Setting AGPS Enable.", Toast.LENGTH_SHORT).show();
	  		} else if(agps_status.equals("1")) {
	  			agps_status = "0";
	  			gpsapp.this.agps_switch.setImageResource(R.drawable.app_home_disable);
	  			gpsapp.this.agps_text.setTextSize(18);
	  			gpsapp.this.agps_text.setText(getResources().getString(R.string.Disable));
	  			
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWAGPS#0#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(gpsapp.this, "Setting AGPS Disable.", Toast.LENGTH_SHORT).show();
	  		}
	  		et.putString("agpssetting", agps_status);
	  		et.commit();
		}
	}

	private class OnClickListenerGeofenceEnable implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			if(destnumber.equals("00000000000")){
				Toast.makeText(gpsapp.this, R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
				return;
			}
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if (geofence_status.equals("0")) {
	  			geofence_status = "1";
	  			gpsapp.this.geofence_switch.setImageResource(R.drawable.app_home_enable);
	  			gpsapp.this.geofence_text.setTextSize(18);
	  			gpsapp.this.geofence_text.setText(getResources().getString(R.string.Enable));
	  			
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWGEOFENCE#1#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(gpsapp.this, "Setting Geofence Enable.", Toast.LENGTH_SHORT).show();
	  		} else if(geofence_status.equals("1")) {
	  			geofence_status = "0";
	  			gpsapp.this.geofence_switch.setImageResource(R.drawable.app_home_disable);
	  			gpsapp.this.geofence_text.setTextSize(18);
	  			gpsapp.this.geofence_text.setText(getResources().getString(R.string.Disable));
	  			
//				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWGEOFENCE#0#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(gpsapp.this, "Setting Geofence Disable.", Toast.LENGTH_SHORT).show();
	  		}
	  		et.putString("geofencesetting", geofence_status);
	  		et.commit();
		}
	}

	private class OnClickListenerAutoReportEnable implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			if(destnumber.equals("00000000000")){
				Toast.makeText(gpsapp.this, R.string.Please_Add_one_device_first, Toast.LENGTH_SHORT).show();
				return;
			}
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if (agps_gpsuatoreport_status.equals("0")) {
	  			agps_gpsuatoreport_status = "1";
	  			gpsapp.this.agps_gpsuatoreport_status_switch.setImageResource(R.drawable.app_home_enable);
	  			gpsapp.this.agps_gpsuatoreport.setTextSize(18);
	  			gpsapp.this.agps_gpsuatoreport.setText(getResources().getString(R.string.Enable));
	  			

	  		} else if(agps_gpsuatoreport_status.equals("1")) {
	  			agps_gpsuatoreport_status = "0";
	  			gpsapp.this.agps_gpsuatoreport_status_switch.setImageResource(R.drawable.app_home_disable);
	  			gpsapp.this.agps_gpsuatoreport.setTextSize(18);
	  			gpsapp.this.agps_gpsuatoreport.setText(getResources().getString(R.string.Disable));
	  			
	  		}
	  		et.putString("agps_gpsuatoreport_setting", agps_gpsuatoreport_status);
	  		et.commit();
		}
	}
	
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//d
			//SET_PW_#AGPS#X1#SWGPS#X2#SWGEOFENCE#X3##GPSRANGE#YYYY#
			int autoreport_time = 0;
			
			if(volume_seek.getProgress()-1==0)
			{
				autoreport_time = 5;
			}
			if(volume_seek.getProgress()-1==1)
			{
				autoreport_time = 15;
			}
			if(volume_seek.getProgress()-1==2)
			{
				autoreport_time = 30;
			}
			if(volume_seek.getProgress()-1==3)
			{
				autoreport_time = 60;
			}
			if(volume_seek.getProgress()-1==4)
			{
				autoreport_time = 120;
			}
			if(volume_seek.getProgress()-1==5)
			{
				autoreport_time = 240;
			}
			
			String smstel = destnumber;//����
			//String smsnote = "SET"+password+"#AGPS#"+agps_mode+"#SWGPS#"+agps_status+"#SWGEOFENCE#"+geofence_status+"#GPSRANGE#"+(String.valueOf(range_seek.getProgress()))  +"#HOMESITE#"+(lastpoint.latitude >0 ? lastpoint.latitude : Math.abs(lastpoint.latitude))+","+(lastpoint.longitude >0 ? lastpoint.longitude : Math.abs(lastpoint.longitude))  +"#GEOFENCEPHONE#"+geofencenumber_edit.getText().toString()+"#";//���
			String smsnote ="";
			if(lastpoint!=null)
			{
				smsnote = "SET"+password+"#AGPS#"+agps_mode+"#SWGPS#"+agps_status+"#SWGEOFENCE#"+geofence_status+"#SWGPSREPORT#"+agps_gpsuatoreport_status+"#GPSRANGE#"+(String.valueOf(range_seek.getProgress())) +"#GPSREPORTTIME#"+(String.valueOf(autoreport_time))  +"#HOMESITE#"+lastpoint.latitude+","+lastpoint.longitude  +"#GEOFENCEPHONE#"+geofencenumber_edit.getText().toString()+"#";//���
			}
			else
			{
				smsnote = "SET"+password+"#AGPS#"+agps_mode+"#SWGPS#"+agps_status+"#SWGEOFENCE#"+geofence_status+"#SWGPSREPORT#"+agps_gpsuatoreport_status+"#GPSRANGE#"+(String.valueOf(range_seek.getProgress()))  +"#GPSREPORTTIME#"+(String.valueOf(autoreport_time)) +"#HOMESITE#0,0#GEOFENCEPHONE#"+geofencenumber_edit.getText().toString()+"#";//���
			}
			
			
			
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
			Toast.makeText(gpsapp.this, R.string.Success , Toast.LENGTH_SHORT).show();
			

			
		   SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		   Editor et=uiState.edit();
		   et.putString("geofencenumber", geofencenumber_edit.getText().toString());
		   et.putString("autoreport_volume_seek", String.valueOf(volume_seek.getProgress() ));
		   et.putString("autoreport_time", String.valueOf(autoreport_time ));
		   et.putString("agps_gpsuatoreport_status", String.valueOf(agps_gpsuatoreport_status ));
		   et.commit();
		   
		   
			   
			
			Intent it = new Intent(gpsapp.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			gpsapp.this.startActivity(it);
			gpsapp.this.finish();
		}
	}
	
	//����Ϣ���ղ������ж�
	private class SmsReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){  
	        	Log.v("huahua", "MainActivity�յ����ŷ�������Ϣ");  
	        	
	            Bundle bundle = intent.getExtras();  
	            SmsMessage msg = null;  
	            if (null != bundle) 
	            {  
	                Object[] smsObj = (Object[]) bundle.get("pdus");  
	                for (Object object : smsObj) 
	                {  
	                    msg = SmsMessage.createFromPdu((byte[]) object);  
	                    //Date date = new Date(msg.getTimestampMillis());//ʱ��  
	                    //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	                    //String receiveTime = format.format(date);  
	                    System.out.println("number:" + msg.getOriginatingAddress()  
	                    + "   body:" + msg.getDisplayMessageBody() + "  time:"  
	                            + msg.getTimestampMillis());  
	                      
	                    //String numbertemp = msg.getOriginatingAddress();
	                    String msgtemp = msg.getDisplayMessageBody();
	                    //������д�Լ����߼�  
	                    //if (msg.getOriginatingAddress().equals("+8618003049659")) 
	                    if (msgtemp.contains("+AGPS")||msgtemp.contains("+GPSRANGE")||msgtemp.contains("+SWAGPS")||msgtemp.contains("+SWGEOFENCE")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(gpsapp.this, R.string.Success, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();
	                    }
	                }
	            }
	        }  
		}
	}

	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(gpsapp.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		gpsapp.this.startActivity(it);
		gpsapp.this.finish();
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
		if(locationManager!=null){  
            //移除监听器  
            locationManager.removeUpdates(locationListener);  
        }  
	}
	
	//set enable
	private class OnClickListenerGpsSetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			gpsapp.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			gpsapp.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenerGpsGetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(gpsapp.this,GetGpsApp.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get gps");//Key
			gpsapp.this.startActivity(it);
			gpsapp.this.finish();
		}
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

	
	
	
	@Override
	public void onMapReady(GoogleMap map) {
		// TODO Auto-generated method stub
		mMap = map;

        // Override the default content description on the view, for accessibility mode.
        map.setContentDescription("");

        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);

       
        LatLng SYDNEY = new LatLng(latitude, longitude);
//        DraggableCircle circle = new DraggableCircle(SYDNEY, DEFAULT_RADIUS);
//        mCircles.add(circle);

        // Move the map so that it is centered on the initial circle
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15.0f));
	}

	private List<DraggableCircle> mCircles = new ArrayList<DraggableCircle>(1);
	@Override
	public void onMapLongClick(LatLng point) {
		// TODO Auto-generated method stub
		// We know the center, let's place the outline at a point 3/4 along the view.
        View view = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getView();
//        LatLng radiusLatLng = mMap.getProjection().fromScreenLocation(new Point(
//                view.getHeight() * 3 / 4, view.getWidth() * 3 / 4));

       // Log.d("xxx", point.latitude+","+point.longitude);
        
        mMap.clear();
        mCircles.clear();
        // ok create it
        DraggableCircle circle = new DraggableCircle(point, range_seek.getProgress());
        mCircles.add(circle);
	}

	@Override
	public void onMarkerDrag(Marker marker) {
		// TODO Auto-generated method stub
		onMarkerMoved(marker);
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		// TODO Auto-generated method stub
		onMarkerMoved(marker);
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		// TODO Auto-generated method stub
		onMarkerMoved(marker);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		for (DraggableCircle draggableCircle : mCircles) {
            draggableCircle.onStyleChange();
        }
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
	private void onMarkerMoved(Marker marker) {
        for (DraggableCircle draggableCircle : mCircles) {
            if (draggableCircle.onMarkerMoved(marker)) {
                break;
            }
        }
    }
	
	private static final double DEFAULT_RADIUS = 1000;

    public static final double RADIUS_OF_EARTH_METERS = 6371009;

    private static final int WIDTH_MAX = 50;

    private static final int HUE_MAX = 360;

    private static final int ALPHA_MAX = 255;
	
	 /** Generate LatLng of radius marker */
    private static LatLng toRadiusLatLng(LatLng center, double radius) {
        double radiusAngle = Math.toDegrees(radius / RADIUS_OF_EARTH_METERS) /
                Math.cos(Math.toRadians(center.latitude));
        return new LatLng(center.latitude, center.longitude + radiusAngle);
    }

    private static double toRadiusMeters(LatLng center, LatLng radius) {
        float[] result = new float[1];
        Location.distanceBetween(center.latitude, center.longitude,
                radius.latitude, radius.longitude, result);
        return result[0];
    }
    
	
	 private class DraggableCircle {

	        private final Marker centerMarker;

	        //private final Marker radiusMarker;

	        private final Circle circle;

	        private double radius;

	        public DraggableCircle(LatLng center, double radius) {
	            this.radius = radius;
	            centerMarker = mMap.addMarker(new MarkerOptions()
	                    .position(center)
	                    .draggable(true));
//	            radiusMarker = mMap.addMarker(new MarkerOptions()
//	                    .position(toRadiusLatLng(center, radius))
//	                    .draggable(true)
//	                    .icon(BitmapDescriptorFactory.defaultMarker(
//	                            BitmapDescriptorFactory.HUE_AZURE)));
	            
	            circle = mMap.addCircle(new CircleOptions()
	                    .center(center)
	                    .radius(radius)
	                    .strokeWidth(5)
	                    .strokeColor(Color.RED)
	                    .fillColor(Color.TRANSPARENT));
	        }

	        public DraggableCircle(LatLng center, LatLng radiusLatLng) {
	            this.radius = toRadiusMeters(center, radiusLatLng);
	            centerMarker = mMap.addMarker(new MarkerOptions()
	                    .position(center)
	                    .draggable(true));
//	            radiusMarker = mMap.addMarker(new MarkerOptions()
//	                    .position(radiusLatLng)
//	                    .draggable(true)
//	                    .icon(BitmapDescriptorFactory.defaultMarker(
//	                            BitmapDescriptorFactory.HUE_AZURE)));
	            circle = mMap.addCircle(new CircleOptions()
	                    .center(center)
	                    .radius(radius)
	                    .strokeWidth(5)
	                    .strokeColor(Color.RED)
	                    .fillColor(Color.TRANSPARENT));
	        }

	        public boolean onMarkerMoved(Marker marker) {
	            if (marker.equals(centerMarker)) {
	                circle.setCenter(marker.getPosition());
	               // radiusMarker.setPosition(toRadiusLatLng(marker.getPosition(), radius));
	                return true;
	            }
//	            if (marker.equals(radiusMarker)) {
//	                radius = toRadiusMeters(centerMarker.getPosition(), radiusMarker.getPosition());
//	                circle.setRadius(radius);
//	                return true;
//	            }
	            return false;
	        }

	        public void onStyleChange() {
	            circle.setStrokeWidth(range_seek.getProgress());
	            circle.setFillColor(Color.TRANSPARENT);
	            circle.setStrokeColor(Color.RED);
	        }
	    }
	 
	 
	
	
}
