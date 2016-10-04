package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.BroadcastReceiver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
//import android.os.PatternMatcher;
//import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnLongClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GetGpsApp  extends FragmentActivity implements OnMapReadyCallback , OnClickListener {
	private SmsReceiver smsReceiver;
	
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private String destnumber = null;
	private String password = null;

	private String location = null;
	private String agps = null;
	private String gps_mode = null;
	private String geofence = null;
	private String geofence_range = null;
	
	private String autoreport = null;
	private String autoreport_time = null;
	
	private TextView get_location = null;
	private TextView get_agps = null;
	private TextView get_gps_mode = null;
	private TextView get_geofence = null;
	private TextView get_geofence_range = null;
	private TextView get_geofence_number = null;
	
	private TextView gpsAutoReport = null;
	private TextView gpsAutoreportTime = null;	
	
	private Button btnBack;
	private GoogleMap mMap;
	
	private TextView tvlcationUrl;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.getgpsapp);
		
		try {
			Log.i("log:", "begin log");
		
			 btnBack = (Button)findViewById(R.id.btnBack);
				btnBack.setOnClickListener(this);
				
				gpsAutoReport = (TextView)findViewById(R.id.gpsAutoReport);
				gpsAutoreportTime = (TextView)findViewById(R.id.gpsAutoreportTime);
				
				get_geofence_number = (TextView)findViewById(R.id.get_geofence_number);
				tvlcationUrl = (TextView)findViewById(R.id.tvlcationUrl);		
			
				SupportMapFragment mapFragment =
		                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		        mapFragment.getMapAsync(this);
				
			//��̬ע����ն�����Ϣ
			IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
			//����������ȼ�
	        localIntentFilter.setPriority(Integer.MAX_VALUE);
	        smsReceiver = new SmsReceiver();
	        registerReceiver(smsReceiver, localIntentFilter);
			
	 	    //ʵ��
			this.set_enable = (Button)super.findViewById(R.id.set_enable);
			this.set_enable.setOnClickListener(new OnClickListenerGetGpsAppsetenble());
			this.get_disable = (Button)super.findViewById(R.id.get_disable);
			this.get_disable.setOnClickListener(new OnClickListenerGetGpsAppgetdisable());
			this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			
			this.get_location = (TextView)super.findViewById(R.id.get_location);
			this.get_agps = (TextView)super.findViewById(R.id.get_agps);
			this.get_gps_mode = (TextView)super.findViewById(R.id.get_gps);
			this.get_geofence = (TextView)super.findViewById(R.id.get_geofence);
			this.get_geofence_range = (TextView)super.findViewById(R.id.get_geofence_range);
			
			this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
			this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
			
	        //���ز���
	 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	 	    destnumber = uiState.getString("destnumber","00000000000" );
	 	    password = "";
	 	    
			location = uiState.getString("location", "");
			agps = uiState.getString("agpssetting", "");
			gps_mode = uiState.getString("gpsmode", "");
			geofence = uiState.getString("geofencesetting", "");
			geofence_range = uiState.getString("geofencerange", "");
			autoreport = uiState.getString("agps_gpsuatoreport_status", "");
			autoreport_time = uiState.getString("autoreport_time", "");		
			
			gpsAutoreportTime.setText(uiState.getString("autoreport_time", ""));
			
			if(agps.equals("0")) {
				this.get_agps.setText(getResources().getString(R.string.Disable));
			} else if(agps.equals("1")) {
				this.get_agps.setText(getResources().getString(R.string.Enable));
			}
			if(autoreport.equals("0")) {
				this.gpsAutoReport.setText(getResources().getString(R.string.Disable));
			} else if(autoreport.equals("1")) {
				this.gpsAutoReport.setText(getResources().getString(R.string.Enable));
			}		
			if(geofence.equals("0")) {
				this.get_geofence.setText(getResources().getString(R.string.Disable));
			} else if(geofence.equals("1")) {
				this.get_geofence.setText(getResources().getString(R.string.Enable));
			}
	
			if(gps_mode.equals("0")) {
				this.get_gps_mode.setText("Gps");
			} else if(gps_mode.equals("1")) {
				this.get_gps_mode.setText("User Plane");
			} else if(gps_mode.equals("2")){
				this.get_gps_mode.setText("Control Plane");
			}
			
			this.get_location.setText(location);
			this.get_geofence_range.setText(geofence_range);
	
			if(Utility.getShareValue(GetGpsApp.this, "GetGpsApp_URL").length()>0)
			{
				tvlcationUrl.setText(Utility.getShareValue(GetGpsApp.this, "GetGpsApp_URL"));
				tvlcationUrl.setVisibility(View.VISIBLE);
			}
			else
			{
				tvlcationUrl.setVisibility(View.GONE);
			}
			
			tvlcationUrl.setOnClickListener(this);
			
			//����LinearLayout
	//		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_gps);
	//		ll.setOnLongClickListener(new OnLongClickListenergentsosnumber());
			
	//        ll.setOnClickListener(new OnClickListener() {
	//            
	//            @Override
	//            public void onClick(View v) {
	//                // TODO Auto-generated method stub
	//                Log.e("ActivityMusic", "====bBLayout�������");
	//            }
	//        });
		
		} catch (Exception e) {
			// TODO: handle exception
			Utility.showToast(this,"error log:"+ e.toString(), Toast.LENGTH_LONG);
			Log.i("error:", e.toString());
		}
		
		
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetGpsApp.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetGpsApp.this.startActivity(it);
			GetGpsApp.this.finish();
		}
		
	}
	
