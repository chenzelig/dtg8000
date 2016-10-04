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
//import android.widget.EditText;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class FallDetectset extends Activity implements OnClickListener  {
	private SmsReceiver smsReceiver;
	private String destnumber = null;
	private String password = null;

	public static int falldetectstate = 0;//1:on   0:off
	
	private TextView fall_detect_name = null;
	
	private Button set_enable = null;
	private Button get_disable = null;
	private ImageView mainmenu_return_but = null;
	
	private ImageView fall_detect_state = null;
	
	private Button btnBack;
	
	private EditText falldetectnumber_edit;
	private Button falldetectnumber_set;
	
	private RadioButton rdolow = null;
	private RadioButton rdonormal = null;
	private RadioButton rdolowhigh = null;
	public String sensitivitystep = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.fall_detect);
		setContentView(R.layout.fall_detect);
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		falldetectnumber_edit = (EditText)findViewById(R.id.falldetectnumber_edit);
		falldetectnumber_set = (Button)findViewById(R.id.falldetectnumber_set);
		falldetectnumber_set.setOnClickListener(this);
		
		
		this.rdolow = (RadioButton)super.findViewById(R.id.rdolow);
		this.rdonormal = (RadioButton)super.findViewById(R.id.rdonormal);
		this.rdolowhigh = (RadioButton)super.findViewById(R.id.rdolowhigh);
		
		//��̬ע����ն�����Ϣ
		IntentFilter localIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		//����������ȼ�
        localIntentFilter.setPriority(Integer.MAX_VALUE);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, localIntentFilter);
		
		this.fall_detect_state = (ImageView)super.findViewById(R.id.fall_detect_state);
		this.fall_detect_state.setOnClickListener(new OnClickListenerfalldetect());
		
 	    //ʵ��
		this.set_enable = (Button)super.findViewById(R.id.set_enable);
		this.set_enable.setOnClickListener(new OnClickListenersosnumbersetenble());
		this.get_disable = (Button)super.findViewById(R.id.get_disable);
		this.get_disable.setOnClickListener(new OnClickListenersosnumbergetdisable());
	    this.set_enable.setBackgroundResource(R.drawable.btn_red2);
		this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		
		this.mainmenu_return_but = (ImageView)super.findViewById(R.id.mainmenu_return_but);
		this.mainmenu_return_but.setOnClickListener(new OnClickListenerreturnbak());
	
		this.fall_detect_name = (TextView)super.findViewById(R.id.fall_detect_name);
        //���ز���
 	    SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
 	    destnumber = uiState.getString("destnumber","00000000000" );
 	    password = "";
 	    
 	   String falldetectnumber = uiState.getString("falldetectnumber", "");  
 	  falldetectnumber_edit.setText(falldetectnumber);
 	   
 	   falldetectstate = uiState.getInt("fallstate", 0);
 	   if(falldetectstate == 0)
 	   {
 		  this.fall_detect_state.setImageResource(R.drawable.app_home_disable);
 		 this.fall_detect_name.setTextSize(18);
 		 this.fall_detect_name.setText(getResources().getString(R.string.Disable));
 	   }
 	   else if(falldetectstate == 1)
 	   {
 		  this.fall_detect_state.setImageResource(R.drawable.app_home_enable);
 		 this.fall_detect_name.setTextSize(18);
 		 this.fall_detect_name.setText(getResources().getString(R.string.Enable));
 	   }

 	   
 	   
 	  sensitivitystep = uiState.getString("sensitivitystep","");
	    if(sensitivitystep.equals("0")){
	    	this.rdolow.setChecked(true);
	    } else if(sensitivitystep.equals("1")){
	    	this.rdonormal.setChecked(true);
	    } else if(sensitivitystep.equals("2")){
	    	this.rdolowhigh.setChecked(true);
	    }
	}
	
	private class OnClickListenerfalldetect implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		if(falldetectstate == 0)
	  		{
	  			falldetectstate = 1;
	  			FallDetectset.this.fall_detect_state.setImageResource(R.drawable.app_home_enable);
	  			FallDetectset.this.fall_detect_name.setTextSize(18);
	  			FallDetectset.this.fall_detect_name.setText(getResources().getString(R.string.Enable));
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWFALL#1#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(FallDetectset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
				
	  		}
	  		else if(falldetectstate == 1)
	  		{
	  			falldetectstate = 0;
	  			FallDetectset.this.fall_detect_state.setImageResource(R.drawable.app_home_disable);
	  			FallDetectset.this.fall_detect_name.setTextSize(18);
	  			FallDetectset.this.fall_detect_name.setText(getResources().getString(R.string.Disable));
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#SWFALL#0#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(FallDetectset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
	  		}
	  		
	  		
