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
import android.widget.TextView;
import android.widget.Toast;

public class LowBatteryset extends Activity implements OnClickListener {
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;

	private Button set_enable = null;
	private Button get_disable = null;
	
	private ImageView mainmenu_return_but = null;
	
	private TextView low_battery_name = null;
	private ImageView low_battery_state = null;
	public static int lowbatterystate = 0;//1:on   0:off
	private EditText alerphonenumber_edit = null;
	private Button lowbatteryalert_set = null;
	
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lowbattery);
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
	
		this.low_battery_name = (TextView)super.findViewById(R.id.low_battery_name);
		this.low_battery_state = (ImageView)super.findViewById(R.id.low_battery_state);
		this.low_battery_state.setOnClickListener(new OnClickListenerlowbattery());
		
		this.alerphonenumber_edit = (EditText)super.findViewById(R.id.alerphonenumber_edit);
		this.lowbatteryalert_set = (Button)super.findViewById(R.id.lowbatteryalert_set);
		this.lowbatteryalert_set.setOnClickListener(new OnClickListenerlowbatterynumber());
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	   String lowbatterynumber = uiState.getString("lowbatterynumber", "");  
 	   alerphonenumber_edit.setText(lowbatterynumber);
 	   
 	   lowbatterystate = uiState.getInt("lowbatterystate", 0);
  	   if(lowbatterystate == 0)
  	   {
  		  this.low_battery_state.setImageResource(R.drawable.app_home_disable);
  		 this.low_battery_name.setTextSize(18);
  		 this.low_battery_name.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(lowbatterystate == 1)
  	   {
  		  this.low_battery_state.setImageResource(R.drawable.app_home_enable);
  		 this.low_battery_name.setTextSize(18);
  		 this.low_battery_name.setText(getResources().getString(R.string.Enable));
  	   }
 	    
	}
	
	private class OnClickListenerlowbatterynumber implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String editphonenumber_temp = LowBatteryset.this.alerphonenumber_edit.getText().toString().trim();//���
			if(editphonenumber_temp.equals("")||destnumber.equals("00000000000"))
			{	
				if(editphonenumber_temp.equals(""))
					Toast.makeText(LowBatteryset.this, R.string.Please_input_phone_number, Toast.LENGTH_SHORT).show();
				else if(destnumber.equals("00000000000"))
					Toast.makeText(LowBatteryset.this, R.string.Please_input_phone_number, Toast.LENGTH_SHORT).show();
			}
			else
			{
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#BATTPHONE#"+editphonenumber_temp+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(LowBatteryset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
				
				//save data
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("lowbatterynumber", editphonenumber_temp);
		  		et.commit();
			}
		}
		
	}
	
	private class OnClickListenerlowbattery implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if(lowbatterystate == 0)
	  		{
	  			lowbatterystate = 1;
	  			LowBatteryset.this.low_battery_state.setImageResource(R.drawable.app_home_enable);
	  			LowBatteryset.this.low_battery_name.setTextSize(18);
	  			LowBatteryset.this.low_battery_name.setText(getResources().getString(R.string.Enable));
	  			
//				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWLOWBATT#1#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(LowBatteryset.this, "Low Battery Warning Enable.", Toast.LENGTH_SHORT).show();
				
	  		}
	  		else if(lowbatterystate == 1)
	  		{
	  			lowbatterystate = 0;
	  			LowBatteryset.this.low_battery_state.setImageResource(R.drawable.app_home_disable);
	  			LowBatteryset.this.low_battery_name.setTextSize(18);
	  			LowBatteryset.this.low_battery_name.setText(getResources().getString(R.string.Disable));
	  			
				//for example:SET9999#PHONE1#911#
//				String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#SWLOWBATT#0#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(LowBatteryset.this, "Low Battery Warning Disable.", Toast.LENGTH_SHORT).show();
	  		}
	  		
	  		et.putInt("lowbatterystate", lowbatterystate);
	  		et.commit();
		}
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			String smstel = destnumber;//����
			String smsnote = "SET"+password+"#SWLOWBATT#"+lowbatterystate+"#";//���
			
			if(alerphonenumber_edit.getText().toString().length()>0)
			{
				smsnote+="BATTPHONE#"+alerphonenumber_edit.getText().toString()+"#";
			
				SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et=uiState.edit();
		  		et.putString("lowbatterynumber", alerphonenumber_edit.getText().toString());
		  		et.commit();
			}
				
				
			
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
			Toast.makeText(LowBatteryset.this, R.string.Low_Battery_Warning, Toast.LENGTH_SHORT).show();
			
			
			
			
			Intent it = new Intent(LowBatteryset.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			LowBatteryset.this.startActivity(it);
			LowBatteryset.this.finish();
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
	                    if (msgtemp.contains("+SWLOWBATT=")||msgtemp.contains("+BATTPHONE=")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(LowBatteryset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
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
 	    
  	   lowbatterystate = uiState.getInt("lowbatterystate", 0);
  	   if(lowbatterystate == 0)
  	   {
  		  this.low_battery_state.setImageResource(R.drawable.app_home_disable);
  		 this.low_battery_name.setTextSize(18);
  		 this.low_battery_name.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(lowbatterystate == 1)
  	   {
  		  this.low_battery_state.setImageResource(R.drawable.app_home_enable);
  		 this.low_battery_name.setTextSize(18);
  		 this.low_battery_name.setText(getResources().getString(R.string.Enable));
  	   }
  	   
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(LowBatteryset.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		LowBatteryset.this.startActivity(it);
		LowBatteryset.this.finish();
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
			LowBatteryset.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			LowBatteryset.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(LowBatteryset.this,GetLowBattery.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			LowBatteryset.this.startActivity(it);
			LowBatteryset.this.finish();
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
