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
import android.widget.Toast;

public class GetPeriodicCheck extends  Activity implements OnClickListener {
	private TextView get_device_check = null;
	private TextView get_device_check_number = null;
	private TextView get_time_range = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private String destnumber = null;
	private String password = null;
	
	private int pdcstate = 0;
	private String pdcnumber = null;
	private int pdctimerange = 0;
	private SmsReceiver smsReceiver;
	private Button btnBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		super.setContentView(R.layout.getperiodiccheck);
		
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
		
		this.get_device_check = (TextView)super.findViewById(R.id.get_device_check);
		this.get_device_check_number = (TextView)super.findViewById(R.id.get_device_check_number);
		this.get_time_range = (TextView)super.findViewById(R.id.get_time_range);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
		
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
		
 	   pdcstate = uiState.getInt("devicecheckstate", 0);
  	   if(pdcstate == 0)
  	   {
  		 this.get_device_check.setTextSize(18);
  		 this.get_device_check.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(pdcstate == 1)
  	   {
  		 this.get_device_check.setTextSize(18);
  		 this.get_device_check.setText(getResources().getString(R.string.Enable));
  	   }
  	   
  	   pdcnumber = uiState.getString("devicechecknumber","" );
 	    this.get_device_check_number.setText(pdcnumber);
 	    
 	   pdctimerange = uiState.getInt("timerange", 0);
	    if(pdctimerange == 1)
 	    	this.get_time_range.setText("24 "+getResources().getString(R.string.Hours));
 	    else if(pdctimerange == 2)
 	    	this.get_time_range.setText("48 "+getResources().getString(R.string.Hours));
 	    else if(pdctimerange == 3)
 	    	this.get_time_range.setText("72 "+getResources().getString(R.string.Hours));
 	    else if(pdctimerange == 4)
 	    	this.get_time_range.setText("96 "+getResources().getString(R.string.Hours));
 	    else if(pdctimerange == 5)
 	    	this.get_time_range.setText("120 "+getResources().getString(R.string.Hours));
 	    else if(pdctimerange == 6)
 	    	this.get_time_range.setText("144 "+getResources().getString(R.string.Hours));
 	    else if(pdctimerange == 7)
 	    	this.get_time_range.setText("168 "+getResources().getString(R.string.Hours));
		
		//����LinearLayout
		LinearLayout ll = (LinearLayout) findViewById(R.id.menu_get_periodic_check);
		ll.setOnLongClickListener(new OnLongClickListenergetdevicecheck());
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetPeriodicCheck.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			GetPeriodicCheck.this.startActivity(it);
			GetPeriodicCheck.this.finish();
		}
		
	}
	
	private class OnLongClickListenergetdevicecheck implements OnLongClickListener
	{
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(GetPeriodicCheck.this, "Long click linearlayout.", Toast.LENGTH_SHORT).show();
			
			return false;
		}
		
	}
	
	@Override
	protected void onRestart(){
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    
  	   pdcstate = uiState.getInt("devicecheckstate", 0);
  	   if(pdcstate == 0)
  	   {
  		 this.get_device_check.setTextSize(18);
  		 this.get_device_check.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(pdcstate == 1)
  	   {
  		 this.get_device_check.setTextSize(18);
  		 this.get_device_check.setText(getResources().getString(R.string.Enable));
  	   }
  	   
  	   pdcnumber = uiState.getString("devicechecknumber","" );
 	    this.get_device_check_number.setText(pdcnumber);
 	    
  	   pdctimerange = uiState.getInt("timerange", 0);
 	    if(pdctimerange == 1)
  	    	this.get_time_range.setText("24 "+getResources().getString(R.string.Hours));
  	    else if(pdctimerange == 2)
  	    	this.get_time_range.setText("48 "+getResources().getString(R.string.Hours));
  	    else if(pdctimerange == 3)
  	    	this.get_time_range.setText("72 "+getResources().getString(R.string.Hours));
  	    else if(pdctimerange == 4)
  	    	this.get_time_range.setText("96 "+getResources().getString(R.string.Hours));
  	    else if(pdctimerange == 5)
  	    	this.get_time_range.setText("120 "+getResources().getString(R.string.Hours));
  	    else if(pdctimerange == 6)
  	    	this.get_time_range.setText("144 "+getResources().getString(R.string.Hours));
  	    else if(pdctimerange == 7)
  	    	this.get_time_range.setText("168 "+getResources().getString(R.string.Hours));
 	    
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(smsReceiver);//����ͷţ���Ȼ����פ�ڴ�
	}	

	public void onBackPressed() { 
		Intent it = new Intent(GetPeriodicCheck.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		GetPeriodicCheck.this.startActivity(it);
		GetPeriodicCheck.this.finish();
    }
	
	//set enable
	private class OnClickListenersosnumbersetenble implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(GetPeriodicCheck.this,PeriodicCheckset.class);//ʵ��Intent
			it.putExtra("myinfo", "entry set sosnumber");//Key
			GetPeriodicCheck.this.startActivity(it);
			GetPeriodicCheck.this.finish();
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			GetPeriodicCheck.this.set_enable.setBackgroundResource(R.drawable.buttonform2);
			GetPeriodicCheck.this.get_disable.setBackgroundResource(R.drawable.btn_red2);
			String smstel = destnumber;//����
			
			String smsnote1 = "GET"+password+"#SWPERIODIC#PERIODICTIME#PERIODICPHONE#";

			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote1,null,null);
			//smsmanager.sendTextMessage(smstel,null,smsnote2,null,null);
			//smsmanager.sendTextMessage(smstel,null,smsnote3,null,null);
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
	                    if (msgtemp.contains("+RANGE=")) {  
	                        //TODO
	                    	String range_status = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putInt("timerange", Integer.parseInt(range_status));
	        		  		et.commit();
	        		  		if(Integer.parseInt(range_status) == 1){
	        		  			GetPeriodicCheck.this.get_time_range.setText("24 "+getResources().getString(R.string.Hours));
	        		  		} else if(Integer.parseInt(range_status) == 2){
	        		  			GetPeriodicCheck.this.get_time_range.setText("48 "+getResources().getString(R.string.Hours));
	        		  		}  else if(Integer.parseInt(range_status) == 3){
	        		  			GetPeriodicCheck.this.get_time_range.setText("72 "+getResources().getString(R.string.Hours));
	        		  		} else if(Integer.parseInt(range_status) == 4){
	        		  			GetPeriodicCheck.this.get_time_range.setText("96 "+getResources().getString(R.string.Hours));
	        		  		} else if(Integer.parseInt(range_status) == 5){
	        		  			GetPeriodicCheck.this.get_time_range.setText("120 "+getResources().getString(R.string.Hours));
	        		  		} else if(Integer.parseInt(range_status) == 6){
	        		  			GetPeriodicCheck.this.get_time_range.setText("144 "+getResources().getString(R.string.Hours));
	        		  		} else if(Integer.parseInt(range_status) == 7){
	        		  			GetPeriodicCheck.this.get_time_range.setText("168 "+getResources().getString(R.string.Hours));
	        		  		}
	                    } else if(msgtemp.contains("+PDCPHONE=")){
	                        //TODO
	                    	String dcp_num = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putString("devicechecknumber", dcp_num);
	        		  		et.commit();
	        		  		GetPeriodicCheck.this.get_device_check_number.setText(dcp_num);
	                    } else if (msgtemp.contains("+SWPDC=")) {  
	                        //TODO
	                    	String check_status = msgtemp.substring(msgtemp.lastIndexOf("=")+1,msgtemp.lastIndexOf("#"));
	                    	//Toast.makeText(GetSosNumber.this, "Received:"+msgtemp+" from:"+numbertemp, Toast.LENGTH_SHORT).show();
	        	        	//�����˶�����Ϣ�·����������,��ͨѶ¼��Ӧ�ý��ղ�����Ϣ��
	        	        	abortBroadcast();

	        	        	SharedPreferences uiState = getSharedPreferences("system", Activity.MODE_PRIVATE);
	        		  		Editor et=uiState.edit();
	        		  		et.putInt("devicecheckstate", Integer.parseInt(check_status));
	        		  		et.commit();
	        		  		if(Integer.parseInt(check_status) == 0){
	        		  			GetPeriodicCheck.this.get_device_check.setText(getResources().getString(R.string.Disable));
	        		  		} else if(Integer.parseInt(check_status) == 1){
	        		  			GetPeriodicCheck.this.get_device_check.setText(getResources().getString(R.string.Enable));
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