//	  		if(falldetectnumber_edit.getText().toString().length()>0)
//	  		{
//	  			String smstel = destnumber;//����
//				String smsnote = "SET"+password+"#FALLPHONE#"+falldetectnumber_edit.getText().toString()+"#";//���
//				SmsManager smsmanager = SmsManager.getDefault();
//				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
//				Toast.makeText(FallDetectset.this, "success", Toast.LENGTH_SHORT).show();
//				
//				
//				SharedPreferences uiState1   = getSharedPreferences("system", Activity.MODE_PRIVATE);
//		  		Editor et1=uiState1.edit();
//		  		et1.putString("falldetectnumber", falldetectnumber_edit.getText().toString());
//		  		et1.commit();
//	  		}
	  		
	  		
	  		et.putInt("fallstate", falldetectstate);
	  		et.commit();
		}
		
	}
	
	private class OnClickListenerreturnbak implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			
			//save data
			SharedPreferences uiState   = getSharedPreferences("system", Activity.MODE_PRIVATE);
	  		Editor et=uiState.edit();
	  		
	  		String smsnote = "SET"+password+"#";
	  		if(falldetectstate == 1)
	  		{
	  			//falldetectstate = 1;
	  			FallDetectset.this.fall_detect_state.setImageResource(R.drawable.app_home_enable);
	  			FallDetectset.this.fall_detect_name.setTextSize(18);
	  			FallDetectset.this.fall_detect_name.setText(getResources().getString(R.string.Enable));
	  			
				//for example:SET9999#PHONE1#911#
				String smstel = destnumber;//����
				smsnote +="SWFALL#1#";//���
				
				
	  		}
	  		else if(falldetectstate == 0)
	  		{
	  			//falldetectstate = 0;
	  			FallDetectset.this.fall_detect_state.setImageResource(R.drawable.app_home_disable);
	  			FallDetectset.this.fall_detect_name.setTextSize(18);
	  			FallDetectset.this.fall_detect_name.setText(getResources().getString(R.string.Disable));
	  			
				smsnote += "SWFALL#0#";//���
				
	  		}
	  		
	  		
	  		if(falldetectnumber_edit.getText().toString().length()>0)
	  		{

				smsnote += "FALLPHONE#"+falldetectnumber_edit.getText().toString()+"#";//���
				
				
				
				SharedPreferences uiState1   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et1=uiState1.edit();
		  		et1.putString("falldetectnumber", falldetectnumber_edit.getText().toString());
		  		et1.commit();
	  		}
	  		
	  		if(rdolow.isChecked())
	  		{
	  			sensitivitystep = "0";
				smsnote += "FALLSENS#0#";//���
				
	  		}
	  		if(rdonormal.isChecked())
	  		{
	  			sensitivitystep = "1";
				smsnote += "FALLSENS#1#";//���
				
	  		}
	  		if(rdolowhigh.isChecked())
	  		{
	  			sensitivitystep = "2";
				smsnote += "FALLSENS#2#";//���
				
	  		}
	  		
	  		String smstel = destnumber;//����
	  		
	  		SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
			Toast.makeText(FallDetectset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
	  		
	  		
	  		et.putInt("fallstate", falldetectstate);
	  		et.putString("sensitivitystep", sensitivitystep);	  		
	  		et.commit();
			
			// TODO Auto-generated method stub
			Intent it = new Intent(FallDetectset.this,MainMenuSetting.class);//ʵ��Intent
			it.putExtra("myinfo", "entry mainmenu");//Key
			FallDetectset.this.startActivity(it);
			FallDetectset.this.finish();
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
	                    if (msgtemp.contains("+SWFALL=")) 
	                    {  
	                        //TODO  
	                    	Toast.makeText(FallDetectset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
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
 	    
  	   falldetectstate = uiState.getInt("fallstate", 0);
  	   if(falldetectstate == 0)
  	   {
  		  this.fall_detect_state.setImageResource(R.drawable.app_home_disable);
  		 this.fall_detect_name.setTextSize(18);
  		 this.fall_detect_name.setText(getResources().getString(R.string.Disable));
  	   }
  	   else if(falldetectstate == 1)
  	   {
  		  this.fall_detect_state.setImageResource(R.drawable.app_home_enable);
  		 this.fall_detect_name.setTextSize(18);
  		 this.fall_detect_name.setText(getResources().getString(R.string.Enable));
  	   }
		super.onRestart();
	}
	
	public void onBackPressed() { 
		Intent it = new Intent(FallDetectset.this,MainMenuSetting.class);//ʵ��Intent
		it.putExtra("myinfo", "entry mainmenu");//Key
		FallDetectset.this.startActivity(it);
		FallDetectset.this.finish();
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
			FallDetectset.this.set_enable.setBackgroundResource(R.drawable.btn_red2);
			FallDetectset.this.get_disable.setBackgroundResource(R.drawable.buttonform2);
		}
		
	}
	
	//get disable <>
	private class OnClickListenersosnumbergetdisable implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it = new Intent(FallDetectset.this,GetFallDetect.class);//ʵ��Intent
			it.putExtra("myinfo", "entry get sosnumber");//Key
			FallDetectset.this.startActivity(it);
			FallDetectset.this.finish();
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
			case R.id.falldetectnumber_set:
				
				String smstel = destnumber;//����
				String smsnote = "SET"+password+"#FALLPHONE#"+falldetectnumber_edit.getText().toString()+"#";//���
				SmsManager smsmanager = SmsManager.getDefault();
				smsmanager.sendTextMessage(smstel,null,smsnote,null,null);
				Toast.makeText(FallDetectset.this, getResources().getString(R.string.Success), Toast.LENGTH_SHORT).show();
				
				
				SharedPreferences uiState1   = getSharedPreferences("system", Activity.MODE_PRIVATE);
		  		Editor et1=uiState1.edit();
		  		et1.putString("falldetectnumber", falldetectnumber_edit.getText().toString());
		  		et1.commit();
		  		
				break;
				
		}
		
	}
}