/*	private class OnLongClickListenergentsosnumber implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(GetGpsApp.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			return false;
		}
		
	}
*/	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}

	public void onBackPressed() { 
		Intent it = new Intent(GetGpsApp.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetGpsApp.this.startActivity(it);
		GetGpsApp.this.finish();
    }
	
	//set enable
	private class OnClickListenerGetGpsAppsetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetGpsApp.this,gpsapp.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			GetGpsApp.this.startActivity(it);
			GetGpsApp.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenerGetGpsAppgetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetGpsApp.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetGpsApp.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			
			if(destnumber.equals("00000000000")){
				Toast.makeText(GetGpsApp.this, getResources().getString(R.string.Please_Add_one_device_first), Toast.LENGTH_SHORT).show();
				return;
			}

			String smstel = destnumber;//����

			String smsnote1 = "GET"+password+"#GPSINFO#GPSRANGE#SWGEOFENCE#SWGPS#AGPS#GEOFENCEPHONE#SWGPSREPORT#GPSREPORTTIME#";//
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
		}
	}

	//����Ϣ���ղ������ж�
	private class SmsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){  
	        	
	        	
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
//	                    Utility.showToast(GetGpsApp.this, "number:" + msg.getOriginatingAddress()  
//	                    + "   body:" + msg.getDisplayMessageBody() + "  time:"  
//	                            + msg.getTimestampMillis(), Toast.LENGTH_LONG);
	                      
	                    //String numbertemp = msg.getOriginatingAddress();
	                    String msgtemp = msg.getDisplayMessageBody();
	                    //������д�Լ����߼�  
	                    //if (msg.getOriginatingAddress().equals("+8618003049659")) 
	                    if (msgtemp.contains("+SWAGPS=")) {  
	                        //TODO
	                    	String  agps_status = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("agpsseting", agps_status);
	        		  		et.commit();
	        		  		if(agps_status.equals("0")){
	        		  			GetGpsApp.this.get_agps.setText(getResources().getString(R.string.Disable));
	        		  		} else {
	        		  			GetGpsApp.this.get_agps.setText(getResources().getString(R.string.Enable));
	        		  		}
	                    } else if(msgtemp.contains("+AGPS=")) {
	                    	String agps_mode = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("gpsmode", agps_mode);
	        		  		et.commit();

	        		  		if(agps_mode.equals("0")){
	        		  			GetGpsApp.this.get_gps_mode.setText(getResources().getString(R.string._GPS));
	        		  		} else if(agps_mode.equals("1")){
	        		  			//GetGpsApp.this.get_gps_mode.setText("User Plane");
	        		  		} else if(agps_mode.equals("2")){
	        		  			GetGpsApp.this.get_gps_mode.setText(getResources().getString(R.string._Control_Plane));
	        		  		}
	                    } else if(msgtemp.contains("+SWGEOFENCE=")) {
	                    	String geofence_status = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("geofencesetting", geofence_status);
	        		  		et.commit();
	        		  		if(geofence_status.equals("0")){
	        		  			GetGpsApp.this.get_geofence.setText(getResources().getString(R.string.Disable));
	        		  		} else if(geofence_status.equals("1")){
	        		  			GetGpsApp.this.get_geofence.setText(getResources().getString(R.string.Enable));
	        		  		}
	                    } else if(msgtemp.contains("+GPSRANGE=")) {
	                    	String geofence_range = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("geofencerange", geofence_range);
	        		  		et.commit();
	        		  		GetGpsApp.this.get_geofence_range.setText(geofence_range);
	                    } else if (msgtemp.contains("+GPSINFO=")) {
	                    	String gps_info = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("location", gps_info);
	        		  		et.commit();
	        		  		GetGpsApp.this.get_location.setText(gps_info);
	                    }	
	                    
	                    else if (msgtemp.contains("+GEOFENCEPHONE=")) {
	                    	String gps_info = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("get_geofence_number", gps_info);
	        		  		et.commit();
	        		  		GetGpsApp.this.get_geofence_number.setText(gps_info);
	                    }	
	                    
	                    if(msgtemp.indexOf("http://maps.google.com/maps")>=0)
	                    {
	                    	try {
															
		                    	double lat = 0;
		                    	double lng = 0;
		                    	String latlng = msgtemp.split("=").length>0 ? msgtemp.split("=")[1] : "";
		                    
		                    	//Log.d("xt-debug", latlng);
		                    	
		                    	if(latlng .length()>0)
		                    	{
		                    		lat = Double.parseDouble(latlng.split(",")[0].trim());
		                    		lng = Double.parseDouble(latlng.split(",")[1].substring(0,latlng.split(",")[1].lastIndexOf("(")-1).trim());		                    		
		                    	}
		                    	
		                    	tvlcationUrl.setText(msgtemp);
		                    	tvlcationUrl.setVisibility(View.VISIBLE);
		                    	Utility.setShareValue(GetGpsApp.this, "GetGpsApp_URL", msgtemp);
		                    	
		                    	
		                    	Utility.setShareValue(GetGpsApp.this, "GetGpsApp_latlng", lat+","+lng);
		                    	
		                    	//Toast.makeText(GetGpsApp.this, latlng, Toast.LENGTH_LONG);
		                    	
		                    	
	//	                    	System.out.println("number:" + msg.getOriginatingAddress()  
	//	        	                    + "   latlng:" + latlng + "  time:"  
	//	        	                            + msg.getTimestampMillis());  
		                    	
		                    	//Utility.showToast(GetGpsApp.this, latlng, Toast.LENGTH_LONG);
		                    	
	//	                    	mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker"));
	//	                    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15.0f));
		                    	
		                    	
		                    	mMap.clear();
		                    	LatLng SYDNEY = new LatLng(lat, lng);
		                    	Marker centerMarker = mMap.addMarker(new MarkerOptions()
			                    .position(SYDNEY)
			                    .draggable(true));
		                    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15.0f));
	                    	
	                    	} catch (Exception e) {
								// TODO: handle exception
	                    		Log.w("SmsReceiver error:", e.toString());
	                    	
	                    		
							}
	                    	
	                    }
	                    
	                } 
	            }
	        }  
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
			case R.id.tvlcationUrl:
				Intent intent = new Intent();        
		        intent.setAction("android.intent.action.VIEW");    
		        Uri content_url = Uri.parse(tvlcationUrl.getText().toString());   
		        intent.setData(content_url);  
		        startActivity(intent);
				break;
				
		}
		
	}

	@Override
	public void onMapReady(GoogleMap arg0) {
		// TODO Auto-generated method stub
		mMap = arg0;
		double lat = 0;
    	double lng = 0;
		String latlng = Utility.getShareValue(GetGpsApp.this, "GetGpsApp_latlng");
		if(latlng .length()>0)
    	{
    		lat = Double.parseDouble(latlng.split(",")[0]);
    		lng = Double.parseDouble(latlng.split(",")[1]);	                    		
    	}
		if(lat>0 && lng>0)
		{
			mMap.clear();
	    	LatLng SYDNEY = new LatLng(lat, lng);
	    	Marker centerMarker = mMap.addMarker(new MarkerOptions()
	        .position(SYDNEY)
	        .draggable(true));
	    	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 15.0f));
		}
    	
    	
	}
}

