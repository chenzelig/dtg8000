package com.daktel.MobileAlertCommunicator;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PeriodicCheckset extends Activity implements OnClickListener {
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;

	private Button set_enable = null;
	private Button get_disable = null;
	
	private ImageView mainmenu_return_but = null;
	
	private TextView device_check_name = null;
	private ImageView device_check_state = null;
	public static int devicecheckstate = 0;//1:on   0:off
	private EditText devicechecknumber_edit = null;
	private Button devicechecknumber_set = null;
	
	private SeekBar timerange_seek = null;
	private int timerange = 0;
	private int timerangebak = 0;
	
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.periodiccheck);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		//��̬ע����ն�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//����������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, localIntentFilter);
        
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
	
		this.device_check_name = (TextView)super.findViewById(R.id.device_check_name);
		this.device_check_state = (ImageView)super.findViewById(R.id.device_check_state);
		this.device_check_state.setOnClickListener(new OnClickListenerdevicecheck());
		
		this.devicechecknumber_edit = (EditText)super.findViewById(R.id.devicechecknumber_edit);
		this.devicechecknumber_set = (Button)super.findViewById(R.id.devicechecknumber_set);
		this.devicechecknumber_set.setOnClickListener(new OnClickListenerdevicechecknumber());
		
		this.timerange_seek = (SeekBar)super.findViewById(R.id.timerange_seek);
		//this.timerange_seek.setOnSeekBarChangeListener(new OnSeekBarChangeListenertimerange());
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	   String devicechecknumber = uiState.getString("devicechecknumber", "");
 	   devicechecknumber_edit.setText(devicechecknumber);
 	   
 	   devicecheckstate = uiState.getInt("devicecheckstate", 0);
  	   if(devicecheckstate == 0)
  	   {
  		  this.device_check_state.setImageResource(R.drawable.app_home_disable);
  		 this.device_check_name.setTextSize(18);
  		 this.device_check_name.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(devicecheckstate == 1)
  	   {
  		  this.device_check_state.setImageResource(R.drawable.app_home_enable);
  		 this.device_check_name.setTextSize(18);
  		 this.device_check_name.setText(getResources().getString(R.string.Enable));
  	   }
  	   
	    //time range
  	 timerange = uiState.getInt("timerange", 0);
  	 timerangebak = timerange;
	    if(timerange == 0)
	    this.timerange_seek.setProgress(0);
	    else
	    	this.timerange_seek.setProgress(timerange-1);
	    
 	    
	}
	
	//seekbar for time range
	private class OnSeekBarChangeListenertimerange implements SeekBar.OnSeekBarChangeListener
	{
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// TODO Auto-generated method stub
//			if(seekBar.getProgress() < 1)
//				seekBar.setProgress(1);
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		SmsManager smsmanager = SmsManager.getDefault();
			switch(seekBar.getProgress())
			{
				case 0:
			  		et.putInt("timerange", 1);
			  		et.commit();
			  		
			  		timerange = 1;
			  		if(timerangebak != timerange)
			  		{
			  			timerangebak = timerange;
						String smstel1 = destnumber;//����
						String smsnote1 = "SET"+password+"#RANGE#1#";//���
						smsmanager.sendTextMessage(smstel1,null,smsnote1,null,null);
						Toast.makeText(PeriodicCheckset.this, "Time Range 1 days.", Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 1:
			  		et.putInt("timerange", 2);
			  		et.commit();
			  		
			  		timerange = 2;
			  		if(timerangebak != timerange)
			  		{
			  			timerangebak = timerange;
						String smstel2 = destnumber;//����
						String smsnote2 = "SET"+password+"#RANGE#2#";//���
						smsmanager.sendTextMessage(smstel2,null,smsnote2,null,null);
						Toast.makeText(PeriodicCheckset.this, "Time Range 2 days.", Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 2:
			  		et.putInt("timerange", 3);
			  		et.commit();
			  		
			  		timerange = 3;
			  		if(timerangebak != timerange)
			  		{
			  			timerangebak = timerange;
						String smstel3 = destnumber;//����
						String smsnote3 = "SET"+password+"#RANGE#3#";//���
						smsmanager.sendTextMessage(smstel3,null,smsnote3,null,null);
						Toast.makeText(PeriodicCheckset.this, "Time Range 3 days.", Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 3:
			  		et.putInt("timerange", 4);
			  		et.commit();
			  		
			  		timerange = 4;
			  		if(timerangebak != timerange)
			  		{
			  			timerangebak = timerange;
						String smstel4 = destnumber;//����
						String smsnote4 = "SET"+password+"#RANGE#4#";//���
						smsmanager.sendTextMessage(smstel4,null,smsnote4,null,null);
						Toast.makeText(PeriodicCheckset.this, "Time Range 4 days.", Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 4:

			  		et.putInt("timerange", 5);
			  		et.commit();
			  		
			  		timerange = 5;
			  		if(timerangebak != timerange)
			  		{
			  			timerangebak = timerange;
						String smstel5 = destnumber;//����
						String smsnote5 = "SET"+password+"#RANGE#5#";//���
						smsmanager.sendTextMessage(smstel5,null,smsnote5,null,null);
						Toast.makeText(PeriodicCheckset.this, "Time Range 5 days.", Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 5:

			  		et.putInt("timerange", 6);
			  		et.commit();
			  		
			  		timerange = 6;
			  		if(timerangebak != timerange)
			  		{
			  			timerangebak = timerange;
						String smstel6 = destnumber;//����
						String smsnote6 = "SET"+password+"#RANGE#6#";//���
						smsmanager.sendTextMessage(smstel6,null,smsnote6,null,null);
						Toast.makeText(PeriodicCheckset.this, "Time Range 6 days.", Toast.LENGTH_SHORT).show();
			  		}
					
					break;
					
				case 6:

			  		et.putInt("timerange", 7);
			  		et.commit();
			  		
			  		timerange = 7;
			  		if(timerangebak != timerange)
			  		{
			  			timerangebak = timerange;
						String smstel7 = destnumber;//����
						String smsnote7 = "SET"+password+"#RANGE#7#";//���
						smsmanager.sendTextMessage(smstel7,null,smsnote7,null,null);
						Toast.makeText(PeriodicCheckset.this, "Time Range 7 days.", Toast.LENGTH_SHORT).show();
			  		}
					
					break;
				
				default:
					break;
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class OnClickListenerdevicechecknumber implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = PeriodicCheckset.this.devicechecknumber_edit.getText().toString().trim();//���
			if(editphonenumber_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editphonenumber_temp.equals(""))
					Toast.makeText(PeriodicCheckset.this, R.string.Please_input_phone_number, Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(PeriodicCheckset.this, R.string.Please_input_phone_number, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#PDCPHONE#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(PeriodicCheckset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
				
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("devicechecknumber", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	private class OnClickListenerdevicecheck implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if(devicecheckstate == 0)
	  		{
	  			devicecheckstate = 1;
	  			PeriodicCheckset.this.device_check_state.setImageResource(R.drawable.app_home_enable);
	  			PeriodicCheckset.this.device_check_name.setTextSize(18);
	  			PeriodicCheckset.this.device_check_name.setText(getResources().getString(R.string.Enable));
	  			
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWPDC#1#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(PeriodicCheckset.this, "Periodic Device Check Enable.", Toast.LENGTH_SHORT).show();
				
	  		}
	  		else if(devicecheckstate == 1)
	  		{
	  			devicecheckstate = 0;
	  			PeriodicCheckset.this.device_check_state.setImageResource(R.drawable.app_home_disable);
	  			PeriodicCheckset.this.device_check_name.setTextSize(18);
	  			PeriodicCheckset.this.device_check_name.setText(getResources().getString(R.string.Disable));
	  			
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWPDC#0#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(PeriodicCheckset.this, "Periodic Device Check Disable.", Toast.LENGTH_SHORT).show();
	  		}
	  		
	  		et.putInt("devicecheckstate", devicecheckstate);
	  		et.commit();
		}
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			//SET_PW_#SWPERIODIC#1#PERIODICTIME#2# PERIODICPHONE#13600000000#
			String smsnote = "SET"+password+"#PERIODICTIME#"+(timerange_seek.getProgress()+1)+"#";//���
			
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
			Editor et=uiState.edit();
			et.putInt("timerange", timerange_seek.getProgress()+1);
			et.commit();
			
			
			smsnote += "SWPERIODIC#"+devicecheckstate+"#";
			
		
			if(devicechecknumber_edit.getText().toString().length()>0)
			{
				
				smsnote += "PERIODICPHONE#"+devicechecknumber_edit.getText()+"#";
				
				et=uiState.edit();
				et.putString("devicechecknumber", devicechecknumber_edit.getText().toString());
				et.commit();
			}
			

			String smstel = destnumber;//����
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
			Toast.makeText(PeriodicCheckset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
			
			Intent it = new Intent(PeriodicCheckset.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			PeriodicCheckset.this.startActivity(it);
			PeriodicCheckset.this.finish();
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
	                    if (msgtemp.contains("+RANGE=")||msgtemp.contains("+SWPDC=")||msgtemp.contains("+PDCPHONE=")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(PeriodicCheckset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
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
 	    
  	   devicecheckstate = uiState.getInt("devicecheckstate", 0);
  	   if(devicecheckstate == 0)
  	   {
  		  this.device_check_state.setImageResource(R.drawable.app_home_disable);
  		 this.device_check_name.setTextSize(18);
  		 this.device_check_name.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(devicecheckstate == 1)
  	   {
  		  this.device_check_state.setImageResource(R.drawable.app_home_enable);
  		 this.device_check_name.setTextSize(18);
  		 this.device_check_name.setText(getResources().getString(R.string.Enable));
  	   }
  	   
	    //time range
 	 timerange = uiState.getInt("timerange", 0);
 	 timerangebak = timerange;
	    if(timerange == 0)
	    this.timerange_seek.setProgress(0);
	    else
	    	this.timerange_seek.setProgress(timerange-1);
	    
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(PeriodicCheckset.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		PeriodicCheckset.this.startActivity(it);
		PeriodicCheckset.this.finish();
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			PeriodicCheckset.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			PeriodicCheckset.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(PeriodicCheckset.this,GetPeriodicCheck.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			PeriodicCheckset.this.startActivity(it);
			PeriodicCheckset.this.finish();
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
	
}
