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
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AlertNotification extends Activity implements OnClickListener {
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;

	public static int alertnotificationstate = 0;//1:on   0:off
	private TextView alert_notification_name = null;
	private ImageView alert_notification_state = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private Button btnBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.alertnotification);
		setContentView(R.layout.alertnotification);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		//��̬ע����ն�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//����������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, localIntentFilter);
        		
		this.alert_notification_name = (TextView)super.findViewById(R.id.alert_notification_name);
		this.alert_notification_state = (ImageView)super.findViewById(R.id.alert_notification_state);
		this.alert_notification_state.setOnClickListener(new OnClickListeneralertnotification());
		
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
	
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	   
 	  alertnotificationstate = uiState.getInt("alertnotificationstate", 0);
 	   if(alertnotificationstate == 0)
 	   {
 		  this.alert_notification_state.setImageResource(R.drawable.app_home_disable);
 		 this.alert_notification_name.setTextSize(18);
 		 this.alert_notification_name.setText(R.string.Disable);
 	   }
 	   else if(alertnotificationstate == 1)
 	   {
 		  this.alert_notification_state.setImageResource(R.drawable.app_home_enable);
 		 this.alert_notification_name.setTextSize(18);
 		 this.alert_notification_name.setText(R.string.Enable);
 	   }

	}
	
	private class OnClickListeneralertnotification implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if(alertnotificationstate == 0)
	  		{
	  			alertnotificationstate = 1;
	  			AlertNotification.this.alert_notification_state.setImageResource(R.drawable.app_home_enable);
	  			AlertNotification.this.alert_notification_name.setTextSize(18);
	  			AlertNotification.this.alert_notification_name.setText(getResources().getString(R.string.Enable));
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWALT#1#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(AlertNotification.this, R.string.Alerts_Notification_Enable, Toast.LENGTH_SHORT).show();
				
	  		}
	  		else if(alertnotificationstate == 1)
	  		{
	  			alertnotificationstate = 0;
	  			AlertNotification.this.alert_notification_state.setImageResource(R.drawable.app_home_disable);
	  			AlertNotification.this.alert_notification_name.setTextSize(18);
	  			AlertNotification.this.alert_notification_name.setText(getResources().getString(R.string.Disable));
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWALT#0#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(AlertNotification.this, R.string.Alerts_Notification_Disable, Toast.LENGTH_SHORT).show();
	  		}
	  		
	  		et.putInt("alertnotificationstate", alertnotificationstate);
	  		et.commit();
		}
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(AlertNotification.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			AlertNotification.this.startActivity(it);
			AlertNotification.this.finish();
		}
		
	}
	
	//����Ϣ���ղ������ж�
	private class SmsReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
	        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){  
	        	//Log.v("huahua", "MainActivity�յ����ŷ�������Ϣ");  
	        	
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
	                   
	                      
	                    //String numbertemp = msg.getOriginatingAddress();
	                    String msgtemp = msg.getDisplayMessageBody();
	                    //������д�Լ����߼�  
	                    //if (msg.getOriginatingAddress().equals("+8618003049659")) 
	                    if (msgtemp.contains("+SWALT=")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(AlertNotification.this, getResources().getString(R.string.Success)+msgtemp, Toast.LENGTH_SHORT).show();
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
 	    
 	 	  alertnotificationstate = uiState.getInt("alertnotificationstate", 0);
 	 	   if(alertnotificationstate == 0)
 	 	   {
 	 		  this.alert_notification_state.setImageResource(R.drawable.app_home_disable);
 	 		 this.alert_notification_name.setTextSize(18);
 	 		 this.alert_notification_name.setText(getResources().getString(R.string.Disable));
 	 	   }
 	 	   else if(alertnotificationstate == 1)
 	 	   {
 	 		  this.alert_notification_state.setImageResource(R.drawable.app_home_enable);
 	 		 this.alert_notification_name.setTextSize(18);
 	 		 this.alert_notification_name.setText(getResources().getString(R.string.Enable));
 	 	   }
 	 	   
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(AlertNotification.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		AlertNotification.this.startActivity(it);
		AlertNotification.this.finish();
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
			AlertNotification.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			AlertNotification.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(AlertNotification.this,GetAlertNotification.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			AlertNotification.this.startActivity(it);
			AlertNotification.this.finish();
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
