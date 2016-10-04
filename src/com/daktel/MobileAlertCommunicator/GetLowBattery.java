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
import android.view.View.OnLongClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GetLowBattery extends Activity  implements OnClickListener {
	private TextView get_low_battery_warning = null;
	private TextView get_alert_phone_number = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private String destnumber = null;
	private String password = null;
	
	private int batterystate = 0;
	private String batterynumber = null;
	private SmsReceiver smsReceiver;
	
	private Button btnBack;
	
	private TextView get_Battery_oltage_check;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.getlowbattery);
		
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
		this.set_enable.setBackgroundResource(R.drawable.buttonform2);
		this.get_disable.setBackgroundResource(R.drawable.btn_red2);
		
		this.get_low_battery_warning = (TextView)super.findViewById(R.id.get_low_battery_warning);
		this.get_alert_phone_number = (TextView)super.findViewById(R.id.get_alert_phone_number);
		this.get_Battery_oltage_check = (TextView)super.findViewById(R.id.get_Battery_oltage_check);
		
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		
 	   batterystate = uiState.getInt("lowbatterystate", 0);
  	   if(batterystate == 0)
  	   {
  		 this.get_low_battery_warning.setTextSize(18);
  		 this.get_low_battery_warning.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(batterystate == 1)
  	   {
  		 this.get_low_battery_warning.setTextSize(18);
  		 this.get_low_battery_warning.setText(getResources().getString(R.string.Enable));
  	   }
  	   
 	    batterynumber = uiState.getString("lowbatterynumber","" );
 	    this.get_alert_phone_number.setText(batterynumber);
		
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_low_battery);
		ll.setOnLongClickListener(new OnLongClickListenergetlowbattery());
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			GetLowBattery.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetLowBattery.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			String smstel = destnumber;//����

			String smsnote1 = "GET"+password+"#SWLOWBATT#BATTPHONE#BAT#";//���
			//String smsnote2 = "GET"+password+"#BATTPHONE#";//���
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			
			Intent it = new Intent(GetLowBattery.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetLowBattery.this.startActivity(it);
			GetLowBattery.this.finish();
		}
		
	}
	
	private class OnLongClickListenergetlowbattery implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			//Toast.makeText(GetLowBattery.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
			return false;
		}
		
	}
	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    
  	   batterystate = uiState.getInt("lowbatterystate", 0);
  	   if(batterystate == 0)
  	   {
  		 this.get_low_battery_warning.setTextSize(18);
  		 this.get_low_battery_warning.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(batterystate == 1)
  	   {
  		 this.get_low_battery_warning.setTextSize(18);
  		 this.get_low_battery_warning.setText(getResources().getString(R.string.Enable));
  	   }
  	   
 	    batterynumber = uiState.getString("lowbatterynumber","" );
 	    this.get_alert_phone_number.setText(batterynumber);
 	    
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}	

	public void onBackPressed() { 
		Intent it = new Intent(GetLowBattery.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetLowBattery.this.startActivity(it);
		GetLowBattery.this.finish();
    }
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetLowBattery.this,LowBatteryset.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			GetLowBattery.this.startActivity(it);
			GetLowBattery.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetLowBattery.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetLowBattery.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			String smstel = destnumber;//����

			String smsnote1 = "GET"+password+"#SWLOWBATT#BATTPHONE#BAT#";//���
			//String smsnote2 = "GET"+password+"#BATTPHONE#";//���
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			//smsmanager.sendTextMessage(smstel,null,smsnote2,null,null);
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
	                    
	                    
	                    for(int i=0;i<msg.getDisplayMessageBody().split("#").length;i++)
	                    {
	                    
		                    String msgtemp = msg.getDisplayMessageBody().split("#")[i];
		                    if(msgtemp.length()<=0)
		                    	continue;
		                    //������д�Լ����߼�  
		                    //if (msg.getOriginatingAddress().equals("+8618003049659")) 
		                    if (msgtemp.contains("+SWLOWBATT=")) {  
		                        //TODO
		                    	String lowbatt_status = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.length());
		                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
		        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
		        	        	abortBroadcast();
	
		        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
		        		  		Editor et=uiState.edit();
		        		  		et.putInt("lowbatterystate", Integer.parseInt(lowbatt_status));
		        		  		et.commit();
		        		  		if(Integer.parseInt(lowbatt_status) == 0){
		        		  			GetLowBattery.this.get_low_battery_warning.setText(getResources().getString(R.string.Disable));
		        		  		} else if(Integer.parseInt(lowbatt_status) == 1){
		        		  			GetLowBattery.this.get_low_battery_warning.setText(getResources().getString(R.string.Enable));
		        		  		}
		                    } 
		                    if(msgtemp.contains("+BATTPHONE=")){
		                    	String batt_num = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.length());
		                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
		        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
		        	        	abortBroadcast();
	
		        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
		        		  		Editor et=uiState.edit();
		        		  		et.putString("lowbatterynumber", batt_num);
		        		  		et.commit();
		        		  		GetLowBattery.this.get_alert_phone_number.setText(batt_num);
		                    }
		                    
		                    if(msgtemp.contains("BAT="))
		                    {
		                    	String batt_num = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.length());
		                    	double bat = Double.parseDouble(batt_num);
		                    	if(bat>=0 && bat<3.45)
		                    	{
		                    		get_Battery_oltage_check.setText("0%");
		                    	}
		                    	if(bat>=3.45 && bat<3.68)
		                    	{
		                    		get_Battery_oltage_check.setText("5%");
		                    	}
		                    	if(bat>=3.68 && bat<3.74)
		                    	{
		                    		get_Battery_oltage_check.setText("10%");
		                    	}
		                    	
		                    	if(bat>= 3.74 && bat<3.77)
		                    	{
		                    		get_Battery_oltage_check.setText("20%");
		                    	}
		                    	
		                    	if(bat>=3.77&& bat<3.79)
		                    	{
		                    		get_Battery_oltage_check.setText("30%");
		                    	}
		                    	
		                    	if(bat>=3.79 && bat<3.82)
		                    	{
		                    		get_Battery_oltage_check.setText("40%");
		                    	}
		                    	
		                    	if(bat>=3.82 && bat<3.87)
		                    	{
		                    		get_Battery_oltage_check.setText("50%");
		                    	}
		                    	
		                    	if(bat>=3.87 && bat<3.92)
		                    	{
		                    		get_Battery_oltage_check.setText("60%");
		                    	}
		                    	
		                    	if(bat>=3.92 && bat<3.98)
		                    	{
		                    		get_Battery_oltage_check.setText("70%");
		                    	}
		                    	
		                    	if(bat>=3.98 && bat<4.06)
		                    	{
		                    		get_Battery_oltage_check.setText("80%");
		                    	}
		                    	
		                    	if(bat>=4.06 && bat<4.2)
		                    	{
		                    		get_Battery_oltage_check.setText("90%");
		                    	}
		                    	
		                    	if(bat>=4.2)
		                    	{
		                    		get_Battery_oltage_check.setText("100%");
		                    	}
		                    	
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
				
		}
		
	}
}
